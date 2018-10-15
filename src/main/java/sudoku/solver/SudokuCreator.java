package sudoku.solver;

import java.util.ArrayList;
import java.util.List;

public class SudokuCreator implements Cloneable {
    private static final int SIZE = 9;
    private int[][] board = new int[SIZE][SIZE];
    private int[][] unsolvedBoard;
    private RandomNumber randomNumber = new RandomNumber();
    private List<String> occupiedFields = new ArrayList<>();
    private List<String> playFields = new ArrayList<>();

    public SudokuCreator() {
        createEmptySudokuBoard();
        createSudokuBoard();
        unsolvedBoard = deepClone();
        putOccupiedFieldsIntoMap();
    }

    private boolean isInRow(int row, int number) {
        for (int i = 0; i < SIZE; i++)
            if (board[row][i] == number)
                return true;

        return false;
    }

    private boolean isInCol(int col, int number) {
        for (int i = 0; i < SIZE; i++)
            if (board[i][col] == number)
                return true;

        return false;
    }

    private boolean isInBox(int row, int col, int number) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (board[i][j] == number)
                    return true;

        return false;
    }

    private boolean isOk(int row, int col, int number) {
        return !isInRow(row, number) && !isInCol(col, number) && !isInBox(row, col, number);
    }

    private void createEmptySudokuBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = 0;
            }
        }
    }

    private int[][] deepClone() {
        int[][] cloneBoard = new int[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                cloneBoard[row][col] = board[row][col];
            }
        }
        return cloneBoard;
    }

    private void createSudokuBoard() {
        int end = 0;
        do {
            for (int row = 0; row < SIZE; row++) {
                for (int col = 0; col < SIZE; col++) {
                    int number = randomNumber.getNextInt();
                    int numberToAdd = randomNumber.getNextInt();
                    if (number > 6) {
                        if (isOk(row, col, numberToAdd)) {
                            board[row][col] = numberToAdd;
                        }
                    }
                    end++;
                }
            }
        } while (end < 80);
    }

    private void putOccupiedFieldsIntoMap() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++){
                if (unsolvedBoard[row][col] != 0){
                    String string = String.valueOf(row) + String.valueOf(col);
                    occupiedFields.add(string);
                } else if (unsolvedBoard[row][col] == 0){
                    String string = String.valueOf(row) + String.valueOf(col);
                    playFields.add(string);
                }
            }
        }
    }

    public int[][] getBoard() {
        int[][] boardToReturn = board.clone();
        return boardToReturn;
    }

    public int[][] getUnsolvedSudoku() {
        return unsolvedBoard;
    }

    public List<String> getOccupiedFields() {
        return occupiedFields;
    }

    public List<String> getPlayFields() {
        return playFields;
    }
}
