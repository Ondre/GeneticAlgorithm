package com.tpu.ap.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Population {
    private List<Species> generation;
    private static String target;
    private static int populationSize;
    private static int generations = 0;
    private static double mutationRate;

    public Population(int populationSize, String target) {
        this(populationSize,target,0.01);
    }

    public Population(int populationSize, String target, double mutationRate) {
        Population.target = target;
        Population.mutationRate = mutationRate;
        Population.populationSize = populationSize;

        generation = new ArrayList<>();
        for (int i = 0; i < Population.populationSize; i++) {
            generation.add(new Species(Population.target.length()));
        }
    }

    public static double getMutationRate() {
        return mutationRate;
    }

    public static void setMutationRate(double mutationRate) {
        Population.mutationRate = mutationRate;
    }

    public void countFitness(){
        for (Species species : generation) {
            species.countFitness(target);
        }
    }

    public void sortByFitness() {
        generation.sort(Comparator.comparingDouble(Species::getRawFitness).reversed());
    }

    public void printAll() {
        System.out.println("Target: " + target);
        for (Species species : generation) {
            System.out.println("Fitness: " + species.getRoundFitness() + "% : " + String.valueOf(species.getDna()));
        }
    }

    public void printBest(int num) {
        System.out.println("Target: " + target);
        for (int i = 0; i < (num < generation.size() ?  num : generation.size()); i++) {
            System.out.println("Fitness: " + generation.get(i).getRoundFitness() + "% : "
                                    + String.valueOf(generation.get(i).getDna()));

        }
    }
}