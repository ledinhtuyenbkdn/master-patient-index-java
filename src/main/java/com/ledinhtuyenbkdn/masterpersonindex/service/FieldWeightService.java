package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.model.FieldWeight;

import java.util.List;

public interface FieldWeightService {

    List<FieldWeight> update(List<FieldWeight> fieldWeights);

    List<FieldWeight> findAll();
}
