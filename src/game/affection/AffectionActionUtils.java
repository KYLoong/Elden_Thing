package game.affection;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import game.actions.GiveAction;
import game.items.affection.Dessert;
import game.items.affection.Flower;
import game.items.affection.Jewelry;

public class AffectionActionUtils {
    /**
     * Adds GiveAction(s) to the provided ActionList based on the character and speakability.
     *
     * @param character  The AffectionReceiver to give items to
     * @param speakable  Whether the receiver can speak (controls if Dessert is included)
     * @param actions    The ActionList to add actions to
     */
    public static void addGiveActions(Actor character, boolean speakable, ActionList actions) {
        actions.add(new GiveAction(character, new Flower()));
        actions.add(new GiveAction(character, new Jewelry()));
        if (speakable) {
            actions.add(new GiveAction(character, new Dessert()));
        }
    }
}
