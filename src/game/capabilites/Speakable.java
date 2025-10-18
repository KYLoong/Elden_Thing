package game.capabilites;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

// Later comment

public interface Speakable {
    /**
     * Method used to define what message speaked.
     * @param actor the player, who performed the cure action
     * @param map the map of the world
     * @param actions a list of action
     */
    public void speak(Actor actor, GameMap map, ActionList actions);

    /**
     * Method used to check the condition when can speak.
     * @param actor the player, who performed the cure action
     * @return a boolean value
     */
    boolean canSpeak(Actor actor);
}
