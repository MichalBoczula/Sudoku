package sudoku.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuVerifier {
    private SudokuCreator sudokuCreator;
    private SudokuSolver sudokuSolver;
    private List<String> occupiedRows = new ArrayList<>();
    private List<String> occupiedCols = new ArrayList<>();

    public SudokuVerifier() {
        this.sudokuCreator = new SudokuCreator();
        this.sudokuSolver = new SudokuSolver(sudokuCreator.getBoard());
        verifyCanSudokuBeSolved();
        getOccupiedRowsAndCols();
    }

    private void verifyCanSudokuBeSolved() {
        if (!sudokuSolver.solve()) {
            this.sudokuCreator = new SudokuCreator();
            this.sudokuSolver = new SudokuSolver(sudokuCreator.getBoard());
            verifyCanSudokuBeSolved();
        }
    }

    public void getOccupiedRowsAndCols() {
        occupiedRows = sudokuCreator.getFields().stream()
                .map(s -> s.substring(0,1))
                .collect(Collectors.toList());
        occupiedCols = sudokuCreator.getFields().stream()
                .map(s -> s.substring(1))
                .collect(Collectors.toList());
    }

    public int[][] getSudokuSolved() {
        return sudokuSolver.getBoard();
    }

    public int[][] getSudokuUnsolved() {
        return sudokuCreator.getUnsolvedSudoku();
    }

    public List<String> getOccupiedRows() {
        return occupiedRows;
    }

    public List<String> getOccupiedCols() {
        return occupiedCols;
    }
}
