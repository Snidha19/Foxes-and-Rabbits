package io.muic.ooc.fab;

import java.util.List;

public class Hunter extends Animal{
    // Characteristics shared by all foxes (class variables).

    @Override
    public int getMaxAge() { return 130; }

    @Override
    protected double getBreedingProbability() { return AnimalType.HUNTER.getBreedingProbability(); }

    @Override
    protected int getMaxLitterSize() { return 5; }

    @Override
    protected int getBreedingAge() {
        return 20;
    }

    @Override
    public void initialize(boolean randomAge, io.muic.ooc.fab.Field field, Location location) {
        super.initialize(randomAge, field, location);
        foodLevel = RANDOM.nextInt(AnimalType.TIGER.getFoodValue());
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
        super.act(animals);
    }

    /**
     * Look for rabbits,foxes and tigers adjacent to the current location. Only the first live
     * animal is eaten.
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
            if (animal instanceof Fox) {
                Fox fox = (Fox) animal;
                if (fox.isAlive()) {
                    fox.setDead();
                    foodLevel = AnimalType.FOX.getFoodValue();
                    return where;
                }
            }
            if (animal instanceof Tiger) {
                Tiger tiger = (Tiger) animal;
                if (tiger.isAlive()) {
                    tiger.setDead();
                    foodLevel = AnimalType.TIGER.getFoodValue();
                    return where;
                }
            }
        }
        return null;
    }
}
