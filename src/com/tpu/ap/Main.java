package com.tpu.ap;

import com.tpu.ap.entity.Population;
import com.tpu.ap.entity.Species;

public class Main {

    public static void main(String[] args) {
	// write your code here

        String target = "To be or not to be? - That is the question.";
        Population p = new Population(1000, target, 0.01);

        while (!p.getBest().equals(target)){
            p.printBestNoHeader(1);
            p.computeNextGeneration();
        }
        p.printBestNoHeader(1);
    }
}
