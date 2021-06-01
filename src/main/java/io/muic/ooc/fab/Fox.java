package io.muic.ooc.fab;

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
    protected boolean kill(Animal animal) {
        return animal instanceof Rabbit;
    }
}
