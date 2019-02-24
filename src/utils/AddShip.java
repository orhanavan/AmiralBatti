package utils;

import model.ships.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AddShip extends Master {

    private int NUM_BOAT = 0, NUM_MINESWEEPER = 0, NUM_FRIGATE = 0, NUM_DESTROYER = 1;
    private String[][] screen = new String[11][11];
    private List<Ship> myShips;

    public List<Ship> getMyShips() {
        return myShips;
    }

    // default constructor
    public AddShip(boolean auto) {
        myShips = new ArrayList<>();
        initScreen(screen);

        if (auto) {
            autoInitShips();
        } else {
            autoManuelSelect();
        }
    }

    private void autoManuelSelect() {
        Scanner reader = new Scanner(System.in);

        label:
        while (true) {
            System.out.println();
            System.out.println("(0) Manual");
            System.out.println("(1) Automatic");
            System.out.println("\nSelect fitting type of ships:");

            String select = reader.nextLine();
            switch (select) {
                case "0":
                    initShips();
                    break label;
                case "1":

                    while (true) {
                        autoInitShips();
                        printScreen(screen, true);

                        System.out.println("(0) OK");
                        System.out.println("(1) Refresh");
                        String isOK = reader.nextLine();

                        if (isOK.equals("0")) {
                            break label;
                        } else if (isOK.equals("1")) {
                            initScreen(screen);
                            myShips.clear();
                        }

                    }

                default:
                    System.out.println("Wrong fit type. Try again.");
                    break;
            }
        }
    }

    private void autoInitShips() {
        for (int i = 0; i < NUM_BOAT; i++) {
            myShips.add(new Boat(setRandomCoordinates(1)));
        }

        for (int i = 0; i < NUM_MINESWEEPER; i++) {
            myShips.add(new Minesweeper(setRandomCoordinates(2)));
        }

        for (int i = 0; i < NUM_FRIGATE; i++) {
            myShips.add(new Frigate(setRandomCoordinates(3)));
        }

        for (int i = 0; i < NUM_DESTROYER; i++) {
            myShips.add(new Destroyer(setRandomCoordinates(4)));
        }
    }

    private void initShips() {

        while (true) {
            printScreen(screen, true);

            System.out.println();
            System.out.println("Key │ Class       │ Length │ Unit Num ");
            System.out.println("────┼─────────────┼────────┼──────────");
            System.out.println("(B) │ Boat        │   1    │     " + NUM_BOAT + "    ");
            System.out.println("(M) │ Minesweeper │   2    │     " + NUM_MINESWEEPER + "    ");
            System.out.println("(F) │ Frigate     │   3    │     " + NUM_FRIGATE + "    ");
            System.out.println("(D) │ Destroyer   │   4    │     " + NUM_DESTROYER + "    ");

            if (NUM_BOAT == 0 && NUM_MINESWEEPER == 0 && NUM_FRIGATE == 0 && NUM_DESTROYER == 0)
                break;
            System.out.println("\nSelect ship type:");
            Scanner reader = new Scanner(System.in);
            String type = reader.nextLine();
            switch (type) {
                case "B":
                    if (NUM_BOAT > 0) {
                        myShips.add(new Boat(setCoordinates(1)));
                        NUM_BOAT--;
                    }
                    break;
                case "M":
                    if (NUM_MINESWEEPER > 0) {
                        myShips.add(new Minesweeper(setCoordinates(2)));
                        NUM_MINESWEEPER--;
                    }
                    break;
                case "F":
                    if (NUM_FRIGATE > 0) {
                        myShips.add(new Frigate(setCoordinates(3)));
                        NUM_FRIGATE--;
                    }
                    break;
                case "D":
                    if (NUM_DESTROYER > 0) {
                        myShips.add(new Destroyer(setCoordinates(4)));
                        NUM_DESTROYER--;
                    }
                    break;
                default:
                    System.out.println("Wrong type!");
            }
        }
    }

    private String[] setRandomCoordinates(int length) {
        String alignment;
        int x, y;

        while (true) {
            Random random = new Random();

            int a1 = random.nextInt(2);
            if (a1 == 0) alignment = "V";
            else alignment = "H";

            x = random.nextInt(10) + 1;
            y = random.nextInt(10) + 1;

            boolean violate = isViolate(alignment, length, x, y);
            if (!violate) break;
        }

        return addShip(alignment, length, x, y);
    }

    private String[] setCoordinates(int length) {
        String alignment, coordinate, x, y;

        while (true) {
            Scanner reader = new Scanner(System.in);

            System.out.println("Enter alignment and starting point:");
            String[] info = reader.nextLine().split(" ");

            alignment = info[0];
            coordinate = info[1];

            x = coordinate.substring(0, 1);
            y = coordinate.substring(1, coordinate.length());

            boolean violate = isViolate(alignment, length, letterToNum(x), Integer.parseInt(y));
            if (!violate) break;
            else System.out.println("Wrong coordinate!");
        }

        return addShip(alignment, length, letterToNum(x), Integer.parseInt(y));
    }

    private boolean isViolate(String alignment, int length, int x, int y) {
        String[] coordinates = new String[length];

        if (alignment.equals("V")) {
            try {
                for (int i = 0; i < length; i++) {
                    coordinates[i] = coordinates[i];
                    screen[x][y] = screen[x][y];
                    if (screen[x][y].equals("░░░"))
                        return true;
                    x++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return true;
            }

        } else if (alignment.equals("H")) {
            try {
                for (int i = 0; i < length; i++) {
                    coordinates[i] = coordinates[i];
                    screen[x][y] = screen[x][y];
                    if (screen[x][y].equals("░░░"))
                        return true;
                    y++;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return true;
            }

        }

        return false;
    }

    private String[] addShip(String alignment, int length, int x, int y) {
        String[] coordinates = new String[length];

        if (alignment.equals("V")) {
            for (int i = 0; i < length; i++) {
                coordinates[i] = "" + numToLetter(x) + y;
                screen[x++][y] = "░░░";
            }
        } else if (alignment.equals("H")) {
            for (int i = 0; i < length; i++) {
                coordinates[i] = "" + numToLetter(x) + y;
                screen[x][y++] = "░░░";
            }
        }

        return coordinates;
    }
}
