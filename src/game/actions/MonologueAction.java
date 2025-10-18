package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.List;
import java.util.Random;

/**
 * A class representing Monologue Action in the game.
 * Monologue Action is the action in the game that performed by the player to listen to a NPC.
 * @author Loong Kang Yew
 */
public class MonologueAction extends Action {
    private List<String> monologues;
    private Actor character;

    /**
     * Constructor
     * Constructs Monologue Action instance.
     */
    public MonologueAction(List<String> monologues, Actor character) {
        this.monologues = monologues;
        this.character = character;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Random random = new Random();
        int randomIndex = random.nextInt(monologues.size());

        return monologues.get(randomIndex);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " listen to " + character;
    }

}
