package game.npc.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.status.Status;

import java.util.Map;
import java.util.TreeMap;

/**
 * A class representing Enemy in the game.
 * Enemy has a list of behaviours and will perform action based on the behaviours each turn.
 * Created by:
 * @author Ooi Zhong Dek
 * Modified by:
 * Loong Kang Yew
 */
public abstract class Enemy extends Actor {
    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private final int attackBehaviourPriority = 1;

    /**
     * Constructor
     * Constructs Enemy instance.
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        behaviours.put(attackBehaviourPriority, new AttackBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
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

}