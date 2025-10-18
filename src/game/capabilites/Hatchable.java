package game.capabilites;

import edu.monash.fit2099.engine.positions.Location;

public interface Hatchable {
    boolean canHatch();
    void hatch(Location location);
}