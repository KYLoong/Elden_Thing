package game.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.status.Status;

/**
 * A damaging crop that deals damage to entities that interact with it.
 * When fertilized, the Bloodrose's damage output is doubled and its display
 * character changes from 'w' to 'ⓦ'. The crop must be watered every 15 turns
 * to maintain its effectiveness.
 *
 * @author Loong Kang Yew, Modified by @Tan Jie Xuan
 */
public class Bloodrose extends Crop {
    private static final int BASE_DAMAGE = 10;
    private static final int FERTILIZED_DAMAGE = 20;

    /**
     * Constructs a new Bloodrose crop.
     * Initializes with base damage of 10, which can be increased to 20 when fertilized.
     */
    public Bloodrose() {
        super('w', "Bloodrose");
    }

    @Override
    protected void applyFertilizerEffect() {
        this.setDisplayChar('ⓦ');
    }

    @Override
    protected String getFertilizationMessage() {
        return "The Bloodrose now emits a more deadly aura!";
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        for(Exit exit: location.getExits()) {
            if(exit.getDestination().containsAnActor()) {
                Actor actor = exit.getDestination().getActor();
                int damage;
                if (hasCapability(Status.FERTILIZED)) {
                    damage = FERTILIZED_DAMAGE;
                } else {
                    damage = BASE_DAMAGE;
                }
                actor.hurt(damage);
                if (!actor.isConscious()) {
                    new Display().println(actor.unconscious(location.map()));
                }
            }
        }
    }
}
