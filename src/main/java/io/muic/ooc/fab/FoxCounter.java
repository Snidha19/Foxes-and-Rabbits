package io.muic.ooc.fab;

public class FoxCounter {
    // How many of this type exist in the simulation.
    private int count;

    /**
     * @return The short description of this type.
     */
    public String getName() {
        return "Fox";
    }

    /**
     * @return The current count for this type.
     */
    public int getCount() {
        return count;
    }

    /**
     * Increment the current count by one.
     */
    public void increment() {
        count++;
    }

    /**
     * Reset the current count to zero.
     */
    public void reset() {
        count = 0;
    }
}
