package game.behaviours;

import java.util.Map;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.Behaviour;

/**
 * Priority Behavior Selection Policy - Behaviors are selected based on priority order
 *
 * @author Hu Longxi
 */
public class PrioritySelectionStrategy implements BehaviourSelectionStrategy {

    @Override
    public Action selectBehaviour(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(actor, map);
            if (action != null)
                return action;
        }
        return null;
    }
}