import model.user.Player;
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

    }

}
