package com.zara.zara4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zara.zara4.model.Pricing;

@Repository
public interface PricingRepository extends JpaRepository<Pricing, Long> {

}

