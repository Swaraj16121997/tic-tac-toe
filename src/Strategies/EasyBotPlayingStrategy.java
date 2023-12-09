package Strategies;
import Models.*;


public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    // whichever is the first empty cell insert bot symbol there
    @Override
    public Move decideMove(Player player, Board board) {
        for (int i = 0; i < board.getCells().size(); i++) {
            for (int j = 0; j < board.getCells().size(); j++) {
                if(board.getCells().get(i).get(j).getCellState() == CellState.EMPTY) {
                    // bot will make a move
                    return new Move(player, new Cell(i, j));
                }
            }
        }
        return null; // throw an exception
    }
}
