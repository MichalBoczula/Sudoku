package sudoku.solver;

import com.google.common.primitives.Ints;

public class SudokuInputVerifier {
    private SudokuInput sudokuInput = new SudokuInput();

    public String verifyInput() {
        String input = sudokuInput.getNextString().trim();
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
        if(Ints.tryParse(input) != null){
            return true;
        } else {
            return false;
        }
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
            message();
            return "error";
        }
    }

    private void message(){
        System.out.println("You can choose number from 1 to 9 or choose one from this options" +
                "\nsudoku to solve puzzle" +
                "\nhint to got help" +
                "\nexit to end game");
    }
}
