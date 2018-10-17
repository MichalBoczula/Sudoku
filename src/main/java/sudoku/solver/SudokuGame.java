package sudoku.solver;

import java.util.List;

public class SudokuGame {
    private SudokuPrintColour sudokuPrintColour = new SudokuPrintColour();
    private SudokuVerifier sudokuVerifier = new SudokuVerifier();
    private SudokuInputVerifier sudokuInputVerifier = new SudokuInputVerifier();
    private int[][] board = sudokuVerifier.getSudokuUnsolved();
    private SudokuSolver sudokuSolver = new SudokuSolver(board);
    private List<String> occupiedFields = sudokuVerifier.getOccupiedFields();
    private List<String> playFields = sudokuVerifier.getPlayFields();
    private RandomNumber randomNumber = new RandomNumber();

    public void game() {
        do {
            System.out.println("choose number form 0 to 8 to choose field in board\n" +
                    "and choose number from 1 to 9 to put into sudoku board");
            display(board);
            System.out.println("give row number or:" +
                    "\nwrite sudoku to solve puzzle" +
                    "\nwrite hint to got help" +
                    "\nwrite exit to end game");
            String row = sudokuInputVerifier.verifyRowAndColInput();
            if (isEnd(row)) {
                break;
            }
            if (!row.equals("hint")) {
                System.out.println("give col number");
                String col = sudokuInputVerifier.verifyRowAndColInput();
                System.out.println("give number to input");
                String number = sudokuInputVerifier.verifyInput();
                if (!checkIsFieldEmpty(row, col)) {
                    board[Integer.parseInt(row)][Integer.parseInt(col)] = Integer.parseInt(number);
                    playFields.remove(row + col);
                    System.out.println(SudokuPrintColour.ANSI_GREEN);
                    System.out.println("\nInput in row: " + row + " col: " + col + " was input number: " + number + "\n");
                    System.out.println(SudokuPrintColour.ANSI_RESET);
                    if (sudokuSolver.isOk(
                            Integer.parseInt(row),
                            Integer.parseInt(col),
                            Integer.parseInt(number))) {
                        occupiedFields.add(
                                String.valueOf(row) + String.valueOf(col)
                        );
                    }
                } else {
                    System.out.println(SudokuPrintColour.ANSI_RED);
                    System.out.println("Sorry this field is occupied pls choose another one");
                    System.out.println(SudokuPrintColour.ANSI_RESET);
                }
            }
        } while (!sudokuSolver.isSolve(board));
        display(board);
    }

    private boolean isEnd(String string) {
        switch (string) {
            case "sudoku":
                canBeSolved();
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
            occupiedFields.add(String.valueOf(row) + String.valueOf(col));
            System.out.println(SudokuPrintColour.ANSI_GREEN);
            System.out.println("Input in row: " + row + " col: " + col + " was input number: " + number + "");
            System.out.println(SudokuPrintColour.ANSI_RESET);
        } else {
            if (!findNumberToInput(row, col)) {
                hint();
            }
        }
    }

    private boolean findNumberToInput(String row, String col) {
        boolean answer = false;
        int number = 1;
        while (number <= 9 || !answer) {
            if (sudokuSolver.isOk(
                    Integer.parseInt(row),
                    Integer.parseInt(col),
                    number
            )
                    ) {
                board[Integer.parseInt(row)][Integer.parseInt(col)] = number;
                answer = true;
            } else {
                number++;
            }
        }
        return answer;
    }

    private void canBeSolved() {
        sudokuSolver.isSolve(board);
        if (!sudokuSolver.isSolve(board)) {
            for (int row = 0; row < 9; row++) {
                for (int col = 0; col < 9; col++) {
                    if (!occupiedFields.contains(
                            String.valueOf(row) + String.valueOf(col))) {
                        board[row][col] = 0;
                    }
                }
            }
        }
        sudokuSolver.solve();
        if (!sudokuSolver.isSolve(board)) {
            canBeSolved();
        }
    }
}

