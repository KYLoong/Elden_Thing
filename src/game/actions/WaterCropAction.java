package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.status.Status;

/**
 * An action to water a crop.
 */
public class WaterCropAction extends Action {
    private final Location location;

    public WaterCropAction(Location location) {
        this.location = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (location.getGround().hasCapability(Status.FERTILIZABLE) && !location.getGround().hasCapability(Status.NORMAL)) {
            location.getGround().addCapability(Status.NORMAL);
            location.getGround().addCapability(Status.WATERED);
            return actor + " waters the " + location.getGround();
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " waters the " + location.getGround();
    }
} 