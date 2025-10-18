package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.npc.enemy.boss.BedOfChaos;
import game.actions.GrowPartAction;

/**
 * A generic behaviour for entities that can grow part on it.
 * This behaviour can be used by any entity that will grow part on it.
 * @author Loong Kang Yew
 */
public class GrowPartBehaviour implements Behaviour {
    private BedOfChaos boss;

    /**
     * Constructor
     * Constructs GrowPartBehaviour instance.
     * @param boss The boss that can grow part on it. For example, Bed of Chaos
     */
    public GrowPartBehaviour(BedOfChaos boss) {
        this.boss = boss;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        for (Exit exit : here.getExits()) {
            if (!exit.getDestination().containsAnActor()) {
                return new GrowPartAction(boss);
            }
        }

        return null;
    }
}
