package utils;

import ai.AI;
import ai.Easy;
import ai.Hard;
import ai.Medium;
import model.ships.Ship;
import model.user.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Game extends Master {

    private String username;
    private AI ai;
    private String[][] screen1, screen2;
    private static String[][] screen3;

    private Map<String, Ship> shipCoordinates1, shipCoordinates2;

    public Game() {
        selectLevel();
        screen1 = new String[11][11];
        screen2 = new String[11][11];
        screen3 = new String[11][11];
        shipCoordinates1 = new HashMap<>();
        shipCoordinates2 = new HashMap<>();
    }

    public String getUsername() {
        return username;
    }

    public void startGame(Player player1, Player player2) {
        initScreen(screen1);
        initScreen(screen2);
        initScreen(screen3);

        Scanner reader = new Scanner(System.in);
        addShipSign(screen1, player1.getShips(), (HashMap) shipCoordinates1);
        addShipSign(screen2, player2.getShips(), (HashMap) shipCoordinates2);

        printBattlefield();
        gameLoop:
        while (true) {

            while (true) {

                System.out.println("Your turn.\nGuess the location of the Enemy's ship!");
                String hitUser = reader.nextLine();

                boolean resultUser = shoot(hitUser, screen2, true);
                printShootResult("You", resultUser);

                if (resultUser) {
                    boolean destroyedUser = isDestroyed(hitUser, (HashMap) shipCoordinates2, screen2);
                    if (isAllShipsDestroyed(player2)) {
                        Strings.youWin();
                        break gameLoop;
                    }
                } else {
                    break;
                }
            }

            while (true) {

                System.out.println("Rival's turn.");
                String hitAI = ai.getGuess();
                sleepMe(2000);
                slowly(hitAI);

                boolean resultBot = shoot(hitAI, screen1, false);
                printShootResult("Rival", resultBot);
                ai.addShootResults(hitAI, resultBot);

                if (resultBot) {
                    boolean destroyedBot = isDestroyed(hitAI,  (HashMap) shipCoordinates1, screen1);
                    if (destroyedBot)
                        ai.removeAllDecisions();

                    if (isAllShipsDestroyed(player1)) {
                        Strings.youLose();
                        break gameLoop;
                    }
                } else {
                    break;
                }
            }

        }
    }

    private boolean isAllShipsDestroyed(Player player) {
        List<Ship> ships = player.getShips();
        for (Ship ship: ships) {

            if (ship.isAlive())
                return false;
        }

        return true;
    }
    private boolean isDestroyed(String point, HashMap shipCoordinates, String[][] screen) {

        Ship ship = (Ship) shipCoordinates.get(point);
        String[] coordinates = ship.getCoordinates();

        for (String s: coordinates) {

            String x = s.substring(0, 1);
            String y = s.substring(1, s.length());

            if (!screen[letterToNum(x)][Integer.parseInt(y)].equals(" X "))
                return false;
        }

        System.out.println(ship.getName() + " destroyed!");
        ship.setAlive(false);
        return true;
    }

    private void printShootResult(String who, boolean result) {
        if (result)
            System.out.println(who + " Hit !");
        else
            System.out.println(who + " Missed.");
    }

    private void printBattlefield() {
        for (int i = 0 ; i < 50; i++) System.out.println();

        System.out.println("               YOU                ");
        System.out.println("──────────────────────────────────");
        printScreen(screen1, false);

        System.out.println("              ENEMY               ");
        System.out.println("──────────────────────────────────");
        printScreen(screen3, false);
    }

    private boolean shoot(String hitPoint, String[][] enemyScreen, boolean who) {
        slowly("...");

        int x = letterToNum(hitPoint.substring(0, 1));
        int y = Integer.parseInt(hitPoint.substring(1, hitPoint.length()));

        if (enemyScreen[x][y].equals("░░░")) {
            enemyScreen[x][y] = " X ";
            if (who)
                screen3[x][y] = " X ";
            printBattlefield();
            return true;
        } else {
            enemyScreen[x][y] = " ○ ";
            if (who)
            screen3[x][y] = " ○ ";
            printBattlefield();
            return false;
        }

    }

    private void addShipSign(String[][] screen, List<Ship> ships, HashMap shipCoordinates) {
        for (Ship ship: ships) {

            for (String coordinate: ship.getCoordinates()) {
                String x = coordinate.substring(0,1);
                String y = coordinate.substring(1,coordinate.length());

                screen[letterToNum(x)][Integer.parseInt(y)] = "░░░";
                shipCoordinates.put(coordinate, ship);
            }
        }
    }

    private void selectLevel() {

        Strings.splashScreen();
        System.out.println("Welcome to the Battleships!");

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter your name: ");
        username = reader.nextLine();

        System.out.println("\u001B[33m");
        slowly("Admiral " + username);
        System.out.println("\u001B[0m");

        label:
        while (true) {

            System.out.println("(1) Easy");
            System.out.println("(2) Normal");
            System.out.println("(3) Hard");
            System.out.println("\nSelect level:");

            String level_input = reader.nextLine();

            switch (level_input) {
                case "1":
                    ai = new Easy();
                    break label;
                case "2":
                    ai = new Medium();
                    break label;
                case "3":
                    ai = new Hard();
                    break label;
                default:
                    System.out.println("\n\nWrong level choice try again.");
                    break;
            }
        }
    }
}
