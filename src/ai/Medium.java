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
            System.out.println("ATIŞ BAŞARILI");

            String x = coordinate.substring(0, 1);
            String y = coordinate.substring(1, coordinate.length());

            // ATIŞ ÖNERİLENLER İÇERİSİNDE DEĞİLSE
            if (!decisions.contains(coordinate)){
                // 4 NOKTASINDADA OLABİLİR

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
            } else {
                // atış önerilenler içerisindeyse
            }

        } else {
            System.out.println("ATIŞ BAŞARISIZ");
        }

        for (String s: decisions) {
            System.out.println("DECISIONS: " + s);
        }
    }


}
