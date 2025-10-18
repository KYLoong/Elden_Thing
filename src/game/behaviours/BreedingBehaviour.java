package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SpawnOffspringAction;
import game.capabilites.Breedable;
import game.status.Status;
import edu.monash.fit2099.engine.actors.Behaviour;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A generic behaviour for entities that can breed.
 * This behaviour can be used by any entity that implements the Breedable interface.
 */
public class BreedingBehaviour implements Behaviour {
    private final Random random = new Random();
    private final Breedable parent;
    private final Status requiredGroundStatus; // e.g., Status.HOLY for Spirit Goats
    /**
     * Constructor for BreedingBehaviour.
     * @param parent The breedable entity that will use this behaviour
     * @param requiredGroundStatus The status that the ground must have for breeding to occur
     */
    public BreedingBehaviour(Breedable parent, Status requiredGroundStatus) {
        this.parent = parent;
        this.requiredGroundStatus = requiredGroundStatus;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);

        // Check if the entity can breed at this location
        if (!parent.canBreed(currentLocation)) {
            return null;
        }

        // Check for required ground status in adjacent tiles
        boolean hasRequiredGroundNearby = false;
        for (Exit exit : currentLocation.getExits()) {
            if (exit.getDestination().getGround().hasCapability(requiredGroundStatus)) {
                hasRequiredGroundNearby = true;
                break;
            }
        }
        if (!hasRequiredGroundNearby) return null;

        // Find empty adjacent tile to spawn offspring
        List<Exit> exits = new ArrayList<>(currentLocation.getExits());
        Collections.shuffle(exits, random); // Randomize spawn location
        for (Exit exit : exits) {
            Location dest = exit.getDestination();
            if (!dest.containsAnActor() && dest.getGround().canActorEnter(actor)) {
                return new SpawnOffspringAction(dest, parent);
            }
        }
        return null;
    }
}