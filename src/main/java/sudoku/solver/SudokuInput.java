package sudoku.solver;

import java.util.Scanner;

public class SudokuInput {
    private Scanner scanner = new Scanner(System.in);

    public int getNextInt(){
        return scanner.nextInt();
    }
}
