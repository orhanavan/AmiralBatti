package ai;

import utils.Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Easy extends Master {

    /**
     * Her defasında rastgele atış yapar.
     * Atış yaptığı bir noktaya tekrar atış yapmaz.
     */
    private List<String> shootingPoints;

    public Easy() {
        shootingPoints = new ArrayList<>();
    }

    public String getGuess() {

        while (true) {
            String point = createRandomPoint();

            if (!shootingPoints.contains(point)) {
                shootingPoints.add(point);
                return point;
            }
        }
    }

    public String createRandomPoint() {
        Random random = new Random();
        int x = random.nextInt(10) + 1;
        int y = random.nextInt(10) + 1;
        return numToLetter(x) + y;
    }

    public void addShootResults(String coordinate, boolean result) {
    }

}
