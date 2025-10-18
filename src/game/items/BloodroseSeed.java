package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PlantSeedAction;
import game.crops.Bloodrose;
import game.capabilites.Plantable;
import game.status.Status;

/**
 * A class representing a Bloodrose Seed item that can be planted on the ground to grow a Bloodrose tree.
 * @author Loong Kang Yew
 */
public class BloodroseSeed extends Item implements Plantable {
    /**
     * Constructor
     * Constructs Bloodrose Seed instance.
     */
    public BloodroseSeed(){
        super("Bloodrose Seed", '*', true);
    }

    @Override
    public void plant(Actor actor,Location location, GameMap map){
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 75);
        location.getActor().hurt(5);
    }

    @Override
    public boolean canPlant(Actor actor){return actor.getAttribute(BaseActorAttributes.STAMINA) >= 75;}

    @Override
    public Item getSeed(){
        return this;
    }

    @Override
    public Ground afterPlanted(){
        return new Bloodrose();
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);

        if (map.locationOf(owner).getGround().hasCapability(Status.SOIL) ){
            actions.add(new PlantSeedAction(this));
        }

        return actions;
    }
}
