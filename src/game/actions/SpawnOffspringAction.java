package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilites.Breedable;

/**
 * An action that spawns a new offspring at a specified location.
 * This action is generic and can be used by any breedable entity.
 */
public class SpawnOffspringAction extends Action {
    private final Location spawnLocation;
    private final Breedable parent;

    /**
     * Constructor for SpawnOffspringAction.
     * @param spawnLocation The location where the offspring will be spawned
     * @param parent The parent entity that is spawning the offspring
     */
    public SpawnOffspringAction(Location spawnLocation, Breedable parent) {
        this.spawnLocation = spawnLocation;
        this.parent = parent;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Actor offspring = parent.createOffspring();
        spawnLocation.addActor(offspring);
        return actor + " spawns a " + parent.getOffspringName() + "!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " spawns a " + parent.getOffspringName() + " nearby";
    }
}