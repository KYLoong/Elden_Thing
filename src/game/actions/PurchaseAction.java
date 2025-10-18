package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilites.Purchasable;

/**
 * A class representing Purchase Action in the game.
 * Purchase Action is the action in the game that performed by the player to purchase item from the merchant.
 * @author Loong Kang Yew
 */
public class PurchaseAction extends Action {
    private Purchasable merchantItem;
    private Actor actor;

    /**
     * Constructor
     * Constructs Purchase Action instance.
     */
    public PurchaseAction(Purchasable merchantItem, Actor actor){
        this.merchantItem = merchantItem;
        this.actor = actor;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        String result = "";

        if (merchantItem.canPurchase(actor, this.actor)){
            merchantItem.purchase(actor, this.actor, map);
            result += actor + " purchased " + this.merchantItem + " from " + this.actor;
        } else {
            result += actor + " do not have enough runes to purchase " + this.merchantItem;
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + merchantItem + " from " + this.actor;
    }
}
