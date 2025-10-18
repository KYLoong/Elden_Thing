package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.npc.enemy.boss.BedOfChaos;

/**
 * A class representing GrowPart Action in the game.
 * GrowPart Action is the action in the game that performed by the boss to grow the growablePart.
 * @author Loong Kang Yew
 */
public class GrowPartAction extends Action {
    private BedOfChaos boss;

    public GrowPartAction(BedOfChaos boss) {
        this.boss = boss;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        boss.grow();
        return boss + " is healed";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " grows";
    }
}