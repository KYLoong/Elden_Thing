package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.status.Status;

/**
 * A class representing a Talisman that an actor can pick up and drop
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Loong Kang Yew
 */
public class Talisman extends Item {
    public Talisman() {
        super("Talisman", 'o', true);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        actor.addCapability(Status.WITH_TALISMAN);
    }
}
