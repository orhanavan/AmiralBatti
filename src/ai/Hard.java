package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

                decisions.remove(point); // atış yaptıktan sonra onu önerilerden sil
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

    /** tüm karar seçeneklerinden 1 tanesini seçer */
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

            // ATIŞ ÖNERİLENLER İÇERİSİNDE DEĞİLSE
            if (!decisions.contains(coordinate)){
                if (isRight(firstShoot, coordinate)) {
                    removeTopBottom(firstShoot);
                    addLeft(x, y);


                } else if (isLeft(firstShoot, coordinate)) {
                    removeTopBottom(firstShoot);
                    addRight(x, y);

                } else if (isTop(firstShoot, coordinate)) {
                    removeLeftRight(firstShoot);
                    addBottom(x, y);

                } else if (isBottom(firstShoot, coordinate)) {
                    removeLeftRight(firstShoot);
                    addTop(x, y);

                } else {
                    addLeft(x, y);
                    addRight(x, y);
                    addTop(x, y);
                    addBottom(x, y);
                }
            }

            firstShoot = coordinate;
        }

        for (String s: decisions) {
            System.out.println("DECISIONS: " + s);
        }
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

    private boolean isLeft(String original, String shoot) {
        String x = shoot.substring(0, 1);
        String y = shoot.substring(1, shoot.length());

        if (!y.equals("1")){
            String left = x + (Integer.parseInt(y) - 1);
            return original.equals(left);
        }

        return false;
    }

    private boolean isRight(String original, String shoot) {
        String x = shoot.substring(0, 1);
        String y = shoot.substring(1, shoot.length());

        if (!y.equals("10")) {
            String right = x + (Integer.parseInt(y) + 1);
            return original.equals(right);
        }

        return false;
    }

    private boolean isTop(String original, String shoot) {
        String x = shoot.substring(0, 1);
        String y = shoot.substring(1, shoot.length());

        if (!x.equals("A")) {
            String top = numToLetter(letterToNum(x) - 1) + y;
            return original.equals(top);
        }
        return false;
    }

    private boolean isBottom(String original, String shoot) {
        String x = shoot.substring(0, 1);
        String y = shoot.substring(1, shoot.length());

        if (!x.equals("J")) {
            String bottom = numToLetter(letterToNum(x) + 1) + y;

            return original.equals(bottom);
        }

        return false;
    }

    private void removeLeftRight(String point) {
        String x = point.substring(0, 1);
        String y = point.substring(1, point.length());

        if (!y.equals("1")){
            String left = x + (Integer.parseInt(y) - 1);
            decisions.remove(left);
        }

        if (!y.equals("10")) {
            String right = x + (Integer.parseInt(y) + 1);
            decisions.remove(right);
        }
    }

    private void removeTopBottom(String point) {
        String x = point.substring(0, 1);
        String y = point.substring(1, point.length());

        if (!x.equals("A")) {
            String top = numToLetter(letterToNum(x) - 1) + y;
            decisions.remove(top);
        }

        if (!x.equals("J")) {
            String bottom = numToLetter(letterToNum(x) + 1) + y;
            decisions.remove(bottom);
        }
    }
}
