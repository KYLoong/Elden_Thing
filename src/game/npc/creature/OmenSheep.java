package game.npc.creature;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.CureAction;
import game.affection.AffectionReceiver;
import game.attribute.AffectionLevelAttribute;
import game.behaviours.ShowLoveBehaviour;
import game.capabilites.Curable;
import game.crops.Inheritree;
import game.egg.OmenSheepEgg;
import game.status.Status;
import game.statuseffects.CrimsonRot;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.displays.Display;
import game.behaviours.WanderBehaviour;

import static game.affection.AffectionActionUtils.addGiveActions;

/**
 * A class representing Omen Sheep in the game.
 * <p>
 * Omen Sheep can wander around the map and produce an Omen Sheep Egg every 7 turns.
 * </p>
 * 
 * @author Loong Kang Yew
 * @modified by Tan Jie Xuan
 */
public class OmenSheep extends Creature implements Curable {
    private CrimsonRot crimsonRot = new CrimsonRot(15);
    private int turnsSinceLastEgg = 0;

    /**
     * Constructor
     * Constructs Omen Sheep instance.
     */
    public OmenSheep() {
        super("Omen Sheep", 'm', 75);
        this.addCapability(Status.CRIMSON_ROT);
        this.addStatusEffect(crimsonRot);
        behaviours.put(222,new ShowLoveBehaviour());

    }

    /**
     * Called each turn to determine and perform the Omen Sheep's action.
     * Also handles egg-laying every 7 turns.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Egg-laying logic
        turnsSinceLastEgg++;
        if (turnsSinceLastEgg >= 7) {
            map.locationOf(this).addItem(new OmenSheepEgg());
            turnsSinceLastEgg = 0;
        }

        // Call the normal behaviour selection
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public void cure(Actor actor, GameMap map) {
        for (Exit exit: map.locationOf(this).getExits()){
            if (exit.getDestination().getGround().canActorEnter(actor)){
                exit.getDestination().setGround(new Inheritree());
            }
        }
    }

    @Override
    public boolean canCure(Actor actor) {
        return true;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if(otherActor.hasCapability(Status.PLAYER) && otherActor.hasCapability(Status.WITH_TALISMAN)){
            actions.add(new CureAction(this, otherActor));
        }
        addGiveActions(this, false, actions);
        return actions;
    }


}
