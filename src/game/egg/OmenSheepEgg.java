package game.egg;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.*;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.EatAction;
import game.capabilites.Eatable;
import game.capabilites.Hatchable;
import game.npc.creature.OmenSheep;
import game.status.Status;

/**
 * A class representing an Omen Sheep Egg item.
 * <p>
 * The egg is produced by Omen Sheep every 7 turns and can be picked up by the player.
 * If left on the ground for more than 3 turns, it hatches into a new Omen Sheep.
 * If picked up by the player, the egg can be eaten to increase the player's maximum health by 10 and heal 10 HP.
 * </p>
 */
public class OmenSheepEgg extends Egg implements Eatable, Hatchable {
    private int turnsOnGround = 0;

    public OmenSheepEgg() {
        super("Omen Sheep Egg", '0', true, 3);
    }

    @Override
    public void tick(Location currentLocation) {
        if (!currentLocation.containsAnActor()) {
            turnsOnGround++;
            if (canHatch()) {
                hatch(currentLocation);
            }
        }
    }

    @Override
    public boolean canHatch() {
        return turnsOnGround > turnstoHatch;
    }

    @Override
    public void hatch(Location location) {
        location.addActor(new OmenSheep());
        location.removeItem(this);
    }

    @Override
    public boolean canBeEaten() {
        return true;
    }

    @Override
    public void eat(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.PLAYER)) {
            actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, 10);
            actor.heal(10);
            actor.removeItemFromInventory(this);
        }
    }


    @Override
    public ActionList allowableActions(Actor actor, GameMap map) {
        ActionList actions = super.allowableActions(actor, map);
        if (actor.hasCapability(Status.PLAYER)) {
            actions.add(new EatAction(this));
        }
        return actions;
    }
}