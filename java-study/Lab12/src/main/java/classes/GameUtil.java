package classes;

import classes.players.HumanoidPlayer;
import classes.players.Player;
import classes.players.BotPlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GameUtil {
    public static List<Integer> generateCardsSet(int cardsRange) {
        List<Integer> cards = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            int card = r.nextInt(cardsRange);
            cards.add(card);
        }
        Collections.shuffle(cards);
        return cards;
    }

    public static Game initGame(List<Integer> cards) throws JSMoveException {
        Game game = new Game(cards);
        Player player1 = new BotPlayer();
        Player player2 = new HumanoidPlayer(System.in);
        game.setPlayer1(player1);
        game.setPlayer2(player2);
        return game;
    }
}