package game.capabilites;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Interface for Purchasable.
 * There are items that can be purchases from the merchant and these are purchasable.
 * Player can purchase these purchasable from the merchant.
 * @author Loong Kang Yew
 */
public interface Purchasable {
    /**
     * Method used to handle what happened after the player purchased an item from the merchant.
     * @param buyer the player that purchase the items from the merchant
     * @param seller the merchant that sell the items to the player
     * @param map the map of the world
     */
    void purchase(Actor buyer, Actor seller,GameMap map);

    /**
     * Method used to check the condition when purchasing the item from the merchant
     * @param buyer the player that purchase the items from the merchant
     * @param seller the merchant that sell the items to the player
     * @return a boolean value
     */
    boolean canPurchase(Actor buyer, Actor seller);
}
