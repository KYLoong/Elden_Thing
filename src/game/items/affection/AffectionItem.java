package game.items.affection;

import edu.monash.fit2099.engine.items.Item;

/**
 * An abstract class representing items that carry affection levels.
 * @author Ooi Zhong Dek
 */
public abstract class AffectionItem extends Item {
    /**
     * The affection level associated with the item.
     */
    private int affectionLevel;

    /**
     * Constructor for AffectionItem.
     *
     * @param name           the name of the item
     * @param displayChar    the character to display for this item
     * @param portable       whether the item can be picked up
     * @param affectionLevel the affection level the item provides or represents
     */
    public AffectionItem(String name,char displayChar, boolean portable, int affectionLevel){
        super(name, displayChar, portable);
        this.affectionLevel = affectionLevel;
    }

    /**
     * Gets the affection level of the item.
     *
     * @return the affection level
     */
    public int getAffectionLevel() {
        return affectionLevel;
    }
}
