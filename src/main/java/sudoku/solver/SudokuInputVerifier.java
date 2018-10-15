package sudoku.solver;

import com.google.common.primitives.Ints;

public class SudokuInputVerifier {
    private SudokuInput sudokuInput = new SudokuInput();

    public String verifyInput() {
        String input = sudokuInput.getNextString().trim();
        String answer = inputController(input);
        return !answer.equals("error") && !answer.equals("111") ?
                answer :
                verifyInput();
    }

    private int verifyNumberIsSmallerThanTen(String input) {
        System.out.println("input number from 1 to 9");
        int number = Integer.parseInt(input);
        if (number > 0 && number < 10) {
            return number;
        } else {
            return 111;
        }
    }

    private boolean verifyInputIsNumber(String input) {
        return Ints.tryParse(input) != null;
    }

    private String inputController(String input) {
        if (verifyInputIsNumber(input)) {
            return String.valueOf(verifyNumberIsSmallerThanTen(input));
        } else {
            return verifyString(input);
        }
    }

    private String verifyString(String input) {
        switch (input.trim().toLowerCase()) {
            case "hint":
                return "hint";
            case "sudoku":
                return "sudoku";
            case "exit":
                return "exit";
            default:
                message();
                return "error";
        }
    }

    private void message() {
        System.out.println("You can choose number from 1 to 9 or choose one from this options" +
                "\nsudoku to solve puzzle" +
                "\nhint to got help" +
                "\nexit to end game");
    }
}
