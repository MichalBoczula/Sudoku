package sudoku.solver;

public class Main {
    public static void main(String[] args) {
        final SudokuCreator sudokuCreator = new SudokuCreator();
        final SudokuSolver sudokuSolver = new SudokuSolver(sudokuCreator.getBoard());
        sudokuSolver.display();
        System.out.println("Solve");
        sudokuSolver.solve();
        sudokuSolver.display();
    }
}
