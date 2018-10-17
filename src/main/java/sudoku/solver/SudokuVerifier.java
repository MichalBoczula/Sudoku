package sudoku.solver;

import java.util.ArrayList;
import java.util.List;

public class SudokuVerifier {
    private SudokuCreator sudokuCreator;
    private SudokuSolver sudokuSolver;
    private List<String> occupiedFields = new ArrayList<>();
    private List<String> playFields = new ArrayList<>();

    public SudokuVerifier() {
        this.sudokuCreator = new SudokuCreator();
        this.sudokuSolver = new SudokuSolver(sudokuCreator.getBoard());
        verifyCanSudokuBeSolved();
        setOccupiedFields();
        setPlayFields();
    }

    private void verifyCanSudokuBeSolved() {
        if (!sudokuSolver.solve()) {
            this.sudokuCreator = new SudokuCreator();
            this.sudokuSolver = new SudokuSolver(sudokuCreator.getBoard());
            verifyCanSudokuBeSolved();
        }
    }

    private void setOccupiedFields(){
        occupiedFields = sudokuCreator.getOccupiedFields();
    }

    private void setPlayFields() {
        playFields = sudokuCreator.getPlayFields();
    }

    public List<String> getOccupiedFields() {
        return new ArrayList<>(occupiedFields);
    }

    public List<String> getPlayFields() {
        return new ArrayList<>(playFields);
    }

    public int[][] getSudokuUnsolved() {
        return sudokuCreator.getUnsolvedSudoku();
    }
}
