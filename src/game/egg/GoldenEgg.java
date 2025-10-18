package game.egg;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.capabilites.Eatable;
import game.capabilites.Hatchable;
import game.npc.creature.GoldenBeetle;
import game.status.Status;

/**
 * A class representing a Golden Egg item.
 * <p>
 * The Golden Egg is produced by a Golden Beetle every 5 turns. It hatches into a new Golden Beetle
 * if positioned near Blight ('x') and not picked up by the Farmer. If in the Farmer's inventory,
 * it can be consumed to restore 20 stamina.
 * </p>
 *
 * @author Hu Longxi
 */
public class GoldenEgg extends Egg implements Eatable, Hatchable {
    private int turnsOnGround = 0;

    /**
     * Constructor for GoldenEgg.
     * Sets the name, display character, portability, and hatching turns.
     */
    public GoldenEgg() {
        super("Golden Egg", '0', true, 5);
    }

    /**
     * Called each turn when the egg is on the ground.
     * Increments the turn counter and hatches the egg into a Golden Beetle if near Blight.
     *
     * @param currentLocation the location of the egg on the map
     */
    @Override
    public void tick(Location currentLocation) {
        // Only hatch if on the ground (not in inventory) and not held by an actor
        if (!currentLocation.containsAnActor()) {
            turnsOnGround++;
            if (canHatch() && isNearCursedEntity(currentLocation)) {
                hatch(currentLocation);
            }
        }
    }

    /**
     * Checks if the egg is near any cursed entity (Blight ground, Omen Sheep, etc).
     *
     * @param location the egg's location
     * @return true if a cursed entity is in an adjacent location
     */
    private boolean isNearCursedEntity(Location location) {
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();

            // Check if the terrain comes with the BLIGHT ability
            if (destination.getGround().hasCapability(Status.BLIGHT)) {
                return true;
            }

            // Check if the character in the location has CRIMSON_ROT abilities (cursed mark)
            if (destination.containsAnActor() &&
                    destination.getActor().hasCapability(Status.CRIMSON_ROT)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the egg can hatch.
     *
     * @return true if the egg has been on the ground for more than the hatching cycle
     */
    @Override
    public boolean canHatch() {
        return turnsOnGround > 0;
    }

    /**
     * Hatches the egg into a new Golden Beetle and removes the egg.
     *
     * @param location the egg's location
     */
    @Override
    public void hatch(Location location) {
        location.addActor(new GoldenBeetle());
        location.removeItem(this);
    }

    /**
     * Returns the list of allowable actions for this item.
     * If the actor is the Farmer, allows the egg to be consumed from inventory.
     *
     * @param actor the actor holding or interacting with the item
     * @param map   the game map
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = super.allowableActions(actor, map);
        if (actor.hasCapability(Status.PLAYER)) {
            actions.add(new EatAction(this));
        }
        return actions;
    }

    @Override
    public boolean canBeEaten() {
        return true;
    }

    @Override
    public void eat(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 20);
        actor.removeItemFromInventory(this);
    }
}