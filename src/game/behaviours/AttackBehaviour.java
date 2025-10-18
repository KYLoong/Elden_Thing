package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

/**
 * A generic behaviour for entities that can attack other actor.
 * This behaviour can be used by any entity that will attack other actor within its surrounding.
 * @author Loong Kang Yew
 */
public class AttackBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor target = exit.getDestination().getActor();
                return new AttackAction(target, exit.getName());
            }
        }

        return null;
    }
}
