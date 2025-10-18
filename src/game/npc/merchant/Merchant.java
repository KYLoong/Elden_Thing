package game.npc.merchant;

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
import game.attribute.AffectionLevelAttribute;
import game.behaviours.WanderBehaviour;
import game.capabilites.Purchasable;
import game.actions.PurchaseAction;
import game.status.Status;
import game.weapons.weaponitems.BroadSword;
import game.weapons.weaponitems.DragonslayerGreatsword;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing Merchant in the game.
 * Merchant has a list of item to sell and the player are able to purchase the items from them when player are surrounding.
 * @author Loong Kang Yew
 */
public abstract class Merchant extends Actor{
    private ArrayList<Purchasable> itemsToSell = new ArrayList<>();
    protected List<Behaviour> behaviours = new ArrayList<>();

    /**
     * Constructor
     * Constructs Merchant instance.
     */
    public Merchant(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.addAttribute(AffectionLevelAttribute.AFFECTION_LEVEL, new BaseActorAttribute(1000));
        this.modifyAttribute(AffectionLevelAttribute.AFFECTION_LEVEL, ActorAttributeOperations.DECREASE,1000);
        this.addItemToSell(new BroadSword());
        this.addItemToSell(new DragonslayerGreatsword());
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        behaviours.add(new WanderBehaviour());

    }

    /**
     * Method used to add the specific item that can be sold into the merchant's sellable item
     * @param item the item to sell
     */
    public void addItemToSell(Purchasable item){
        itemsToSell.add(item);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if(otherActor.hasCapability(Status.PLAYER)){
            for(Purchasable item : itemsToSell){
                actions.add(new PurchaseAction(item, this));
            }
        }

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }

        return actions;
    }




}
