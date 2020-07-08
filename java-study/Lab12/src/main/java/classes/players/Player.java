package classes.players;

import classes.JSMoveException;
import classes.RoundResult;
import classes.PlayerMoveException;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    List<Integer> cards = new ArrayList<>();
    private int score = 0;
    private RoundResult lastRoundResult = RoundResult.TIE;

    public abstract int getNextMove(List<Integer> player2CardsLeft) throws PlayerMoveException, JSMoveException;

    public abstract int getLastMove();

    public abstract List<Integer> getAvailableCards();

    public abstract String getName();

    public void score(RoundResult result) {
        if (result == RoundResult.PLAYER_WON) {
            score += 2;
        }
        if (result == RoundResult.TIE)
            score++;
        lastRoundResult = result;
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }

    public RoundResult getLastRoundResult() {
        return lastRoundResult;
    }

    public abstract void setCards(List<Integer> cards);

    public String getFormattedCards() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ");
        for (Integer card : cards) {
            builder.append(card).append(" | ");
        }
        builder.delete(builder.length() - 3, builder.length() - 1).append("]");
        return builder.toString();
    }
}
