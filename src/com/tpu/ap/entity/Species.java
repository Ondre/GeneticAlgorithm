package com.tpu.ap.entity;

public class Species {
    private char[] dna;
    private double fitness = 0;

    private static char[] symbolPool;

    static {
        symbolPool = new char[99];
        for (int i = 0; i < 64; i++) {
            symbolPool[i] = (char) ('А' + i);
        }
        symbolPool[64] = 'ё';
        symbolPool[65] = 'Ё';
        symbolPool[66] = ' ';
        for (int i = 0; i < 32; i++) {
            symbolPool[67 + i] = (char) ((int)'!' + i);
        }
    }

    public char[] getDna() {
        return dna;
    }

    public String getDnaAsString() {
        return String.valueOf(dna);
    }

    public int getRoundFitness() {
        return (int) Math.round(fitness * 100);
    }
    public double getRawFitness() {
        return fitness;
    }


    public Species(int speciesSize) {
        dna = new char[speciesSize];
        for (int i = 0; i < speciesSize; i++) {
            dna[i] = symbolPool[(int) (Math.random() * symbolPool.length)];
        }
    }

    public void countFitness(String target) {
        fitness = 0;
        for (int i = 0; i < dna.length; i++) {
            if (dna[i] == target.charAt(i)) fitness++;
        }
        fitness = fitness / target.length();
    }

    // Crossover
    Species crossover(Species partner) {
        // A new child
        Species child = new Species(dna.length);

        int midpoint = (int) (Math.random() * dna.length); // Pick a midpoint

        // Half from one, half from the other
        for (int i = 0; i < dna.length; i++) {
            if (i > midpoint) child.dna[i] = dna[i];
            else              child.dna[i] = partner.dna[i];
        }
        return child;
    }

    // Based on a mutation probability, picks a new random character
    void mutate(double mutationRate) {
        for (int i = 0; i < dna.length; i++) {
            if (Math.random() < mutationRate) {
                dna[i] = symbolPool[(int) (Math.random() * symbolPool.length)];
            }
        }
    }
}
