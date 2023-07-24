package com.zara.zara5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zara.zara5.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}

