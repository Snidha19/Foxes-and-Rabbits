package io.muic.ooc.fab;

import java.util.List;
import java.util.Random;

public class Rabbit extends Animal {
    // Characteristics shared by all rabbits (class variables).

    @Override
    protected int getMaxLitterSize() {
        return 4;
    }

    @Override
    public int getMaxAge() {
        return 40;
    }

    @Override
    protected int getBreedingAge() {
        return 5;
    }

    @Override
    protected double getBreedingProbability() {
        return AnimalType.RABBIT.getBreedingProbability();
    }

    @Override
    protected Location moveToNewLocation() {
        return field.freeAdjacentLocation(getLocation());
    }

}
