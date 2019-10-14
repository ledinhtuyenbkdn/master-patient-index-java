package com.ledinhtuyenbkdn.masterpersonindex.service.algorithm;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class FuzzyAlgorithm implements AlgorithmInterface {

    @Override
    public double ratio(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            return 0;
        }
        return FuzzySearch.ratio(s1, s2);
    }
}
