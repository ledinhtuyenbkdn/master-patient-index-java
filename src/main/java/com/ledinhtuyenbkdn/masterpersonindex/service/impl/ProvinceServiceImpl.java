package com.ledinhtuyenbkdn.masterpersonindex.service.impl;

import com.ledinhtuyenbkdn.masterpersonindex.model.Province;
import com.ledinhtuyenbkdn.masterpersonindex.repository.ProvinceRepository;
import com.ledinhtuyenbkdn.masterpersonindex.service.ProvinceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceServiceImpl implements ProvinceService {

    private ProvinceRepository provinceRepository;

    public ProvinceServiceImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Optional<Province> findOne(Long id) {
        return provinceRepository.findById(id);
    }

    @Override
    public List<Province> findAll() {
        return provinceRepository.findAll();
    }
}
