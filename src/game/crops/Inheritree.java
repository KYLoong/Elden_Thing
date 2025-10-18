package game.crops;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.status.Status;

/**
 * A healing crop that provides health and stamina restoration to entities that interact with it.
 * When fertilized, the Inheritree's healing and stamina restoration effects are doubled,
 * and its display character changes from 't' to 'ⓣ'. The crop must be watered every 15 turns
 * to maintain its effectiveness.
 *
 * @author Loong Kang Yew, @modified by Tan Jie Xuan
 */
public class Inheritree extends Crop {
    private static final int BASE_HEAL_AMOUNT = 5;
    private static final int BASE_STAMINA_AMOUNT = 5;
    private static final int FERTILIZED_HEAL_AMOUNT = 10;
    private static final int FERTILIZED_STAMINA_AMOUNT = 10;
    /**
     * Constructs a new Inheritree crop.
     * Initializes with base healing of 5 and stamina restoration of 5,
     * which can be doubled when fertilized.
     */
    public Inheritree() {
        super('t', "Inheritree");
        this.addCapability(Status.HOLY);
    }

    @Override
    public void tick(Location location) {
        super.tick(location);
        for(Exit exit: location.getExits()) {
            if(exit.getDestination().containsAnActor()) {
                Actor actor = exit.getDestination().getActor();
                int healAmount;
                int staminaAmount;
                if (hasCapability(Status.FERTILIZED)) {
                    healAmount = FERTILIZED_HEAL_AMOUNT;
                    staminaAmount = FERTILIZED_STAMINA_AMOUNT;
                } else {
                    healAmount = BASE_HEAL_AMOUNT;
                    staminaAmount = BASE_STAMINA_AMOUNT;
                }
                actor.heal(healAmount);
                if (actor.hasAttribute(BaseActorAttributes.STAMINA)) {
                    actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, staminaAmount);
                }
            }
        }
    }

    @Override
    protected void applyFertilizerEffect() {
        this.setDisplayChar('ⓣ');
    }

    @Override
    protected String getFertilizationMessage() {
        return "The Inheritree now emits a more healing aura!";
    }
}
