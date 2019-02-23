package utils;

import model.ships.Ship;
import model.user.Player;

import javax.net.ssl.SNIHostName;
import java.util.List;
import java.util.Scanner;

public class Game extends Master {

    private String username;
    private int level;
    private String[][] screen1;
    private String[][] screen2;

    public Game() {
        selectLevel();
        screen1 = new String[11][11];
        screen2 = new String[11][11];
    }

    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public void startGame(Player player1, Player player2) {
        initScreen(screen1);
        initScreen(screen2);

        Scanner reader = new Scanner(System.in);
        addShipSign(screen1, player1.getShips());
        addShipSign(screen2, player2.getShips());

        String shootResult = "";

        while (true) {

            System.out.println("               YOU                ");
            System.out.println("──────────────────────────────────");
            printScreen(screen1, false);

            System.out.println("              ENEMY               ");
            System.out.println("──────────────────────────────────");
            printScreen(screen2, false);

            System.out.println(shootResult);
            System.out.println("Your turn.\nGuess the location of the Enemy's ship!");
            String hitPoint = reader.nextLine();
            shootResult = shoot(hitPoint, screen2);
            if (hitPoint.equals("kill"))
                break;
        }


    }

    private String shoot(String hitPoint, String[][] enemyScreen) {
        slowly("...");

        int x = letterToNum(hitPoint.substring(0, 1));
        int y = Integer.parseInt(hitPoint.substring(1, hitPoint.length()));

        if (enemyScreen[x][y].equals("░░░")) {
            enemyScreen[x][y] = " X ";
            return "Hit !";
        } else {
            enemyScreen[x][y] = " ○ ";
            return "Missed.";
        }

    }

    private void addShipSign(String[][] screen, List<Ship> ships) {
        for (Ship ship: ships) {

            for (String coordinate: ship.getCoordinates()) {
                String x = coordinate.substring(0,1);
                String y = coordinate.substring(1,coordinate.length());

                screen[letterToNum(x)][Integer.parseInt(y)] = "░░░";
            }

        }
    }

    private void selectLevel() {

        System.out.println("Welcome to the Battleships!");

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter your name:");
        username = reader.nextLine();

        label:
        while (true) {

            System.out.println("\n(1) Easy");
            System.out.println("(2) Normal");
            System.out.println("(3) Hard");
            System.out.println("\nSelect level:");

            String level_input = reader.nextLine();

            switch (level_input) {
                case "1":
                    level = 1;
                    break label;
                case "2":
                    level = 2;
                    break label;
                case "3":
                    level = 3;
                    break label;
                default:
                    System.out.println("\n\nWrong level choice try again.");
                    break;
            }
        }
    }
}
