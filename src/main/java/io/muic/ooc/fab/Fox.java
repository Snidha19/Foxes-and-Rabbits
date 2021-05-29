package io.muic.ooc.fab;

import java.util.List;
import java.util.Random;

public class Fox extends Animal {
    // Characteristics shared by all foxes (class variables).

    @Override
    public int getMaxAge() {
        return 180;
    }

    @Override
    protected double getBreedingProbability() { return AnimalType.FOX.getBreedingProbability(); }

    @Override
    protected int getMaxLitterSize() {
        return 2;
    }

    @Override
    protected int getBreedingAge() {
        return 15;
    }

    @Override
    public void initialize(boolean randomAge, Field field, Location location) {
        super.initialize(randomAge, field, location);
        foodLevel = RANDOM.nextInt(AnimalType.RABBIT.getFoodValue());
    }

    @Override
    protected Location moveToNewLocation(){
        Location newLocation = findFood();
        if (newLocation == null) {  // No food found - try to move to a free location.
            newLocation = field.freeAdjacentLocation(getLocation());
        }
        return newLocation;
    }

    @Override
    public void act(List<Animal> animals) {
        incrementHunger();
        super.act(animals);
    }

    /**
     * Make this fox more hungry. This could result in the fox's death.
     */
    private void incrementHunger() {
        foodLevel--;
        if (foodLevel <= 0) {
            setDead();
        }
    }

    /**
     * Look for rabbits adjacent to the current location. Only the first live
     * rabbit is eaten.
     *
     * @return Where food was found, or null if it wasn't.
     */
    public Location findFood() {
        List<Location> adjacent = field.adjacentLocations(getLocation());
        for (Location where : adjacent) {
            Object animal = field.getObjectAt(where);
            if (animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if (rabbit.isAlive()) {
                    rabbit.setDead();
                    foodLevel = AnimalType.RABBIT.getFoodValue();
                    return where;
                }
            }
        }
        return null;
    }

}
