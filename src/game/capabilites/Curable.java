package game.capabilites;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for Curable.
 * Since the world is affected by the Crimson Rot, player can perform cure action to Curable and cure them of the Crimson Rot.
 * @author Loong Kang Yew
 */
public interface Curable {
    /**
     * Method used to define what happened after the target is cured.
     * @param actor the player, who performed the cure action
     * @param map the map of the world
     */
    void cure(Actor actor, GameMap map);

    /**
     * Method used to check the condition when curing the target.
     * @param actor the player, who performed the cure action
     * @return a boolean value
     */
    boolean canCure(Actor actor);
}
