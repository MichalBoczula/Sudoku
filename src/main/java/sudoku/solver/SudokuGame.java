package sudoku.solver;

import java.util.List;

public class SudokuGame {
    private SudokuVerifier sudokuVerifier = new SudokuVerifier();
    private SudokuInputVerifier sudokuInputVerifier = new SudokuInputVerifier();
    private int[][] board = sudokuVerifier.getSudokuUnsolved();
    private SudokuSolver sudokuSolver = new SudokuSolver(board);
    private List<String> occupiedRows = sudokuVerifier.getOccupiedRows();
    private List<String> occupiedCols = sudokuVerifier.getOccupiedCols();

    public void game() {
        do {
            display(board);
            String row = sudokuInputVerifier.verifyInput();
//            String col = sudokuInputVerifier.verifyInput();
//            String number = sudokuInputVerifier.verifyInput();
            isEnd(row);
            if (row.equals("exit")){
                break;
            }
//            isEnd(col);
//            isEnd(number);
        } while (!sudokuSolver.isSolve(board));
        display(board);
        System.out.println(occupiedRows.toString());
        System.out.println(occupiedCols.toString());
    }

    public void isEnd(String string){
        if (string.equals("sudoku")){
            sudokuSolver.solve();
        } else if(string.equals("hint")){

        }
    }

    private void display(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print("|" + board[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
