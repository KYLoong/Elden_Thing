package game.weapons.intrinsicweapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.npc.enemy.boss.GrowablePart;

import java.util.List;
import java.util.Random;

/**
 * Class representing an intrinsic weapon called a Bed of Chaos Intrinsic Weapon.
 * This intrinsic weapon deals 25 damage points with a 75% chance
 * and the damage will increase by 3 for each branch the Bed of Chaos have
 * and the damage will increase by 1 for each leaf the Bed of Chaos have
 * to hit the target.
 * @author Loong Kang Yew
 */
public class BedOfChaosIntrinsicWeapon extends IntrinsicWeapon {
    private final List<GrowablePart> parts;

    /**
     * Constructor
     * Constructs Bed of Chaos Intrinsic Weapon instance.
     * @param parts The bossPart that the Bed of Chaos have
     */
    public BedOfChaosIntrinsicWeapon(List<GrowablePart> parts) {
        super(25, "smashes", 75);
        this.parts = parts;
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        int bonusDamage = 0;

        for (GrowablePart part : parts) {
            bonusDamage += part.getAttackBonus();
        }

        int newDamage = 25 + bonusDamage;

        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(newDamage);
        return String.format("%s %s %s for %d damage", attacker, verb, target, newDamage);
    }
}
