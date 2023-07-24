package com.zara.zara5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zara.zara5.exception.ResourceNotFoundException;
import com.zara.zara5.model.Brand;
import com.zara.zara5.repository.BrandRepository;

@Service
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(Long id) {
        Optional<Brand> brandOptional = brandRepository.findById(id);
        if (brandOptional.isPresent()) {
            return brandOptional.get();
        }
        throw new ResourceNotFoundException("Brand not found with id:" + id);
    }

    @Override
    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand updateBrand(Long id, Brand updatedBrand) {
        Brand brand = getBrandById(id);
        brand.setName(updatedBrand.getName());
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
    	getBrandById(id);
        brandRepository.deleteById(id);
    }
}

