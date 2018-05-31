package com.tpu.ap.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Population {
    private List<Species> generation;
    private List<Species> matingPool;
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

        matingPool = new ArrayList<>();
        generation = new ArrayList<>();
        for (int i = 0; i < Population.populationSize; i++) {
            generation.add(new Species(Population.target.length()));
        }
        this.countFitness();
        this.sortByFitness();
    }

    public void collectMatingPool(){
        matingPool.clear();

        for (int i = 0; i < generation.size(); i++) {
            int nnnn = (int) (generation.get(i).getRawFitness() * 100);
            for (int j = 0; j < nnnn; j++) {
                matingPool.add(generation.get(i));
            }
        }
        if (matingPool.isEmpty()) {
            matingPool.addAll(generation);
        }
    }

    public void computeNextGeneration(){
        generations++;
        this.collectMatingPool();

        for (int i = 0; i < generation.size(); i++) {
            int a = (int)(Math.random() * matingPool.size());
            int b = (int)(Math.random() * matingPool.size());
            Species partnerA = matingPool.get(a);
            Species partnerB = matingPool.get(b);
            Species child = partnerA.crossover(partnerB);
            child.mutate(mutationRate);
            generation.set(i, child);
        }
        this.countFitness();
        this.sortByFitness();
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
        this.printBest(populationSize);
    }


    public void printBest(int num) {
        String stringTarget = "Target | ";

        System.out.format("\n%s%" + target.length() + "s\n", stringTarget, target);
        for (int i = 0; i < target.length() + stringTarget.length(); i++) { System.out.print("-"); } System.out.println();

        printBestNoHeader(num);

    }

    public String getBest(){
        return String.valueOf(generation.get(0).getDnaString());
    }

    public void printGeneration() {
        System.out.println("Generation: " + generations);
    }

    public void printBestNoHeader(int num) {
        for (int i = 0; i < (num < generation.size() ? num: generation.size()); i++) {
            System.out.format("%5d%% | %" + target.length() + "s \n",
                    generation.get(i).getRoundFitness(),
                    generation.get(i).getDnaString());
        }
    }
}
