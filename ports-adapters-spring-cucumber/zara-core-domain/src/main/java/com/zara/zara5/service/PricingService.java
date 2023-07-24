package com.zara.zara5.service;

import java.time.LocalDateTime;
import java.util.List;

import com.zara.zara5.model.Pricing;

public interface PricingService {

	public Pricing createPricing(Pricing pricing) ;

	public Pricing getPricingById(Long id) ;

	public List<Pricing> getAllPricing() ;

	public Pricing updatePricing(Long id, Pricing updatedPricing) ;

	public void deletePricing(Long id) ;

	public List<Pricing> getPriceListBaseOnDate(Long productId, Long brandId, LocalDateTime inputDate) ;
}
