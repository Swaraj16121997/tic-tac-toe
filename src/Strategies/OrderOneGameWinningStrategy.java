package Strategies;

import Models.Board;
import Models.Move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy{
    // hashmap (the symbol, its count) in each row, hence, list of hashmap
    List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();

    // hashmap (the symbol, its count) in each column, hence, list of hashmap
    List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();

    // hashmap (the symbol, its count) in both the diagonals, hence, just hashmaps
    HashMap<Character, Integer> topLeftDiagonalSymbolCounts = new HashMap<>();
    HashMap<Character, Integer> topRightDiagonalSymbolCounts = new HashMap<>();


    public OrderOneGameWinningStrategy(int dimension) {
        for (int i = 0; i < dimension; i++) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move) {
        char symbol = move.getPlayer().getSymbol();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        int dimension = board.getCells().size();

        // update the row hashmap of the cell
        if(rowSymbolCounts.get(row).containsKey(symbol))
            rowSymbolCounts.get(row).put(symbol, rowSymbolCounts.get(row).get(symbol) + 1);
        else
            rowSymbolCounts.get(row).put(symbol, 1);

        // update the column hashmap of the cell
        if (colSymbolCounts.get(col).containsKey(symbol))
            colSymbolCounts.get(col).put(symbol, colSymbolCounts.get(col).get(symbol) + 1);
        else
            colSymbolCounts.get(col).put(symbol, 1);

        // update the diagonal hashmaps of the cell, if applicable
        if (row == col) {   // left diagonal cells
            if (topLeftDiagonalSymbolCounts.containsKey(symbol))
                topLeftDiagonalSymbolCounts.put(symbol, topLeftDiagonalSymbolCounts.get(symbol) + 1);
            else
                topLeftDiagonalSymbolCounts.put(symbol, 1);
        }

        if (row + col == dimension - 1) {   // right diagonal cells
            if (topRightDiagonalSymbolCounts.containsKey(symbol))
                topRightDiagonalSymbolCounts.put(symbol, topRightDiagonalSymbolCounts.get(symbol) + 1);
            else
                topRightDiagonalSymbolCounts.put(symbol, 1);
        }

        // the winning conditions
        if(rowSymbolCounts.get(row).get(symbol) == dimension || colSymbolCounts.get(col).get(symbol) == dimension)
            return true;

        if(row == col && topLeftDiagonalSymbolCounts.get(symbol) == dimension)
            return true;

        if(row + col == dimension - 1 && topRightDiagonalSymbolCounts.get(symbol) == dimension)
            return true;


        return false;
    }
}
