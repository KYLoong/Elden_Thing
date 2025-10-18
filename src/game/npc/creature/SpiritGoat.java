package game.npc.creature;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CureAction;
import game.behaviours.BreedingBehaviour;
import game.behaviours.ShowLoveBehaviour;
import game.capabilites.Breedable;
import game.capabilites.Curable;
import game.status.Status;
import game.statuseffects.CrimsonRot;

import static game.affection.AffectionActionUtils.addGiveActions;

/**
 * A class representing a Spirit Goat in the game.
 * <p>
 * Spirit Goats can wander around the map and produce offspring if they are adjacent to an Inheritree.
 * Offspring are spawned in a random adjacent empty location when the conditions are met.
 * </p>
 *
 * @author Loong Kang Yew
 * @modified by Tan Jie Xuan
 */
public class SpiritGoat extends Creature implements Curable, Breedable {
    private CrimsonRot crimsonRot = new CrimsonRot(10);

    /**
     * Constructor for SpiritGoat.
     * Initializes the Spirit Goat with its behaviours and status effects.
     */
    public SpiritGoat() {
        super("Spirit Goat", 'y', 50);
        this.addCapability(Status.CRIMSON_ROT);
        behaviours.put(111, new BreedingBehaviour(this, Status.HOLY));
        this.addStatusEffect(crimsonRot);
        behaviours.put(222,new ShowLoveBehaviour());
    }

    /**
     * Cures the Spirit Goat of Crimson Rot.
     *
     * @param actor the actor performing the cure
     * @param map   the game map
     */
    @Override
    public void cure(Actor actor, GameMap map) {
        crimsonRot.resetLifeSpan();
    }

    /**
     * Determines if the Spirit Goat can be cured by the given actor.
     *
     * @param actor the actor attempting to cure
     * @return true if the Spirit Goat can be cured
     */
    @Override
    public boolean canCure(Actor actor) {
        return true;
    }

    /**
     * Returns the list of allowable actions that another actor can perform on this Spirit Goat.
     * Adds a CureAction if the other actor is the player and has a talisman.
     *
     * @param otherActor the actor interacting with this Spirit Goat
     * @param direction  the direction of the other actor
     * @param map        the game map
     * @return the list of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if(otherActor.hasCapability(Status.PLAYER) && otherActor.hasCapability(Status.WITH_TALISMAN)){
            actions.add(new CureAction(this, otherActor));
        }
        addGiveActions(this, false, actions);
        return actions;
    }

    @Override
    public boolean canBreed(Location location) {
        return true; // Spirit Goats can always breed when near an Inheritree
    }

    @Override
    public Actor createOffspring() {
        return new SpiritGoat();
    }

    @Override
    public String getOffspringName() {
        return "Spirit Goat";
    }

}
