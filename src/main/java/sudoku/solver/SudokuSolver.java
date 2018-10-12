package sudoku.solver;

public class SudokuSolver {
    private int[][] board;
    private static final int EMPTY = 0;
    private static final int SIZE = 9;

    public SudokuSolver(int[][] board) {
        this.board = board;
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

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int number = 1; number <= SIZE; number++) {
                        if (isOk(row, col, number)) {
                            board[row][col] = number;

                            if (solve()) {
                                return true;
                            } else {
                                board[row][col] = EMPTY;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSolve(int[][] board) {
        boolean answer = true;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] != EMPTY) {
                    if (isOk(row, col, board[row][col])) {
                        answer = true;
                    }
                } else {
                    answer = false;
                }
            }
        }
        return answer;
    }

    public int[][] getBoard() {
        return board;
    }

    public void display() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print("|" + board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

}