package sudoku.solver;

public class SudokuVerifier {
    private SudokuCreator sudokuCreator;
    private SudokuSolver sudokuSolver;

    public SudokuVerifier() {
        this.sudokuCreator = new SudokuCreator();
        this.sudokuSolver = new SudokuSolver(sudokuCreator.getBoard());
        verifyCanSudokuBeSolved();
    }

    private void verifyCanSudokuBeSolved() {
        if (!sudokuSolver.solve()) {
            this.sudokuCreator = new SudokuCreator();
            this.sudokuSolver = new SudokuSolver(sudokuCreator.getBoard());
            verifyCanSudokuBeSolved();
        }
    }

    public int[][] getSudokuSolved() {
        return sudokuSolver.getBoard();
    }

    public int[][] getSudokuUnsolved() {
        return sudokuCreator.getUnsolvedSudoku();
    }
}
