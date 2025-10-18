package game.items.farming;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DigAction;
import game.status.Status;

/**
 * A class representing a Shovel that can be used for digging.
 */
public class Shovel extends FarmingTool {
    public Shovel() {
        super("Shovel", 'S');
    }

    @Override
    protected void addToolAction(ActionList actions, Location location) {
        if (location.getGround().hasCapability(Status.DEAD)) {
            actions.add(new DigAction(location));
        }
    }
}