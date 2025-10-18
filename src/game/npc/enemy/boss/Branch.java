package game.npc.enemy.boss;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing the Branch.
 * Branch will either grows a branch or a leaf on it and provide 3 bonus attack damage for the bed of chaos boss
 * Created by:
 * @author  Loong Kang Yew
 */
public class Branch implements GrowablePart {
    private List<GrowablePart> parts = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public List<String> grow() {
        List<String> results = new ArrayList<>();
        GrowablePart newPart;

        if ((random.nextInt(100) < 50)) {
            newPart = new Branch();
            results.add("Branch is growing...\n" + "It grows a Branch...\n");
        } else {
            newPart = new Leaf();
            results.add("Branch is growing... \n" + "It grows a Leaf...\n");
        }

        parts.add(newPart);
        results.addAll(newPart.grow());

        return results;
    }

    @Override
    public int getAttackBonus() {
        int bonusDamage = 3;
        for (GrowablePart part : parts) {
            bonusDamage += part.getAttackBonus();
        }
        return bonusDamage;
    }

    @Override
    public void afterGrowthEffect(Actor boss) {
        for (GrowablePart part : parts) {
            part.afterGrowthEffect(boss);
        }
    }
}