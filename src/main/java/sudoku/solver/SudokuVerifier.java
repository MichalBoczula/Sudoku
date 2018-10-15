package sudoku.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void setPlayFields() {
        playFields = sudokuCreator.getPlayFields();
    }

    public List<String> getOccupiedFields() {
        return occupiedFields.stream().collect(Collectors.toList());
    }

    public List<String> getPlayFields() {
        return playFields;
    }

    public int[][] getSudokuUnsolved() {
        return sudokuCreator.getUnsolvedSudoku();
    }

}
