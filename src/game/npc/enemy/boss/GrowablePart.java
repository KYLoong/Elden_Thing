package game.npc.enemy.boss;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.List;

/**
 * Interface for GrowablePart.
 * This is to handle those part that can grow, for example the bed of chaos boss will grow the branch or leaf on it.
 * @author Loong Kang Yew
 */
public interface GrowablePart {
    /**
     * Method used to define what happened after the target is growth.
     * @return A list of String to show what is happening during the growth
     */
    List<String> grow();

    /**
     * Method used to get the attack bonus that the growth part provided.
     * @return A int value that represent the bonus attack damage
     */
    int getAttackBonus();
    /**
     * Method used to define what effect after the path is growth.
     * For example, the leaf of bed of chaos will still heal the boss every turn after the grow turn
     * @param boss the Boss, bed of chaos that performed the growPart action
     */
    void afterGrowthEffect(Actor boss);
}
