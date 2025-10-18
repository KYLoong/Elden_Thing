package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.attribute.AffectionLevelAttribute;
import game.status.Status;

/**
 * A class that represents the love inside a when ShowLoveBehaviour activated.
 * @author Ooi Zhong Dek
 */
public class Love extends Ground {
    private Actor creator;
    private int countdown = 3;

    public Love(Actor creator){
        super('♡',"Love");
        this.creator = creator;

    }

    @Override
    public void tick(Location location) {
        countdown -= 1;
        if(countdown <= 0){
            location.setGround(new Soil());
        }
        for(Exit exit: location.getExits()){
            // Check the location has actor
            if(exit.getDestination().containsAnActor()) {
                 Actor actor =  exit.getDestination().getActor();
                 if(!actor.hasCapability(Status.PLAYER) && !actor.equals(creator) ){
                     actor.modifyAttribute(AffectionLevelAttribute.AFFECTION_LEVEL, ActorAttributeOperations.INCREASE,5);
                     new Display().println(actor.toString() + " has increase affection level to " + actor.getAttribute(AffectionLevelAttribute.AFFECTION_LEVEL));

                 }

            }
        }
    }
}
