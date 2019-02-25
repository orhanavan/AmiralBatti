import ai.Medium;
import model.user.Player;
import model.ships.Ship;
import utils.AddShip;
import utils.Game;

import java.util.HashMap;
import java.util.Map;

public class Starter {

    public static void main(String[] args) {

        Game game = new Game();
        AddShip addShip = new AddShip(false);
        Player player = new Player(game.getUsername(), addShip.getMyShips());

        AddShip botShips = new AddShip(true);
        Player bot = new Player("bot", botShips.getMyShips());

        game.startGame(player, bot);

//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("orhan33", "1");
//        hashMap.put("orhan32", "2");
//        hashMap.put("orhan33", "3");
//        hashMap.put("orhan33", "4");
//
//
//        for(Map.Entry<String, String> entry : hashMap.entrySet()) {
//            String key = entry.getKey();
//            String  value = entry.getValue();
//
//            System.out.println(key +"  " + value);
//        }

    }


}
