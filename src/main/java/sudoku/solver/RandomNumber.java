package sudoku.solver;

import java.util.Random;

public class RandomNumber {
    private final Random random;

    public RandomNumber() {
        this.random = new Random();
    }

    public int getNextInt(){
        return random.nextInt(9)+1;
    }

}
