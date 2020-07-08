package classes;

import classes.players.HumanoidPlayer;
import classes.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public int roundNumber = 1;
    private List<Integer> initialCards;
    private Printer printer = new Printer();
    private Player player1 = new HumanoidPlayer(System.in);
    private Player player2 = new HumanoidPlayer(System.in);
    private boolean gameFinished = false;
    private boolean boardDisplayEnabled = true;

    public Game(List<Integer> initialCards) {
        this.initialCards = initialCards;
        player1.setCards(new ArrayList<>(initialCards));
        player2.setCards(new ArrayList<>(initialCards));
    }

    public void nextTurn() throws PlayerMoveException {
        if (boardDisplayEnabled)
            printer.printBoard();
        evaluateMoves();
        printer.printRoundConclusion();
        checkEndCondition();
    }

    private void evaluateMoves() throws PlayerMoveException {
        try {
            List<Integer> player1Cards = player1.getAvailableCards();
            List<Integer> player2Cards = player2.getAvailableCards();
            int player1Card = player1.getNextMove(player2Cards);
            int player2Card = player2.getNextMove(player1Cards);
            RoundResult player1Result = checkRoundResult(player1Card, player2Card);
            RoundResult player2Result = checkRoundResult(player2Card, player1Card);
            player1.score(player1Result);
            player2.score(player2Result);
        } catch (PlayerMoveException e) {
            gameFinished = true;
            throw new PlayerMoveException(e.getMessage());
        }
    }

    private RoundResult checkRoundResult(int player1Card, int player2Card) {
        if (player1Card > player2Card)
            return RoundResult.PLAYER_WON;
        else if (player2Card > player1Card)
            return RoundResult.PLAYER_LOST;
        else return RoundResult.TIE;
    }

    private void checkEndCondition() {
        if (player1.getAvailableCards().size() == 0
                && player2.getAvailableCards().size() == 0) {
            gameFinished = true;

        }
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setPlayer1(Player player) {
        player.setCards(this.player1.getAvailableCards());
        this.player1 = player;
    }

    public void setPlayer2(Player player) {
        player.setCards(this.player2.getAvailableCards());
        this.player2 = player;
    }

    public void printGameConclusion() {
        printer.printGameConclusion();
    }

    public void restart() {
        player1.setCards(initialCards);
        player2.setCards(initialCards);
        player1.resetScore();
        player2.resetScore();
        roundNumber = 1;
    }

    public void switchBoardDisplay() {
        boardDisplayEnabled = !boardDisplayEnabled;
    }

    private class Printer {
        private void printBoard() {
            System.out.println("*****************************");
            System.out.println(player1.getFormattedCards() + " " + player1.getName());
            System.out.println();
            System.out.println(player2.getFormattedCards() + " " + player2.getName());
            System.out.println("*****************************");
        }

        private void printRoundConclusion() {
            System.out.println("Player 1 turn: " + player1.getLastMove());
            System.out.println("Player 2 turn: " + player2.getLastMove());

            if (player1.getLastRoundResult() == RoundResult.PLAYER_WON) {
                System.out.println("Player 1 won!");
            } else if (player2.getLastRoundResult() == RoundResult.PLAYER_WON) {
                System.out.println("Player 2 won!");
            } else {
                System.out.println("draw!");
            }

            System.out.println("Player 1: " + player1.getScore() + " points!");
            System.out.println("Player 2: " + player2.getScore() + " points!");
            System.out.println();
            System.out.println();
        }

        private void printGameConclusion() {
            if (player1.getScore() > player2.getScore()) {
                System.out.println("Player 1 won!");
            } else if (player2.getScore() > player1.getScore()) {
                System.out.println("Player 2 won!");
            } else System.out.println("Draw!");
        }


    }
}
