package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilites.Curable;

/**
 * A class representing Cure Action in the game.
 * Cure Action is the action in the game that performed by the player to cure the Curable from the Crimson Rot.
 * @author Loong Kang Yew
 */
public class CureAction extends Action {
    private Curable target;
    private Actor actor;

    /**
     * Constructor
     * Constructs Cure Action instance.
     */
    public CureAction(Curable target, Actor actor){
        this.target = target;
        this.actor = actor;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        if (target.canCure(actor)){
            target.cure(actor, map);
            result += actor + " cures " + this.target + " with Talisman successfully.";
        } else {
            result += "Stamina of " + actor + " is not enough to cures " + this.target + ".";
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " cures " + this.target + " with Talisman.";
    }
}
