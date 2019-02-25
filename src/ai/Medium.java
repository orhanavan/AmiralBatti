package ai;

import java.util.*;

public class Medium extends Easy implements AI {

    private List<String> shootingPoints;
    private List<String> decisions;
    private String firstShoot;

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

            /*
            * isabetli bir şut olduğunda ve bu şut karar listesi içerisindeyse
            * simetriğinin bir dahaki atışta yapılabilmesi için
            *
            * ilgisiz olan atışların silinmesi gerekir
            *
            * */
            if (decisions.contains(coordinate)){
                if (isRightLeft(firstShoot, coordinate)) {
                    removeTopBottom(firstShoot);
                } else if (isTopBottom(firstShoot, coordinate)) {
                    removeLeftRight(firstShoot);
                }
            } else {
                // left
                if (!y.equals("1")) {
                    String point = x + (Integer.parseInt(y) - 1);
                    if (!shootingPoints.contains(point))
                        decisions.add(point);
                }
                // right
                if (!y.equals("10")) {
                    String point = x + (Integer.parseInt(y) + 1);
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
            }

        } else  {

            if (decisions.contains(coordinate)) {
//                int index = decisions.indexOf(coordinate);
//                decisions.remove(index);

                if (isRightLeft(firstShoot, coordinate))
                    removeTopBottom(coordinate);

                if (isTopBottom(firstShoot, coordinate))
                    removeLeftRight(coordinate);
                /*
                * kararlar içerinden seçilen bir atış başarısız olduğunda
                * onun simetriğindeki atışın da silinmesi gerekir
                * */
            }
        }

        for (String s: decisions) {
            System.out.println("DECISIONS: " + s);
        }
    }

    private boolean isRightLeft(String original, String shoot) {
        String x = shoot.substring(0, 1);
        String y = shoot.substring(1, shoot.length());

        if (!y.equals("1")){
            String left = x + (Integer.parseInt(y) - 1);
            if (original.equals(left))
                return true;
        }

        if (!y.equals("10")) {
            String right = x + (Integer.parseInt(y) + 1);
            if (original.equals(right))
                return true;
        }

        return false;
    }

    private boolean isTopBottom(String original, String shoot) {
        String x = shoot.substring(0, 1);
        String y = shoot.substring(1, shoot.length());

        if (!x.equals("A")) {
            String top = numToLetter(letterToNum(x) - 1) + y;
            if (original.equals(top))
                return true;
        }

        if (!x.equals("J")) {
            String bottom = numToLetter(letterToNum(x) + 1) + y;

            if (original.equals(bottom))
                return true;
        }

        return false;
    }

    private void removeLeftRight(String point) {
        String x = point.substring(0, 1);
        String y = point.substring(1, point.length());

        System.out.println("REMOVE LEFT RIGHT");
        // remove old left decision
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

        System.out.println("REMOVE TOP BOTTOM");
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
