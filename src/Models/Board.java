package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> cells;

    public Board(int dimension) {
        // initialised each cell in the board
        this.cells = new ArrayList<>();
        for(int i=0;i<dimension;i++){
            this.cells.add(new ArrayList<Cell>());
            for(int j=0;j<dimension;j++)
                this.cells.get(i).add(new Cell(i,j));
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public void displayBoard() {
        //Print the board.
        int boardSize = cells.size();
        System.out.println();
        System.out.println("Here's your Board. Good luck!!");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++)
                if (cells.get(i).get(j).getCellState().equals(CellState.EMPTY))
                System.out.print("|  |");
                else
                    System.out.print("|" + cells.get(i).get(j).getPlayer().getSymbol() + "|");

            System.out.println();
        }
    }

    public void applyMove(int row, int col, Player player) {
        cells.get(row).get(col).setCellState(CellState.FILLED);
        cells.get(row).get(col).setPlayer(player);
    }
}
