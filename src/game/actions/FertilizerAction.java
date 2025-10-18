package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.status.Status;

/**
 * An action to fertilize a crop.
 */
public class FertilizerAction extends Action {
    private final Location location;

    public FertilizerAction(Location location) {
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (location.getGround().hasCapability(Status.FERTILIZABLE) && !location.getGround().hasCapability(Status.FERTILIZED)) {
            location.getGround().addCapability(Status.FERTILIZED);
            return actor + " fertilizes the " + location.getGround() + ". The " + location.getGround() + " now emits a more deadly aura!";
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fertilizes the " + location.getGround();
    }
}
