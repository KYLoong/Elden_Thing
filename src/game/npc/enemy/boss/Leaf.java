package game.npc.enemy.boss;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Leaf.
 * Leaf will heal the bed of chaos boss for 5 hp every turn and provide 1 bonus attack damage for it.
 * Created by:
 * @author  Loong Kang Yew
 */
public class Leaf implements GrowablePart {

    @Override
    public List<String> grow() {
        List<String> results = new ArrayList<>();
        results.add("Leaf heals the boss");
        return results;
    }

    @Override
    public int getAttackBonus() {
        int bonusDamage = 1;

        return bonusDamage;
    }

    @Override
    public void afterGrowthEffect(Actor boss) {
        boss.heal(5);
    }
}
