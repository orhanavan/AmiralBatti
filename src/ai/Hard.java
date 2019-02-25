package ai;

import java.util.ArrayList;
import java.util.List;

public class Hard extends Medium implements AI {

    private List<String> shootingPoints;
    private List<String> decisions;
    private String firstShoot;

    public Hard() {
        shootingPoints = new ArrayList<>();
        decisions = new ArrayList<>();
    }


    @Override
    public String getGuess() {
        while (true) {
            String point;

            if (decisions.size() != 0) {
                point = makeDecision(decisions);

                decisions.remove(point);
                System.out.println("DECISION WORKED");
            }
            else {
                System.out.println("RANDOM WORKED");
                point = createRandomPoint();
                firstShoot = point;
            }

            if (!shootingPoints.contains(point)) {
                shootingPoints.add(point);
                return point;
            }
        }
    }
}
