package ai;

import utils.Master;

import java.util.Random;

public class AI extends Master {

    public AI(int level) {

    }

    public String getGuess() {
        Random random = new Random();
        int x = random.nextInt(10) + 1;
        int y = random.nextInt(10) + 1;

        return numToLetter(x) + y;
    }
}
