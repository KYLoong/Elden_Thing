package game.weapons.weaponitems;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilites.Purchasable;
import game.npc.creature.OmenSheep;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class representing Katana WeaponItem in the game.
 * This weapon deal 50 damage with 60% chance to hit the target.
 * @author Loong Kang Yew
 */
public class Katana extends WeaponItem implements Purchasable {
    /**
     * Constructor
     * Constructs Katana instance.
     */
    public Katana(){
        super("Katana", 'j', 50, "hits", 60);
    }

    @Override
    public void purchase(Actor buyer, Actor seller,GameMap map) {
        Random random = new Random();

        //Check for the exits that without any actor
        List<Exit> emptyExits = new ArrayList<>();
        for(Exit exit : map.locationOf(buyer).getExits()){
            if(!exit.getDestination().containsAnActor()){
                emptyExits.add(exit);
            }
        }

        while (!emptyExits.isEmpty()){
            Location randExitLocation = map.locationOf(seller).getExits().get(random.nextInt(8)).getDestination();
            if(!randExitLocation.containsAnActor()) {
                randExitLocation.addActor(new OmenSheep());
                break;
            }
        }

        buyer.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 10);
        buyer.modifyAttributeMaximum(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, 20);
        buyer.deductBalance(canDiscountPrice(500,seller));
        buyer.addItemToInventory(new Katana());
    }

    @Override
    public boolean canPurchase(Actor buyer, Actor seller) {
        return buyer.getBalance() >= canDiscountPrice(500,seller);
    }



}
