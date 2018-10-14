package sudoku.solver;

public class SudokuInputVerifier {
    private SudokuInput sudokuInput = new SudokuInput();

    public String verifyInput() {
        System.out.println("input number from 1 to 9 or " +
                "\nhint for help " +
                "\nsudoku for solve puzzle " +
                "\nexit for end game");
        String input = sudokuInput.getNextString();
        String answer = inputController(input);
        return answer != "error" ?
                answer :
                verifyInput();
    }

    private int verifyNumberIsSmallerThanTen(String input) {
        System.out.println("input number from 1 to 9");
        int number = Integer.parseInt(input);
        return number < 10 ?
                number :
                verifyNumberIsSmallerThanTen(input);
    }

    private boolean verifyInputIsNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private String inputController(String input) {
        if (verifyInputIsNumber(input)) {
            return String.valueOf(verifyNumberIsSmallerThanTen(input));
        } else {
            return verifyString(input);
        }
    }

    private String verifyString(String input) {
        if (input.trim().toLowerCase().equals("hint")) {
            return "hint";
        } else if (input.trim().toLowerCase().equals("sudoku")) {
            return "sudoku";
        } else if (input.trim().toLowerCase().equals("exit")) {
            return "exit";
        } else {
            return "error";
        }
    }

}
