package sudoku.solver;

public class Main {
    public static void main(String[] args) {
        SudokuInputVerifier sudokuInputVerifier = new SudokuInputVerifier();
        System.out.println(sudokuInputVerifier.verifyInput());
    }

}
