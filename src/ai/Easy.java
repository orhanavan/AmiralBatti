package ai;

import utils.Master;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Easy extends Master implements AI {

    /**
     * Her defasında rastgele atış yapar.
     * Atışlar tekrarsızdır.
     * Atış yapılan bir noktaya tekrar atış yapılmaz.
     */
    private List<String> shootingPoints;

    public Easy() {
        shootingPoints = new ArrayList<>();
    }

    @Override
    public String getGuess() {

        while (true) {
            String point = createRandomPoint();

            if (!shootingPoints.contains(point)) {
                shootingPoints.add(point);
                return point;
            }
        }
    }

    @Override
    public void addShootResults(String hitAI, boolean resultBot) {
    }

    @Override
    public void removeAllDecisions() {
    }

    String createRandomPoint() {
        Random random = new Random();
        int x = random.nextInt(10) + 1;
        int y = random.nextInt(10) + 1;
        return numToLetter(x) + y;
    }

}
