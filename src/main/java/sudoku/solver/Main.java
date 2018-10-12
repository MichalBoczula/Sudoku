package sudoku.solver;

public class Main {
    public static void main(String[] args) {
        SudokuVerifier sudokuVerifier = new SudokuVerifier();
        display(sudokuVerifier.getSudokuSolved());
        display(sudokuVerifier.getSudokuUnsolved());
    }

    public static void display(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print("|" + board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
