package game.capabilites;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface Eatable {
    boolean canBeEaten();
    void eat(Actor actor, GameMap map);
}