package game.crops;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilites.Fertilizable;
import game.capabilites.Waterable;
import game.grounds.DeadRoot;
import game.status.Status;

/**
 * Abstract base class for all crops in the game.
 * This class provides common functionality for managing crop lifecycle,
 * including turn counting and watering mechanics. All crops must be watered
 * every 15 turns to survive. Crops can be fertilized to enhance their effects.
 *
 * @author Tan Jie Xuan
 */
public abstract class Crop extends Ground implements Fertilizable, Waterable {
    protected int turnsLeft;
    protected static final int MAX_TURNS = 15;

    /**
     * Constructs a new Crop with the specified display character and name.
     * Initializes the turn counter to MAX_TURNS (15) and adds the FERTILIZABLE capability.
     *
     * @param displayChar The character used to represent this crop on the map
     * @param name The name of the crop
     */
    public Crop(char displayChar, String name) {
        super(displayChar, name);
        this.turnsLeft = MAX_TURNS;
        this.addCapability(Status.FERTILIZABLE);
    }

    @Override
    public void tick(Location location) {
        if (!hasCapability(Status.NORMAL)) {
            turnsLeft--;
            if (turnsLeft <= 0) {
                location.setGround(new DeadRoot());
            }
        }
        removeCapability(Status.NORMAL);
        if (hasCapability(Status.WATERED)) {
            turnsLeft = MAX_TURNS;
            removeCapability(Status.WATERED);
        }
        if (hasCapability(Status.FERTILIZED)) {
            applyFertilizerEffect();
        }
    }

    /**
     * Applies the fertilizer effect to the crop.
     * This method should be implemented by subclasses to provide specific effects.
     */
    protected abstract void applyFertilizerEffect();

    /**
     * Gets the message to display when the crop is fertilized.
     * This method should be implemented by subclasses to provide specific messages.
     *
     * @return The fertilization message
     */
    protected abstract String getFertilizationMessage();

    /**
     * Waters the crop, resetting its turn counter to MAX_TURNS (15).
     * This method should be called when using the WateringCan tool.
     */
    @Override
    public void water() {
        turnsLeft = MAX_TURNS;
    }
    
    /**
     * Fertilizes the crop, enhancing its effects permanently.
     * The specific effects depend on the crop type.
     *
     * @return A message describing the fertilization effect, or null if no message is needed
     */
    @Override
    public String fertilize() {
        this.addCapability(Status.FERTILIZED);
        return getFertilizationMessage();
    }
}