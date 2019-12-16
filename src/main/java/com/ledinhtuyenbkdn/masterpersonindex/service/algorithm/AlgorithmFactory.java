package com.ledinhtuyenbkdn.masterpersonindex.service.algorithm;

public class AlgorithmFactory {

    public static AlgorithmInterface getAlgorithm(int algorithm) {
        if (algorithm == 1) {
            return new FuzzyAlgorithm();
        }

        return null;
    }
}
