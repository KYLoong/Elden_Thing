package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Soil;

/**
 * An action that represents the removal of dead roots using a shovel.
 * This action can only be performed on ground with the DEAD capability.
 * When executed, it converts dead roots back to soil, effectively clearing
 * the area for new plant growth.
 *
 * @author Tan Jie Xuan
 */
public class DigAction extends Action {
    private final Location targetLocation;

    public DigAction(Location targetLocation) {
        this.targetLocation = targetLocation;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        targetLocation.setGround(new Soil());
        return actor + " digs up the dead root and converts it back to soil";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " digs up the dead root";
    }
} 