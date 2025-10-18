package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.status.Status;

/**
 * A class representing a dead root that appears when crops die.
 */
public class DeadRoot extends Ground {
    public DeadRoot() {
        super('r', "Dead Root");
        this.addCapability(Status.DEAD);
    }

    /**
     * Converts the dead root back to soil
     * @return a new Soil ground
     */
    public Soil convertToSoil() {
        return new Soil();
    }
} 