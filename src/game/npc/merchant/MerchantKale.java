package game.npc.merchant;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.MonologueAction;
import game.capabilites.Speakable;
import game.status.Status;

import java.util.ArrayList;
import java.util.List;

import static game.affection.AffectionActionUtils.addGiveActions;
import static game.utils.ConditionUtils.*;


/**
 * A class representing MerchantKale in the game.
 * Player are allows to purchase the purchasable item from the MerchantKale if player are surrounding.
 * Created by:
 * @author Ooi Zhong Dek
 * Modified by:
 * @author Loong Kang Yew
 */
public class MerchantKale extends Merchant implements Speakable {

    /**
     * Constructor
     * Constructs MerchantKale instance.
     */
    public MerchantKale() {
        super("Merchant Kale", 'k', 200);
        this.addCapability(Status.KALE);
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
        if(isLessBalance(actor,500)){
            monologuesPurified.add("Ah, hard times, I see. Keep your head low and your blade sharp.");
        }
        // Condition 2
        if(isInventoryEmpty(actor)){
            monologuesPurified.add("Not a scrap to your name? Even a farmer should carry a trinket or two.");
        }
        // Condition 3
        if(checkCrusedEntities(map, this)){
            monologuesPurified.add("Rest by the flame when you can, friend. These lands will wear you thin.");
        }

        // Condition 4
        monologuesPurified.add("A merchant’s life is a lonely one. But the roads… they whisper secrets to those who listen.");

        actions.add(new MonologueAction(monologuesPurified,this));
    }


}
