package utils;

public class Master {

    public static String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    public void initScreen(String[][] screen) {
        for (int i = 0; i < screen.length; i++) {

            for (int j = 0; j < screen.length; j++) {

                if (i == 0) {
                    if (j == 0)
                        screen[i][j] = "   ";
                    else
                        screen[i][j] = " " + String.valueOf(j) + " ";
                } else if (j == 0) {
                    screen[i][j] = " " + letters[i - 1] + " ";
                } else {
                    screen[i][j] = " . ";
                }
            }
        }
    }

    public void printScreen(String[][] screen, boolean space) {

        if (space)
            for (int i = 0; i < 50; i++) System.out.println();

        for (String[] aScreen : screen) {
            for (int j = 0; j < screen.length; j++) {
                System.out.print(aScreen[j]);
            }
            System.out.println();
        }
    }

    public int letterToNum(String s) {
        switch (s) {
            case "A":
                return 1;
            case "B":
                return 2;
            case "C":
                return 3;
            case "D":
                return 4;
            case "E":
                return 5;
            case "F":
                return 6;
            case "G":
                return 7;
            case "H":
                return 8;
            case "I":
                return 9;
            case "J":
                return 10;
            default:
                return 11;
        }
    }

    public String numToLetter(int i) {
        return letters[i -1];
    }

    public void slowly(String text) {
        String[] txt = text.split("");
        for (String aTxt : txt) {
            System.out.print(aTxt);
            sleepMe(300);
        }

        System.out.println();
    }

    public void sleepMe(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
