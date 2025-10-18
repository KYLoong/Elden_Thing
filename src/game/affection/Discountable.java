package game.affection;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for Discountable.
 *
 * @author Ooi Zhong Dek
 */
public interface Discountable {
    /**
     * Calculates the discounted price based on the given actor.
     *
     * @param price the original price before discount
     * @param actor the actor attempting the transaction
     * @return the discounted price
     */
    int canDiscountPrice(int price, Actor actor);

}
