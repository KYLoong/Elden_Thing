package game.statuseffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.StatusEffect;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class representing Crimson Rot Status Effect in the game.
 * Crimson Rot is a Status Effect that cause a creature to have a countdown timer.
 * @author Loong Kang Yew
 */
public class CrimsonRot extends StatusEffect {
    private int lifeSpan;
    private int maxLifeSpan;

    /**
     * Constructor
     * Constructs Crimson Rot instance.
     */
    public CrimsonRot(int lifeSpan){
        super("Crimson Rot");
        this.lifeSpan = lifeSpan;
        this.maxLifeSpan = lifeSpan;
    }

    /**
     *  Method to reset the lifespan of a creature
     */
    public void resetLifeSpan(){
        this.lifeSpan = maxLifeSpan;
    }

    @Override
    public void tick(Location location, Actor actor){
        lifeSpan--;
        if (lifeSpan <= 0){
            location.map().removeActor(actor);
            actor.removeStatusEffect(this);
        }
    }
}
