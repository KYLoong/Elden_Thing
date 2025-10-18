package game.npc.enemy.boss;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.npc.enemy.Enemy;
import game.weapons.intrinsicweapons.BedOfChaosIntrinsicWeapon;
import game.behaviours.GrowPartBehaviour;

import java.util.*;

/**
 * Class representing the Bed of Chaos.
 * Created by:
 * @author Loong Kang Yew
 */
public class BedOfChaos extends Enemy {
    private final int growPartBehaviourPriority = 9;
    private List<GrowablePart> parts = new ArrayList<>();
    private final Random random = new Random();

    /**
     * Constructor of Bed of Chaos.
     */
    public BedOfChaos() {
        super("Bed of Chaos", 'T', 1000);
        behaviours.put(growPartBehaviourPriority, new GrowPartBehaviour(this));
        this.setIntrinsicWeapon(new BedOfChaosIntrinsicWeapon(parts));
    }

    /**
     * The grow action for the boss, as it will grow either a branch or a leaf every single turn.
     * Also perform the effet that the growth part attached to it.
     */
    public void grow() {
        GrowablePart newPart;
        Display display = new Display();

        if (random.nextInt(100) < 50) {
            newPart = new Branch();
            display.println(this +" is growing...\n" + "It grows a Branch...\n");
        } else {
            newPart = new Leaf();
            display.println(this +" is growing...\n" + "It grows a Leaf...\n");
        }

        parts.add(newPart);

        for (String result : newPart.grow()) {
            display.println(result);
        }

        for (GrowablePart part : parts) {
            part.afterGrowthEffect(this);
        }
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }

        return new DoNothingAction();
    }

}
