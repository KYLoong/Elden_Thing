package game.npc.enemy;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.MonologueAction;
import game.affection.AffectionReceiver;
import game.attribute.AffectionLevelAttribute;
import game.behaviours.WanderBehaviour;
import game.capabilites.Speakable;
import game.npc.enemy.Enemy;
import game.status.Status;
import game.weapons.intrinsicweapons.BareFist;

import java.util.ArrayList;
import java.util.List;

import static game.affection.AffectionActionUtils.addGiveActions;
import static game.utils.ConditionUtils.isLessHealth;

/**
 * A class representing Guts in the game.
 * Guts has LISTEN_ABILITY that farmer can listen and has AttackAction
 * Created by:
 * @author Ooi Zhong Dek
 * Modified by:
 * @author Loong Kang Yew
 */
public class Guts extends Enemy implements Speakable, AffectionReceiver {
    private final int wanderBehaviourPriority = 9;

    /**
     * Constructor
     * Constructs Guts instance.
     */
    public Guts(){
        super("Guts", 'g', 500);
        this.setIntrinsicWeapon(new BareFist());
        behaviours.clear();
        behaviours.put(wanderBehaviourPriority, new WanderBehaviour());
        this.addAttribute(AffectionLevelAttribute.AFFECTION_LEVEL, new BaseActorAttribute(1000));
        this.modifyAttribute(AffectionLevelAttribute.AFFECTION_LEVEL, ActorAttributeOperations.DECREASE,1000);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Attack Behaviour
        // But due to the engine only can run one behaviour
        // Iff want some behaviour to run, need to explicitly add it
        if(!canShareAffection(this)){
            for(Exit exit: map.locationOf(this).getExits()){
                // Check the location has actor
                if(exit.getDestination().containsAnActor()) {
                    Actor actorAttack = exit.getDestination().getActor();
                    // Iff has, check the health of actor
                    if(actorAttack.getAttribute(BaseActorAttributes.HEALTH) > 50){
                        return new AttackAction(actorAttack,exit.getName(),this.getIntrinsicWeapon());

                    }
                }
            }
        }

        return super.playTurn(actions, lastAction,map, display);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if(canSpeak(otherActor)){
            speak(otherActor, map, actions);
        }
        addGiveActions(this, true, actions);

        return actions;
    }
    @Override
    public boolean canSpeak(Actor actor){
        return actor.hasCapability(Status.LISTEN_ABILITY);
    }

    @Override
    public void speak(Actor actor, GameMap map, ActionList actions){
        List<String> monologuesPurified = new ArrayList<>();
        // Condition 1
        monologuesPurified.add("RAAAAGH!");
        // Condition 2
        monologuesPurified.add("I’LL CRUSH YOU ALL!");
        // Condition 3
        if(isLessHealth(actor,50)){
            monologuesPurified.add("WEAK! TOO WEAK TO FIGHT ME!");
        }

        actions.add(new MonologueAction(monologuesPurified,this));
    }


    @Override
    public boolean canShareAffection(Actor actor){
        if(this.getAttribute(AffectionLevelAttribute.AFFECTION_LEVEL) >= 100){
            return true;
        }
        return false;
    }
}
