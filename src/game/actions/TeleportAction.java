package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Teleport Action, which allows characters to teleport between maps
 *
 * @author Hu Longxi
 */
public class TeleportAction extends Action {

    private final Location destination;
    private final String destinationMapName;

    /**
     * Construct a teleport action
     *
     * @param destination Destination location
     * @param destinationMapName The name of the destination map
     */
    public TeleportAction(Location destination, String destinationMapName) {
        this.destination = destination;
        this.destinationMapName = destinationMapName;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Removes the character from the current map
        map.removeActor(actor);

        // Add the character to a specified location on the destination map
        destination.map().addActor(actor, destination);

        return actor + " Teleported to " + destinationMapName;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " Teleport to " + destinationMapName;
    }
}