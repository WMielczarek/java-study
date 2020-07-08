package classes.players;


import classes.JSMoveException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BotPlayer extends Player {
    private ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    private int lastMove;
    private String scriptFileName = "smarty.js";

    @Override
    public int getNextMove(List<Integer> player2CardsLeft) throws JSMoveException {

        try {
            engine.eval(new FileReader(scriptFileName));
            Invocable invocable = (Invocable) engine;
            Object result;
            result = invocable.invokeFunction("nextMove", cards, player2CardsLeft);
            lastMove = (int) result;
        } catch (ScriptException e) {
            throw new JSMoveException("Javascript error!!");
        } catch (FileNotFoundException e) {
            throw new JSMoveException("File with script dont exist");
        } catch (NoSuchMethodException e) {
            throw new JSMoveException("Couldnt find function in file");
        }
        return lastMove;
    }

    @Override
    public int getLastMove() {
        return lastMove;
    }

    @Override
    public List<Integer> getAvailableCards() {
        return new ArrayList<>(cards);
    }

    @Override
    public String getName() {
        return scriptFileName;
    }

    @Override
    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    public void swapScript(String scriptFileName) {
        this.scriptFileName = scriptFileName;
    }


}
