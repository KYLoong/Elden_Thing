package game.capabilites;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for entities that can breed and produce offspring.
 * This interface defines the basic breeding capabilities that any breedable entity should have.
 */
public interface Breedable {
    /**
     * Checks if the entity can breed at the current location.
     * @param location The current location of the entity
     * @return true if the entity can breed, false otherwise
     */
    boolean canBreed(Location location);

    /**
     * Creates and returns a new offspring of the same type as the parent.
     * @return A new instance of the breedable entity
     */
    Actor createOffspring();

    /**
     * Gets the name of the offspring that will be created.
     * @return The name of the offspring
     */
    String getOffspringName();
}