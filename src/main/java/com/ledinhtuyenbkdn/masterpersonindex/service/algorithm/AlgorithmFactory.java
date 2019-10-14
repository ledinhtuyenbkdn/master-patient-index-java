package com.ledinhtuyenbkdn.masterpersonindex.service.algorithm;

public class AlgorithmFactory {
    public static final int FUZZY_SEARCH = 1;

    public static AlgorithmInterface getAlgorithm(int algorithm) {
        if (algorithm == FUZZY_SEARCH) {
            return new FuzzyAlgorithm();
        }

        return null;
    }
}
