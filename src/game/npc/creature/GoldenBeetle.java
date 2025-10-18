package game.npc.creature;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.behaviours.FollowBehaviour;
import game.capabilites.Eatable;
import game.egg.GoldenEgg;
import game.status.Status;

/**
 * A class representing a Golden Beetle in the game.
 * <p>
 * The Golden Beetle is a creature with 25 health points that wanders the map and can follow the Farmer.
 * Every 5 turns, it produces a Golden Egg. The beetle is immune to Crimson Rot and can be consumed by
 * the Farmer to heal 15 HP and gain 1000 runes. Consuming the beetle removes it from the map.
 * </p>
 *
 * @author Hu Longxi
 */
public class GoldenBeetle extends Creature implements Eatable {
    private int turnsSinceLastEgg = 0;
    private Actor followTarget = null; // The actor being followed (e.g., Farmer)

    /**
     * Constructor for GoldenBeetle.
     * Initializes the beetle with 25 health points and adds wandering behavior.
     */
    public GoldenBeetle() {
        super("Golden Beetle", 'b', 25);
    }

    /**
     * Called each turn to determine and perform the Golden Beetle's action.
     * Handles egg-laying every 5 turns and following the Farmer if applicable.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Egg-laying logic (prioritized over following)
        turnsSinceLastEgg++;
        if (turnsSinceLastEgg >= 5) {
            layEgg(map, display);
            turnsSinceLastEgg = 0;
            return new DoNothingAction(); // No further action this turn
        }

        // Check for followable actors (e.g., Farmer) in surroundings
        if (followTarget == null) {
            for (Actor actor : getSurroundingActors(map)) {
                if (actor.hasCapability(Status.PLAYER)) {
                    followTarget = actor;
                    behaviours.clear(); // Remove wandering
                    behaviours.put(111, new FollowBehaviour(followTarget, 5));
                    break;
                }
            }
        }

        // Perform normal behavior (wandering or following)
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Lays a Golden Egg at the beetle's current location.
     *
     * @param map     the game map
     * @param display the display for output
     */
    private void layEgg(GameMap map, Display display) {
        map.locationOf(this).addItem(new GoldenEgg());
    }

    /**
     * Set the players you want to follow
     *
     * @param player The player to follow
     */
    public void setPlayerToFollow(Actor player) {
        behaviours.put(700, new FollowBehaviour(player, 5));
    }

    /**
     * Retrieves actors in surrounding locations.
     *
     * @param map the game map
     * @return a list of actors in adjacent locations
     */
    private Iterable<Actor> getSurroundingActors(GameMap map) {
        Location here = map.locationOf(this);
        // Create an empty list to store the surrounding roles
        java.util.List<Actor> surroundingActors = new java.util.ArrayList<>();

        // Go through all exits
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                surroundingActors.add(destination.getActor());
            }
        }

        // Return to the collection of surrounding characters
        return surroundingActors;
    }

    /**
     * Returns the list of allowable actions for this beetle.
     * If the actor is the Farmer, allows consuming the beetle.
     *
     * @param otherActor the actor interacting with the beetle
     * @param direction  the direction of interaction
     * @param map        the game map
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.PLAYER)) {
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
        actor.heal(15);
        actor.addBalance(1000);
        map.removeActor(this);
    }
}