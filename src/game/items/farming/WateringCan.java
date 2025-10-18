package game.items.farming;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.WaterCropAction;
import game.status.Status;

/**
 * A class representing a Watering Can that can be used for watering plants.
 */
public class WateringCan extends FarmingTool {
    public WateringCan() {
        super("Watering Can", 'W');
    }

    @Override
    protected void addToolAction(ActionList actions, Location location) {
        if (location.getGround().hasCapability(Status.FERTILIZABLE) && !location.getGround().hasCapability(Status.NORMAL)) {
            actions.add(new WaterCropAction(location));
        }
    }
} 