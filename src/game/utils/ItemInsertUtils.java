package game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class ItemInsertUtils {

    /**
     * Inserts the given item into the actor's inventory multiple times.
     *
     * @param item  the item to insert
     * @param actor the actor receiving the items
     * @param count the number of times to insert the item
     */
    public static void insertMultipleItem(Item item, Actor actor, int count) {
        for (int i = 0; i < count; i++) {
            actor.addItemToInventory(item);
        }
    }
}
