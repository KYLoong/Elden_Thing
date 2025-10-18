package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Love;
import game.status.Status;

/**
 * Class representing an action where an actor shows love to surrounding areas.
 *
 * @author Ooi Zhong Dek
 */
public class ShowLoveAction extends Action {
    /**
     * Executes the Show Love action by transforming nearby ground tiles.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for(Exit exit: map.locationOf(actor).getExits()){
            if (exit.getDestination().getGround().hasCapability(Status.BLIGHT) || exit.getDestination().getGround().hasCapability(Status.SOIL)){
                exit.getDestination().setGround(new Love(actor));
            }
        }
        return actor + "has show loved";
    }

    /**
     * Returns a description of this action suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " show Love " + " nearby";
    }
}
