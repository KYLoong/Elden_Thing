package game.weapons.weaponitems;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.affection.AffectionReceiver;
import game.affection.Discountable;
import game.attribute.AffectionLevelAttribute;
import game.status.Status;

import java.util.Random;

/**
 * Class representing items that can be used as a weapon.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Loong Kang Yew
 */
public abstract class WeaponItem extends Item implements Weapon, Discountable, AffectionReceiver {
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int damage;
    private int hitRate;
    private final String verb;
    private float damageMultiplier;

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, true);
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) < this.hitRate)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(Math.round(damage * damageMultiplier));

        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = super.allowableActions(otherActor, location);

        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }

        return actions;
    }

    @Override
    public int canDiscountPrice(int price, Actor actor){
        if(canShareAffection(actor)){
            return price / 2;
        }
        return price;

    }

//    public boolean isDiscountable() {
//        return discountable;
//    }

    @Override
    public boolean canShareAffection(Actor actor){
//        System.out.println(this.getAttribute(AffectionLevelAttribute.AFFECTION_LEVEL));
        if(actor.getAttribute(AffectionLevelAttribute.AFFECTION_LEVEL) >= 100){
            return true;
        }
        return false;
    }


}
