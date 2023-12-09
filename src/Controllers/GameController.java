package Controllers;

import Models.Game;
import Models.GameStatus;
import Models.Player;

import java.util.List;

public class GameController {
    public Game createGame(int dimensions, List<Player> players){
        return Game.getBuilder().setDimension(dimensions).setPlayers(players).build();
    }
    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }
    public void setGameStatus(Game game, GameStatus gameStatus){
        game.setGameStatus(gameStatus);
    }

    public int getNextPlayerIndex(Game game){
        return game.getNextPlayerIndex();
    }
    public void setNextPlayerIndex(Game game, int nextPlayerIndex){
        game.setNextPlayerIndex(nextPlayerIndex);
    }

    public List<Player> getPlayers(Game game){
        return game.getPlayers();
    }
    public void setPLayers(Game game, List<Player> players){
        game.setPlayers(players);
    }

    public void display(Game game) {
        game.getBoard().displayBoard();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }
}
