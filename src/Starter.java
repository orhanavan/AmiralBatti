import ai.Medium;
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

//        Medium medium = new Medium();
//
//        medium.addShootResults("J10", true);
//        medium.getGuess();

    }


}
