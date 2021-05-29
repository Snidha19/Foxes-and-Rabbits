package io.muic.ooc.fab;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Random;

public abstract class Animal {

    protected static final Random RANDOM = new Random();

    // Whether the animal is alive or not.
    private boolean alive;

    // The animal's position.
    private Location location;

    // The field occupied.
    protected Field field;

    // The animal's age.
    private int age = 0;

    protected int foodLevel;


    /**
     * Create an animal. An animal can be created as a new born (age zero and not
     * hungry) or with a random age and with or without food level(depends on class of Animal).
     *
     * @param randomAge If true, the animal will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public void initialize(boolean randomAge, Field field, Location location){
        setAlive(true);
        this.field = field;
        setLocation(location);
        if (randomAge) {
            age = RANDOM.nextInt(getMaxAge());
        }
    }

    /**
     * Check whether the animal is alive or not.
     *
     * @return true if the animal is still alive.
     */
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Return the animal's location.
     *
     * @return The animal's location.
     */
    public Location getLocation() {
        return location;
    }

    public abstract int getMaxAge();

    /**
     * Increase the age. This could result in the animal's death.
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }

    /**
     * Indicate that the animal is no longer alive. It is removed from the field.
     */
    protected void setDead() {
        setAlive(false);
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Place the animal at the new location in the given field.
     *
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * Generate a number representing the number of births, if it can breed.
     *
     * @return The number of births (may be zero).
     */
    private int breed() {
        int births = 0;
        if (canBreed() && RANDOM.nextDouble() <= getBreedingProbability()) {
            births = RANDOM.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    protected abstract double getBreedingProbability();

    protected abstract int getMaxLitterSize();

    /**
     * An animal can breed if it has reached the breeding age.
     *
     * @return true if the animal can breed, false otherwise.
     */
    private boolean canBreed() {
        return age >= getBreedingAge();
    }

    /** The age at which a fox can start to breed. **/
    protected abstract int getBreedingAge();

    private Animal createYoung(boolean randomAge, Field field, Location location){
        return AnimalFactory.createAnimal(getClass(), field, location);
    }

    /**
     * Check whether or not this rabbit is to give birth at this step. New
     * births will be made into free adjacent locations.
     *
     * @param newAnimals A list to return newly born animals.
     */
    protected void giveBirth(List<Animal> newAnimals) {
        // New animals are born into adjacent locations.
        // Get a list of adjacent free locations.
        List<Location> free = field.getFreeAdjacentLocations(location);
        int births = breed();
        for (int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Animal young = createYoung(false, field, loc);
            newAnimals.add(young);
        }
    }

    protected abstract Location findFood();

    protected abstract Location moveToNewLocation();

    /**
     * This is what the animals does most of the time: it hunts for other animals. In the
     * process, it might breed, die of hunger, or die of old age.
     *
     * @param animals A list to return newly born animals.
     */
    public void act(List<Animal> animals){
        incrementAge();
        if (isAlive()) {
            giveBirth(animals);
            Location newLocation = moveToNewLocation();
            if (newLocation != null) {
                setLocation(newLocation);
            } else {
                // Overcrowding.
                setDead();
            }
        }
    }

}
