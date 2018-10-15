package sudoku.solver;

import java.util.List;

public class SudokuGame {
    private SudokuVerifier sudokuVerifier = new SudokuVerifier();
    private SudokuInputVerifier sudokuInputVerifier = new SudokuInputVerifier();
    private int[][] board = sudokuVerifier.getSudokuUnsolved();
    private SudokuSolver sudokuSolver = new SudokuSolver(board);
    private List<String> occupiedFields = sudokuVerifier.getOccupiedFields();
    private List<String> playFields = sudokuVerifier.getPlayFields();
    private RandomNumber randomNumber = new RandomNumber();

    public void game() {
        do {
            System.out.println("choose number form 1 to 9 to choose field in board\n" +
                    "and choose number from 1 to 9 to put into sudoku board");
            display(board);
            System.out.println("give row number or:" +
                    "\nwrite sudoku to solve puzzle" +
                    "\nwrite hint to got help" +
                    "\nwrite exit to end game");
            String row = sudokuInputVerifier.verifyInput();
            if (isEnd(row)) {
                break;
            }
            if (!row.equals("hint")) {
                System.out.println("give col number");
                String col = sudokuInputVerifier.verifyInput();
                System.out.println("give number to input");
                String number = sudokuInputVerifier.verifyInput();
                if (!checkIsFieldEmpty(row, col)) {
                    board[Integer.parseInt(row) - 1][Integer.parseInt(col) - 1] = Integer.parseInt(number);
                    playFields.remove(row + col);
                } else {
                    System.out.println("Sorry this field is occupied pls choose another one");
                }
            }
        } while (!sudokuSolver.isSolve(board));
        display(board);
    }

    private boolean isEnd(String string) {
        switch (string) {
            case "sudoku":
                isMatch();
                sudokuSolver.solve();
                return true;
            case "hint":
                hint();
                return false;
            case "exit":
                return true;
            case "error":
        }
        return false;
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

    private boolean checkIsFieldEmpty(String row, String col) {
        String toCompare = row + col;
        return occupiedFields.contains(toCompare);
    }

    private void hint() {
        int number = randomNumber.getNextInt();
        String rowAndCol = playFields.get(randomNumber.getNextIntToHint(playFields.size()));
        String row = rowAndCol.substring(0, 1);
        String col = rowAndCol.substring(1);
        if (sudokuSolver.isOk(
                Integer.parseInt(row),
                Integer.parseInt(col),
                number
        )) {
            board[Integer.parseInt(row)][Integer.parseInt(col)] = number;
            System.out.println("In row: " + row + " col: " + col + " was input number: " + number + "\n");
        } else {
            if (!findNumberToInput(row, col)) {
                hint();
            }
        }
    }

    private boolean findNumberToInput(String row, String col) {
        boolean answer = false;
        int number = 1;
        while (number <= 9 || answer == false) {
            if (sudokuSolver.isOk(
                    Integer.parseInt(row),
                    Integer.parseInt(col),
                    number
            )) {
                board[Integer.parseInt(row)][Integer.parseInt(col)] = number;
                answer = true;
            } else {
                number++;
            }
        }
        return answer;
    }

    private void isMatch() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!sudokuSolver.isOk(row, col, board[row][col]) && !occupiedFields.contains(row + col)){
                    board[row][col] = 0;
                }
            }
        }
    }
}
