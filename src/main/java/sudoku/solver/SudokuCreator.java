package sudoku.solver;

public class SudokuCreator {
    private int[][] board = new int[9][9];
    private static final int SIZE = 9;
    private RandomNumber randomNumber = new RandomNumber();

    public SudokuCreator() {
        createEmptySudokuBoard();
        createSudokuBoard();
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
                    System.out.println("row: " + row + ", col: " + col);
                }
            }
        } while (end < 80);
    }

    public int[][] getBoard() {
        return board;
    }
}
