package game.capabilites;

/**
 * Interface for objects that can be fertilized.
 */
public interface Fertilizable {
    /**
     * Fertilizes the object and returns a message describing the effect.
     * @return String message describing the fertilization effect
     */
    String fertilize();
} 