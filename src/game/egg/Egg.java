package game.egg;

import edu.monash.fit2099.engine.items.Item;

/**
 * A base class for all egg-related items.
 */
public abstract class Egg extends Item {
    public int turnstoHatch;
    public Egg(String name, char displayChar, boolean portable, int turnstoHatch) {
        super(name, displayChar, portable);
        this.turnstoHatch = turnstoHatch;
    }
}