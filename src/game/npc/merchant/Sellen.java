package game.npc.merchant;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.MonologueAction;
import game.capabilites.Speakable;
import game.status.Status;
import game.weapons.weaponitems.Katana;

import java.util.ArrayList;
import java.util.List;

import static game.affection.AffectionActionUtils.addGiveActions;

/**
 * A class representing Sellen in the game.
 * Player are allows to purchase the purchasable item from the Sellen if player are surrounding.
 * Created by:
 * @author Ooi Zhong Dek
 * Modified by:
 * @author Loong Kang Yew
 */
public class Sellen extends Merchant implements Speakable {
    private List<String> monologues;

    /**
     * Constructor
     * Constructs Sellen instance.
     */
    public Sellen() {
        super("Sellen", 's', 150);
        super.addItemToSell(new Katana());
        this.addCapability(Status.SELLEN);

    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if(canSpeak(otherActor)){
            speak(otherActor, map,actions);
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
        monologuesPurified.add("The academy casts out those it fears. Yet knowledge, like the starts, cannot be bound forever.");
        monologuesPurified.add( "You sense it too, don't you? The Glintstone hums, even now.");
        actions.add(new MonologueAction(monologuesPurified,this));
    }
}
