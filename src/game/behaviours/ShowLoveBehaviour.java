package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ShowLoveAction;
import game.attribute.AffectionLevelAttribute;

/**
 * Created by:
 * @author Ooi Zhong Dek
 *
 */
public class ShowLoveBehaviour implements Behaviour {
    /**
     * Returns a ShowLoveAction if the actor's affection level reaches the required threshold.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return a ShowLoveAction if affection level is sufficient, otherwise null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()).execute(actor, map);
            }
        }

        if(actor.getAttribute(AffectionLevelAttribute.AFFECTION_LEVEL) >= 100){
            return new ShowLoveAction();
        }

        return null;
    }
}
