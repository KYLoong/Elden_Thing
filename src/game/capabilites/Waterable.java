package game.capabilites;

/**
 * Interface for objects that can be watered.
 * Implementing classes must provide functionality for watering.
 *
 * @author Tan Jie Xuan
 */
public interface Waterable {
    /**
     * Waters the object, resetting its turn counter.
     */
    void water();
} 