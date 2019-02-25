package ai;

import java.util.*;

public class Medium extends Easy {

    private List<String> shootingPoints;
    private List<String> shootResults;
    private List<String> decisions;

    public Medium() {
        shootingPoints = new ArrayList<>();
        shootResults = new ArrayList<>();
        decisions = new ArrayList<>();
    }

    @Override
    public String getGuess() {
        while (true) {
            String point;

            if (decisions.size() != 0) {
                point = makeDecision();
                System.out.println("DECISION WORKED");
            }
            else {
                System.out.println("RANDOM WORKED");
                point = createRandomPoint();
            }

            if (!shootingPoints.contains(point)) {
                shootingPoints.add(point);
                return point;
            }
        }
    }

    public String makeDecision() {
        Random random = new Random();
        int i = random.nextInt(decisions.size());
        return decisions.get(i);
    }

    @Override
    public void addShootResults(String coordinate, boolean result) {
        if (result) {
            String x = coordinate.substring(0, 1);
            String y = coordinate.substring(1, coordinate.length());

            // right
            if (!y.equals("10")) {
                String point = x + (Integer.parseInt(y) + 1);
                if (!shootingPoints.contains(point))
                    decisions.add(point);
            }
            // left
            if (!y.equals("1")) {
                String point = x + (Integer.parseInt(y) - 1);
                if (!shootingPoints.contains(point))
                    decisions.add(point);
            }
            // top
            if (!x.equals("A")) {
                String point = numToLetter(letterToNum(x) - 1) + y;
                if (!shootingPoints.contains(point))
                    decisions.add(point);
            }
            // bottom
            if (!x.equals("J")) {
                String point = numToLetter(letterToNum(x) + 1) + y;
                if (!shootingPoints.contains(point))
                    decisions.add(point);
            }
        } else  {
            if (decisions.contains(coordinate)) {
                int index = decisions.indexOf(coordinate);
                decisions.remove(index);
            }
        }

        for (String s: decisions) {
            System.out.println("DECISIONS: " + s);
        }
    }
}
