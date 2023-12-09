package Models;

import Exceptions.InvalidBoardGameDimensionsException;
import Strategies.GameWinningStrategy;
import Strategies.OrderOneGameWinningStrategy;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private GameWinningStrategy gameWinningStrategy;

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public static Builder getBuilder(){     // as Builder class is static this method will also be static
        return new Builder();
    }

    public void makeNextMove() {
        Player playerWhoseTurnItIs = players.get(nextPlayerIndex);
        System.out.println("Now it's " + playerWhoseTurnItIs.getName() + "'s turn");

        Move move = playerWhoseTurnItIs.decideMove(board);

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (board.getCells().get(row).get(col).getCellState().equals(CellState.EMPTY)) {    // if cell is empty
            board.applyMove(row,col,move.getPlayer());  // apply move of that player at said position
            moves.add(move);                            // register the move

            // check winner
            if(gameWinningStrategy.checkWinner(board, move)) {
                gameStatus = GameStatus.ENDED;
                return;
            }

            // check DRAW
            if(moves.size() == board.getCells().size() * board.getCells().size())
                gameStatus = GameStatus.DRAW;

            nextPlayerIndex += 1;
            nextPlayerIndex %= players.size();  // so that it doesn't go out of bound
        } else {
            // throw an exception
        }
    }

    // Builder design pattern to create a game object after performing certain validations
    public static class Builder{
        private int dimension;  // to create Board object dimension will suffice
        private List<Player> players;

        private boolean isvalid() throws InvalidBoardGameDimensionsException {
            if(this.dimension < 3)
                throw new InvalidBoardGameDimensionsException("Dimensions entered must be greater than 2");

            return true;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build(){
            try{
                isvalid();
            }
            catch (InvalidBoardGameDimensionsException e){
                System.out.println(e);
                return null;
            }

            Game game = new Game();
            game.setBoard(new Board(this.dimension));
            game.setPlayers(this.players);
            game.setNextPlayerIndex(0);
            game.setMoves(new LinkedList<>());
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));

            return game;
        }
    }
}
