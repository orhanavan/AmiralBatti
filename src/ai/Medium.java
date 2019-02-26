package ai;

import java.util.*;

public class Medium extends Easy implements AI {

    private List<String> shootingPoints;
    private List<String> decisions;

    /**
     * Easy gibi her defasında rastgele ve tekrasız atış yapar.
     * Bir isabetli atış kaydettiğinde, atışın etrafında 4 yönde tekrar atış yapar.
     */
    public Medium() {
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
            }

            if (!shootingPoints.contains(point)) {
                shootingPoints.add(point);
                return point;
            }
        }
    }

    public String makeDecision(List<String> decisions) {
        Random random = new Random();
        int i = random.nextInt(decisions.size());
        return decisions.get(i);
    }

    @Override
    public void addShootResults(String coordinate, boolean result) {
        if (result) {

            String x = coordinate.substring(0, 1);
            String y = coordinate.substring(1, coordinate.length());

            if (!decisions.contains(coordinate)){
                addLeft(x, y);
                addRight(x, y);
                addTop(x, y);
                addBottom(x, y);
            }
        }
    }

    @Override
    public void removeAllDecisions() {
    }

    private void addLeft(String x, String y) {
        if (!y.equals("1")) {
            String point = x + (Integer.parseInt(y) - 1);
            if (!shootingPoints.contains(point))
                decisions.add(point);
        }
    }

    private void addRight(String x, String y) {
        if (!y.equals("10")) {
            String point = x + (Integer.parseInt(y) + 1);
            if (!shootingPoints.contains(point))
                decisions.add(point);
        }
    }

    private void addTop(String x, String y) {
        if (!x.equals("A")) {
            String point = numToLetter(letterToNum(x) - 1) + y;
            if (!shootingPoints.contains(point))
                decisions.add(point);
        }
    }

    private void addBottom(String x, String y) {
        if (!x.equals("J")) {
            String point = numToLetter(letterToNum(x) + 1) + y;
            if (!shootingPoints.contains(point))
                decisions.add(point);
        }
    }

}
