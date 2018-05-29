package com.tpu.ap;

import com.tpu.ap.entity.Population;

public class Main {

    public static void main(String[] args) {
	// write your code here

        String target = "Ихарь-пихарь!";
        Population p = new Population(100, target, 0.01);

        p.countFitness();
        p.sortByFitness();
        p.printBest(5);


    }
}
