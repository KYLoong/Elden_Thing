package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Implement a class that follows behavior so that one character can follow another target persona.
 * <p>
 * This behavior causes the character to move towards the target, but will only follow if the distance between the two does not exceed the maximum distance.
 * The character chooses to move in the direction that minimizes the distance between themselves and the target.
 * </p>
 *
 * @author Hu Longxi
 */
public class FollowBehaviour implements Behaviour {
    /**
     * The target persona to follow
     */
    private final Actor target;

    /**
     * The maximum follow distance, after which the following will stop
     */
    private final int maxDistance;

    /**
     * Create a new Follow behavior
     *
     * @param target The target persona to follow
     * @param maxDistance The maximum follow distance, after which the following will stop
     */
    public FollowBehaviour(Actor target, int maxDistance) {
        this.target = target;
        this.maxDistance = maxDistance;
    }

    /**
     * Decide what actions to perform to follow the goal
     * <p>
     * If the distance to the target exceeds the maximum following distance, no action will be performed.
     * Otherwise, an adjacent location is chosen to minimize the distance between the character and the target to move there.
     * </p>
     *
     * @param actor Roles that perform follow-up behaviors
     * @param map Game map
     * @return If you can follow, return to the mobile action; Otherwise, null is returned
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(actor);
        Location targetLocation = map.locationOf(target);

        // Check distance to target
        int distance = Math.abs(actorLocation.x() - targetLocation.x()) + Math.abs(actorLocation.y() - targetLocation.y());
        if (distance > maxDistance) {
            return null;
        }

        // Find the closest exit to the target
        Exit closestExit = null;
        int minDistance = Integer.MAX_VALUE;
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();
            // Check both canActorEnter and that the ground itself allows entry
            if (!destination.containsAnActor() && destination.canActorEnter(actor)) {
                int newDistance = Math.abs(destination.x() - targetLocation.x()) + Math.abs(destination.y() - targetLocation.y());
                if (newDistance < minDistance) {
                    minDistance = newDistance;
                    closestExit = exit;
                }
            }
        }

        if (closestExit != null) {
            return new MoveActorAction(closestExit.getDestination(), closestExit.getName());
        }
        return null;
    }
}