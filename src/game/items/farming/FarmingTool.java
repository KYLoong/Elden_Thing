package game.items.farming;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Abstract base class for all farming tools in the game.
 * This class provides common functionality for managing tool actions and capabilities.
 * Farming tools are used to interact with crops and ground, such as watering plants,
 * fertilizing crops, or removing dead roots.
 *
 * @author Tan Jie Xuan
 */
public abstract class FarmingTool extends Item {
    protected final String name;
    protected final char displayChar;

    /**
     * Constructs a new FarmingTool with the specified name and display character.
     * The display character is used to represent the tool on the map.
     *
     * @param name The name of the tool
     * @param displayChar The character used to represent this tool on the map
     */
    public FarmingTool(String name, char displayChar) {
        super(name, displayChar, true);
        this.name = name;
        this.displayChar = displayChar;
    }

    /**
     * Returns a list of actions that can be performed with this tool.
     * This method adds tool-specific actions based on the ground type at the actor's location.
     *
     * @param owner The actor who owns this tool
     * @param map The game map
     * @return A list of actions that can be performed with this tool
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        Location here = map.locationOf(owner);
        addToolAction(actions, here);
        return actions;
    }

    /**
     * Adds the appropriate action for this tool based on the ground type.
     * This method should be implemented by each specific tool type to define
     * what actions are available based on the ground at the given location.
     *
     * @param actions The list of actions to add to
     * @param location The location to check for ground type
     */
    protected abstract void addToolAction(ActionList actions, Location location);
} 