package game.items.farming;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FertilizerAction;
import game.status.Status;

/**
 * A class representing a Fertilizer that can be used to enhance crops.
 */
public class Fertilizer extends FarmingTool {
    public Fertilizer() {
        super("Fertilizer", 'F');
    }

    @Override
    protected void addToolAction(ActionList actions, Location location) {
        if (location.getGround().hasCapability(Status.FERTILIZABLE) && !location.getGround().hasCapability(Status.FERTILIZED)) {
            actions.add(new FertilizerAction(location));
        }
    }
}