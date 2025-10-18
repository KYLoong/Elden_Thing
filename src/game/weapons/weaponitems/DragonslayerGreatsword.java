package game.weapons.weaponitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilites.Purchasable;
import game.npc.creature.GoldenBeetle;
import game.status.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class representing Dragonslayer Greatsword WeaponItem in the game.
 * This weapon deal 70 damage with 75% chance to hit the target.
 * @author Loong Kang Yew
 */
public class DragonslayerGreatsword extends WeaponItem implements Purchasable {


    /**
     * Constructor
     * Constructs Dragonslayer Greatsword instance.
     */
    public DragonslayerGreatsword(){
        super("Dragonslayer Greatsword", 'D', 70, "strikes", 75);
    }

    @Override
    public void purchase(Actor buyer, Actor seller, GameMap map) {
        Random random = new Random();

        //Check for the exits that without any actor
        List<Exit> emptyExits = new ArrayList<>();
        for(Exit exit : map.locationOf(buyer).getExits()){
            if(!exit.getDestination().containsAnActor()){
                emptyExits.add(exit);
            }
        }

        //Condition 1: bought from Sellen
        if(seller.hasCapability(Status.SELLEN)){
            buyer.deductBalance(canDiscountPrice(1500,seller));
            while (!emptyExits.isEmpty()){
                Location randExitLocation = map.locationOf(buyer).getExits().get(random.nextInt(8)).getDestination();
                if(!randExitLocation.containsAnActor()) {
                    randExitLocation.addActor(new GoldenBeetle());
                    break;
                }
            }
        }

        //Condition 2: bought from MerchantKale
        if(seller.hasCapability(Status.KALE)){
            buyer.deductBalance(canDiscountPrice(1700,seller));
            buyer.modifyAttributeMaximum(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 20);
        }

        buyer.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 15);
        buyer.addItemToInventory(new DragonslayerGreatsword());
    }

    @Override
    public boolean canPurchase(Actor buyer, Actor seller) {
        boolean bool = false;

        //Condition 1: bought from Sellen
        if(seller.hasCapability(Status.SELLEN)){
            if(buyer.getBalance() >= canDiscountPrice(1500,seller)){
                bool = true;
            }
        }

        //Condition 2: bought from MerchantKale
        if(seller.hasCapability(Status.KALE)){
            if(buyer.getBalance() >= canDiscountPrice(1700,seller)){
                bool = true;
            }
        }

        return bool;
    }




}
