package com.zara.zara4.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zara.zara4.model.Brand;


@Service
public class BrandApplicationService {

    private final BrandService brandService;

    public BrandApplicationService(BrandService brandService) {
        this.brandService = brandService;
    }

    public List<Brand> getAllBrands() {
        return brandService.getAllBrands();
    }

    public Brand getBrandById(Long id) {
        return  brandService.getBrandById(id);
    }

    public Brand createBrand(Brand brand) {
        return brandService.createBrand(brand);
    }

    public Brand updateBrand(Long id, Brand brand) {
        return brandService.updateBrand(id, brand);
    }

    public void deleteBrand(Long id) {
        brandService.deleteBrand(id);
    }
}

