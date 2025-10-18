package game.utils;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.status.Status;

public class ConditionUtils {

    public static boolean isLessHealth(Actor actor, int value) {
        return actor.getAttribute(BaseActorAttributes.HEALTH) < value;
    }

    public static boolean isLessBalance(Actor actor, int value) {
        return actor.getBalance() < value;
    }

    public static boolean isInventoryEmpty(Actor actor) {
        return actor.getItemInventory().isEmpty();
    }

    public static boolean checkCrusedEntities(GameMap map, Actor character){
        boolean bool = false;

        for (Exit exit: map.locationOf(character).getExits()) {
            // Check for crused ground
            if (exit.getDestination().getGround().hasCapability(Status.CRIMSON_ROT)) {
                bool = true;
            }

            //Check for crused actor
            if (exit.getDestination().containsAnActor()) {
                if (exit.getDestination().getActor().hasCapability(Status.CRIMSON_ROT)) {
                    bool = true;
                }
            }
        }
        return  bool;
    }
}

