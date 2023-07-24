package com.zara.zara4.service;

import java.util.List;

import com.zara.zara4.model.Brand;

public interface BrandService {


    public List<Brand> getAllBrands();

    public Brand getBrandById(Long id);

    public Brand createBrand(Brand brand);

    public Brand updateBrand(Long id, Brand updatedBrand);

    public void deleteBrand(Long id);
}

