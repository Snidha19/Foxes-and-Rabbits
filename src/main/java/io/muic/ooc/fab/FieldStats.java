package io.muic.ooc.fab;

import java.util.HashMap;

public class FieldStats {
    // Whether the counters are currently up to date.
    private boolean countsValid = true;

    private FoxCounter foxCount = new FoxCounter();
    private RabbitCounter rabbitCount = new RabbitCounter();
    private TigerCounter tigerCount = new TigerCounter();
    private HunterCounter hunterCount = new HunterCounter();

    /**
     * Get details of what is in the field.
     *
     * @param field
     * @return A string describing what is in the field.
     */
    public String getPopulationDetails(Field field) {
        StringBuilder buffer = new StringBuilder();
        if (!countsValid) {
            generateCounts(field);
        }
        buffer.append(String.format("%s: %d ", foxCount.getName(), foxCount.getCount()));
        buffer.append(String.format("%s: %d ", rabbitCount.getName(), rabbitCount.getCount()));
        buffer.append(String.format("%s: %d ", tigerCount.getName(), tigerCount.getCount()));
        buffer.append(String.format("%s: %d ", hunterCount.getName(), hunterCount.getCount()));
        return buffer.toString();
    }

    /**
     * Invalidate the current set of statistics; reset all counts to zero.
     */
    public void reset() {
        countsValid = false;
        foxCount.count = 0;
        rabbitCount.count = 0;
        tigerCount.count = 0;
        hunterCount.count = 0;
    }

    /**
     * Increment the count for one class of animal.
     *
     * @param animal The class of animal to increment.
     */
    public void incrementCount(Object animal) {
        if(animal instanceof Fox) foxCount.count++;
        else if(animal instanceof Rabbit) rabbitCount.count++;
        else if(animal instanceof Tiger) tigerCount.count++;
        else if(animal instanceof Hunter) hunterCount.count++;
    }

    /**
     * Indicate that an animal count has been completed.
     */
    public void countFinished() {
        countsValid = true;
    }

    /**
     * Determine whether the simulation is still viable. I.e., should it
     * continue to run.
     *
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field) {
        // How many counts are non-zero.
        int nonZero = foxCount.getCount() + rabbitCount.getCount() + tigerCount.getCount() + hunterCount.getCount();
        if (!countsValid) {
            generateCounts(field);
        }
        return nonZero > 1;
    }

    /**
     * Generate counts of the number of foxes and rabbits. These are not kept up
     * to date as foxes and rabbits are placed in the field, but only when a
     * request is made for the information.
     *
     * @param field The field to generate the stats for.
     */
    private void generateCounts(Field field) {
        reset();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if (animal != null) {
                    incrementCount(animal);
                }
            }
        }
        countsValid = true;
    }
}
