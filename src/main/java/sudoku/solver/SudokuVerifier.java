package sudoku.solver;

public class SudokuVerifier {
    private SudokuCreator sudokuCreator;
    private SudokuSolver sudokuSolver;

    public SudokuVerifier() {
        try {
            this.sudokuCreator = new SudokuCreator();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        this.sudokuSolver = new SudokuSolver(sudokuCreator.getBoard());
        verifyCanSudokuBeSolved();
    }

    private void verifyCanSudokuBeSolved() {
        if (!sudokuSolver.solve()) {
            try {
                this.sudokuCreator = new SudokuCreator();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
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
