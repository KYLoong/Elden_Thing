package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilites.Curable;
import game.status.Status;
import game.actions.CureAction;

/**
 * A class representing a blight covering the ground of the valley.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Loong Kang Yew
 */
public class Blight extends Ground implements Curable {
    public Blight() {
        super('x', "Blight");
        this.addCapability(Status.BLIGHT);
        this.addCapability(Status.CRIMSON_ROT);
    }

    @Override
    public void cure(Actor actor, GameMap map) {
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 50);
        map.locationOf(actor).setGround(new Soil());
    }

    @Override
    public boolean canCure(Actor actor) {
        return actor.getAttribute(BaseActorAttributes.STAMINA) >= 50;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = super.allowableActions(actor, location, direction);
        if(actor.hasCapability(Status.PLAYER) && actor.hasCapability(Status.WITH_TALISMAN) && location.containsAnActor()){
            actions.add(new CureAction(this, actor));
        }
        return actions;
    }
}
