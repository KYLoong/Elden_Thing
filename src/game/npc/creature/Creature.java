package game.npc.creature;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.affection.AffectionReceiver;
import game.attribute.AffectionLevelAttribute;
import game.behaviours.BehaviourSelectionStrategy;
import game.behaviours.PrioritySelectionStrategy;
import game.behaviours.WanderBehaviour;
import game.status.Status;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class representing Creature in the game.
 * Creature has a list of behaviours and will perform action based on the behaviours each turn.
 * @author Loong Kang Yew
 */
public abstract class Creature extends Actor implements AffectionReceiver {
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();
    protected BehaviourSelectionStrategy behaviourSelectionStrategy;

    /**
     * Constructor
     * Constructs Creature instance.
     */
    public Creature(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        behaviours.put(999, new WanderBehaviour());

        // Initialize the property
        this.addAttribute(AffectionLevelAttribute.AFFECTION_LEVEL, new BaseActorAttribute(1000));
        this.modifyAttribute(AffectionLevelAttribute.AFFECTION_LEVEL, ActorAttributeOperations.DECREASE,1000);

        // Use priority selection policies
        this.behaviourSelectionStrategy = new PrioritySelectionStrategy();
    }

    /**
     * Set a behavior selection policy
     * @param strategy behavior to select a strategy
     */
    public void setBehaviourSelectionStrategy(BehaviourSelectionStrategy strategy) {
        this.behaviourSelectionStrategy = strategy;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Use the policy mode to select behaviors
        Action action = behaviourSelectionStrategy.selectBehaviour(behaviours, this, map);
        if (action != null) {
            return action;
        }
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }

    @Override
    public boolean canShareAffection(Actor actor){
        if(this.getAttribute(AffectionLevelAttribute.AFFECTION_LEVEL) >= 100){
            return true;
        }
        return false;
    }
}