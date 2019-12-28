package com.ledinhtuyenbkdn.masterpersonindex.service;

import com.ledinhtuyenbkdn.masterpersonindex.model.Province;

import java.util.List;
import java.util.Optional;

public interface ProvinceService {

    Optional<Province> findOne(Long id);

    List<Province> findAll();
}
