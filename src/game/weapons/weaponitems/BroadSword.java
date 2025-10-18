package game.weapons.weaponitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilites.Purchasable;
import game.status.Status;

/**
 * A class representing Broad Sword WeaponItem in the game.
 * This weapon deal 30 damage with 50% chance to hit the target.
 * @author Loong Kang Yew
 */
public class BroadSword extends WeaponItem implements Purchasable {

    /**
     * Constructor
     * Constructs Broad Sword instance.
     */
    public BroadSword(){
        super("Broad Sword", 'b', 30,"slashes", 50);

    }

    @Override
    public void purchase(Actor buyer, Actor seller,GameMap map) {

        //Condition 1: bought from Sellen
        if(seller.hasCapability(Status.SELLEN)){
            buyer.deductBalance(canDiscountPrice(100, seller));
            buyer.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 20);
        }

        //Condition 2: bought from MerchantKale
        if(seller.hasCapability(Status.KALE)){
            buyer.deductBalance(canDiscountPrice(150,seller));
            buyer.modifyAttributeMaximum(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 30);
        }

        buyer.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 10);
        buyer.addItemToInventory(new BroadSword());
    }

    @Override
    public boolean canPurchase(Actor buyer, Actor seller) {
        boolean bool = false;

        //Condition 1: bought from Sellen
        if(seller.hasCapability(Status.SELLEN)){
            if(buyer.getBalance() >= canDiscountPrice(100,seller)){
                bool = true;
            }
        }

        //Condition 2: bought from MerchantKale
        if(seller.hasCapability(Status.KALE)){
            if(buyer.getBalance() >= canDiscountPrice(150,seller)){
                bool = true;
            }
        }

        return bool;
    }


}
