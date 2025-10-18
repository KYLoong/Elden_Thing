package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilites.Eatable;

/**
 * An action that allows an actor to consume an item or another actor.
 * <p>
 * When executed, the target (e.g., Omen Sheep Egg, Golden Egg, or Golden Beetle) is consumed,
 * and the actor receives the corresponding benefits (e.g., increased max HP, stamina, or runes).
 * The target is removed from the inventory (for items) or the map (for actors).
 * </p>
 * Created by:
 * @author Tan Jie Xuan
 * Modified by:
 * @author  Hu Longxi
 * @author Loong Kang Yew
 */
public class EatAction extends Action {
    private Eatable eatable;

    public EatAction(Eatable eatable){
        this.eatable = eatable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        if (eatable.canBeEaten()){
            eatable.eat(actor, map);
            result += actor + " consumed " + this.eatable.toString() + ".";
        } else {
            result += actor + " cannot consume " + this.eatable.toString();
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.eatable.toString();
    }
}