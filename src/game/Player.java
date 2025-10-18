package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.items.affection.Dessert;
import game.items.affection.Flower;
import game.items.affection.Jewelry;
import game.status.Status;
import game.weapons.intrinsicweapons.BareFist;

import static game.utils.ItemInsertUtils.insertMultipleItem;


/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * Loong Kang Yew
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(200));
        this.addCapability(Status.PLAYER);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.setIntrinsicWeapon(new BareFist());
        // Add this to let player can listen to creature that has listen ability
        this.addCapability(Status.LISTEN_ABILITY);
        insertMultipleItem(new Flower(),this,100);
        insertMultipleItem(new Dessert(),this,100);
        insertMultipleItem(new Jewelry(),this,100);
        this.addBalance(10000);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if(!this.isConscious()){
            super.unconscious(map);
            return new DoNothingAction();
        }

        // Easier to visualise the player attributes
        display.println(this.toString());
        display.println("Stamina: (" + this.getAttribute(BaseActorAttributes.STAMINA) + "/" + this.getAttributeMaximum(BaseActorAttributes.STAMINA) + ")");
        display.println("Runes: " + this.getBalance());

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    @Override
    public String unconscious(Actor actor, GameMap map){
        return super.unconscious(actor, map) + "\n" + FancyMessage.YOU_DIED;
    }

    @Override
    public String unconscious(GameMap map){
        return super.unconscious(map) + "\n" + FancyMessage.YOU_DIED;
    }

}
