package io.muic.ooc.fab;

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
    protected void setDead() {
    }

    @Override
    public void initialize(boolean randomAge, io.muic.ooc.fab.Field field, Location location) {
        super.initialize(randomAge, field, location);
        foodLevel = RANDOM.nextInt(AnimalType.TIGER.getFoodValue());
    }

    @Override
    protected boolean kill(Animal animal) {
        return  animal instanceof Rabbit || animal instanceof Fox || animal instanceof Tiger;
    }
}
