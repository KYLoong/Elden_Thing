package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.status.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Portal terrain class, which is used to connect multiple different maps
 *
 * @author Hu Longxi
 */
public class Portals extends Ground {

    // Use Map to store multiple destinations
    private Map<String, Location> destinations = new HashMap<>();

    /**
     * Create a portal
     */
    public Portals() {
        super('A', "Portal");
    }

    /**
     * Add a delivery destination
     *
     * @param destinationName Destination name
     * @param destinationLocation Target location
     */
    public void addDestination(String destinationName, Location destinationLocation) {
        this.destinations.put(destinationName, destinationLocation);
    }

    /**
     * Get all teleportation destinations
     *
     * @return Destination mapping table
     */
    public Map<String, Location> getDestinations() {
        return destinations;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);

        // Only the player can use the portal
        if (actor.hasCapability(Status.PLAYER)) {
            // Create a teleport action for each destination
            for (Map.Entry<String, Location> entry : destinations.entrySet()) {
                actions.add(new TeleportAction(entry.getValue(), entry.getKey()));
            }
        }

        return actions;
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return true;
    }
}