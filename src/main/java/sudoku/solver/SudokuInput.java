package sudoku.solver;

import java.util.Scanner;

public class SudokuInput {
    private Scanner scanner = new Scanner(System.in);

    public String getNextString(){
        return scanner.nextLine();
    }

}
