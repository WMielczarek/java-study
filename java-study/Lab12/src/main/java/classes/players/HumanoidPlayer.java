package classes.players;

import classes.PlayerMoveException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanoidPlayer extends Player {
    private Scanner scanner;
    private int lastMove;

    public HumanoidPlayer(InputStream input) {
        scanner = new Scanner(input);
    }

    @Override
    public int getNextMove(List<Integer> player2CardsLeft) throws PlayerMoveException {
        System.out.println("Your cards: ");
        System.out.println(getFormattedCards());
        System.out.println("Type card index");
        int selectedCardIndex = parsePlayerInput(scanner.nextLine());
        try {
            lastMove = cards.remove(selectedCardIndex);
            return lastMove;
        } catch (IndexOutOfBoundsException e) {
            throw new PlayerMoveException("Wrong index");
        }
    }

    @Override
    public List<Integer> getAvailableCards() {
        return new ArrayList<>(cards);
    }

    @Override
    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    @Override
    public String getName() {
        return "Human";
    }

    @Override
    public int getLastMove() {
        return lastMove;
    }

    private int parsePlayerInput(String s) {
        int selectedIndex = -1;
        try {
            selectedIndex = Integer.parseInt(s);
        } catch (NumberFormatException ignored) {
        }
        return selectedIndex;
    }


}
