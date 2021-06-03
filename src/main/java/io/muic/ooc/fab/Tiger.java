package io.muic.ooc.fab;

public class Tiger extends Animal{
    // Characteristics shared by all tigers (class variables).

    @Override
    public int getMaxAge() {
        return 200;
    }

    @Override
    protected double getBreedingProbability() {
        return AnimalType.TIGER.getBreedingProbability();
    }

    @Override
    protected int getMaxLitterSize() {
        return 2;
    }

    @Override
    protected int getBreedingAge() {
        return 30;
    }

    @Override
    public void initialize(boolean randomAge, Field field, Location location) {
        super.initialize(randomAge, field, location);
        foodLevel = RANDOM.nextInt(AnimalType.RABBIT.getFoodValue());
    }

    @Override
    protected boolean kill(Animal animal) {
        return animal instanceof Rabbit || animal instanceof Fox;
    }
}
