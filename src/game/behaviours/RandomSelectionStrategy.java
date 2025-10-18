package game.behaviours;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actors.Behaviour;

/**
 * Random Behavior Selection Strategy - Randomly selects a behavior
 *
 * @author Hu Longxi
 */
public class RandomSelectionStrategy implements BehaviourSelectionStrategy {

    private Random random = new Random();

    @Override
    public Action selectBehaviour(Map<Integer, Behaviour> behaviours, Actor actor, GameMap map) {
        if (behaviours.isEmpty()) {
            return null;
        }

        // Convert behaviors into a list for random selection
        List<Behaviour> behaviourList = new ArrayList<>(behaviours.values());

        // Choose a random behavior
        Behaviour selectedBehaviour = behaviourList.get(random.nextInt(behaviourList.size()));
        Action action = selectedBehaviour.getAction(actor, map);

        // If the selected action cannot be executed, do nothing
        if (action == null) {
            return new DoNothingAction();
        }

        return action;
    }
}