package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilites.Plantable;

/**
 * A class representing Plant Seed Action in the game.
 * Plant Seed Action is the action in the game that performed by the player to plant the plantable on the soil.
 * @author Loong Kang Yew
 */
public class PlantSeedAction extends Action {
    private Plantable seed;

    /**
     * Constructor
     * Constructs Plant Seed Action instance.
     */
    public PlantSeedAction(Plantable seed){
        this.seed = seed;
    }

    @Override
    public String execute(Actor actor, GameMap gameMap){
        String result = "";

        if (seed.canPlant(actor)){
            gameMap.locationOf(actor).setGround(seed.afterPlanted());
            seed.plant(actor, gameMap.locationOf(actor), gameMap);
            actor.removeItemFromInventory(seed.getSeed());
            result += actor + " plants " + this.seed.toString() + " on the ground.";
        } else {
            result += "Stamina of " + actor + " is not enough to plants " + this.seed.toString();
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " plants " + this.seed.toString();
    }
}
