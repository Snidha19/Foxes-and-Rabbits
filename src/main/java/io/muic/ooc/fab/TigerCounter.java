package io.muic.ooc.fab;

public class TigerCounter {
    // How many of this type exist in the simulation.
    protected int count;

    /**
     * @return The short description of this type.
     */
    public String getName() {
        return "Tiger";
    }

    /**
     * @return The current count for this type.
     */
    public int getCount() {
        return count;
    }
}
