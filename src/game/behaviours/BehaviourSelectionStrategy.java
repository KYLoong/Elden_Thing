package game.behaviours;

import java.util.Map;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.Behaviour;

/**
 * Behavior selection policy interface
 *
 * @author Hu Longxi
 */
public interface BehaviourSelectionStrategy {
    /**
     * Select a behavior from the available behaviors
     *
     * @param behaviours Available behavior mappings
     * @param actor The role that performs the act
     * @param map Game map
     * @return The selected behavior, if no behavior is available, null will be returned
     */
    Action selectBehaviour(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map);
}