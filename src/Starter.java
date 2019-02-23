import model.user.Player;
import model.ships.Ship;
import utils.AddShip;
import utils.Game;

public class Starter {

    public static void main(String[] args) {

        Game game = new Game();
        AddShip addShip = new AddShip(false);
        Player player = new Player(game.getUsername(), addShip.getMyShips());

        AddShip botShips = new AddShip(true);
        Player bot = new Player("bot", botShips.getMyShips());

        game.startGame(player, bot);

//        // userı yazdır
//        System.out.println(player.getName());
//        for (Ship ship: player.getShips()) {
//
//            System.out.println(ship.getLength());
//
//            for (String s: ship.getCoordinates()) {
//                System.out.println(s);
//            }
//            System.out.println("---------------------------------");
//        }

//        // botu yazdır
//        System.out.println(bot.getName());
//        for (Ship ship: bot.getShips()) {
//
//            System.out.println(ship.getLength());
//
//            for (String s: ship.getCoordinates()) {
//                System.out.println(s);
//            }
//            System.out.println("---------------------------------");
//        }


    }


}
