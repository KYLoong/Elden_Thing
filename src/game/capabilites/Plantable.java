package game.capabilites;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Interface for Plantable.
 * Seeds in the player inventory can be plant on the soil
 * Player can perform planting action to plantable
 * @author Loong Kang Yew
 */
public interface Plantable {
    /**
     * Method used to determine what happened after planting the plantable on the soil.
     * @param actor the player, who performed the planting action
     * @param location the location that where the planting action occurs
     * @param map the map of the world
     */
    void plant(Actor actor, Location location, GameMap map);

    /**
     * Getter method to get the seed item from the player's inventory.
     * @return the seed that used for planting action
     */
    Item getSeed();

    /**
     * Method to determine what will grow up after planting the seed on the soil.
     * @return a ground object that represent the crop after planting the seed
     */
    Ground afterPlanted();

    /**
     * Method to check the condition when planting the plantable.
     * @param actor the player, who performed the planting action
     * @return a boolean value
     */
    boolean canPlant(Actor actor);
}
