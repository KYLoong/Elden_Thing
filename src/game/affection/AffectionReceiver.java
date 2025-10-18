package game.affection;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.affection.AffectionItem;

/**
 * Interface for AffectionReceiver.
 * @author Ooi Zhong Dek
 */
public interface AffectionReceiver {
    /**
     * Determines whether the actor can share affection with this receiver.
     *
     * @param actor the actor attempting to share affection
     * @return true if the actor is allowed to share affection, false otherwise
     */
    boolean canShareAffection(Actor actor);
}

