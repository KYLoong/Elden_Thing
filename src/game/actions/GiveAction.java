package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attribute.AffectionLevelAttribute;
import game.items.affection.AffectionItem;

public class GiveAction extends Action {
    private AffectionItem affectionItem;
    private Actor character;

    public GiveAction(Actor character,AffectionItem affectionItem){
        this.affectionItem = affectionItem;
        this.character = character;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(affectionItem);
        character.modifyAttribute(AffectionLevelAttribute.AFFECTION_LEVEL, ActorAttributeOperations.INCREASE, affectionItem.getAffectionLevel());
        return "Affection Level of " + character.toString() + " has been increased to " + character.getAttribute(AffectionLevelAttribute.AFFECTION_LEVEL);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " gives " + affectionItem.toString() + " to " + character.toString();
    }
}
