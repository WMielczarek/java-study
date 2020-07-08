package main;

import classes.Game;
import classes.GameUtil;
import classes.JSMoveException;
import classes.PlayerMoveException;
import classes.players.BotPlayer;
import classes.players.HumanoidPlayer;

import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private Game game;

    public static void main(String[] args) {
        int cardsRange = 10;
        try {
            if (args.length > 0) {
                cardsRange = Integer.parseInt(args[0]);
            }
            new Main().run(cardsRange);
        } catch (NumberFormatException e) {
            System.out.println("Wrong argument set only number of cards");
        }
    }

    private void run(int cardsRange) {
        List<Integer> cards = GameUtil.generateCardsSet(cardsRange);
        try {
            game = GameUtil.initGame(cards);
        } catch (JSMoveException e) {
            System.out.println("Inic. error!");
        }
        while (!game.isGameFinished()) {
            try {
                checkUserInput();
                System.out.println("Round " + game.roundNumber + "!");
                game.nextTurn();
            } catch (PlayerMoveException e) {
                System.out.println(e.getMessage());

            }
        }
        game.printGameConclusion();
    }

    private void checkUserInput() {
        String input = pressEnter();
        switch (input) {
            case "m": {
                options();
                break;
            }
        }
    }

    private void options() {
        System.out.println("MENU:");
        System.out.println("1. Start from begin");
        System.out.println("2. Turn on/off cards view");
        System.out.println("3. Change player");
        String input = scan();
        switch (input) {
            case "1": {
                game.restart();
                System.out.println("Game reseted!");
                break;
            }
            case "2": {
                game.switchBoardDisplay();
                System.out.println("Turn of cards view!");
                break;
            }
            case "3": {
                switchPlayer();
                break;
            }
        }
    }

    private void switchPlayer() {
        System.out.println("1. Change player 1");
        System.out.println("2. Change player 2");
        String input = scan();
        switch (input) {
            case "1": {
                switchPlayer1();
                break;
            }
            case "2": {
                switchPlayer2();
                break;
            }
        }
    }

    private void switchPlayer1() {
        System.out.println("1. New Player Human");
        System.out.println("2. New Player BOT");
        String input = scan();
        switch (input) {
            case "1": {
                game.setPlayer1(new HumanoidPlayer(System.in));
                break;
            }
            case "2": {
                BotPlayer botPlayer = new BotPlayer();
                System.out.println("Name of file with algorithm: ");
                String scriptPath = scan();
                botPlayer.swapScript(scriptPath);
                game.setPlayer1(botPlayer);
                break;
            }
        }
    }

    private void switchPlayer2() {
        System.out.println("1. New Player");
        System.out.println("2. New Player BOT");
        String input = scan();
        switch (input) {
            case "1": {
                game.setPlayer2(new HumanoidPlayer(System.in));
                break;
            }
            case "2": {
                BotPlayer botPlayer = new BotPlayer();
                System.out.println("File name with ");
                String scriptPath = scan();
                botPlayer.swapScript(scriptPath);
                game.setPlayer2(botPlayer);
                break;
            }
        }
    }

    private String pressEnter() {
        System.out.println("Press M to turn on menu");
        return scan();
    }

    private String scan() {
        return scanner.nextLine().trim();
    }
}