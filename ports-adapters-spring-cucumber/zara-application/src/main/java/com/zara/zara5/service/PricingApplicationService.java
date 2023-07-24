package com.zara.zara5.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zara.zara5.model.Pricing;
import com.zara.zara5.service.PricingService;

@Service
@Transactional
public class PricingApplicationService {
	private final PricingService pricingService;
	
	public PricingApplicationService(PricingService pricingService) {
		this.pricingService = pricingService;
	}

	public Pricing createPricing(Pricing pricing) {
			return pricingService.createPricing(pricing);
	}

	public Pricing getPricingById(Long id) {
		return pricingService.getPricingById(id);
	}

	public List<Pricing> getAllPricing() {
		return pricingService.getAllPricing();
	}

	public Pricing updatePricing(Long id, Pricing updatedPricing) {
		return pricingService.updatePricing(id, updatedPricing);
	}

	public void deletePricing(Long id) {
		pricingService.deletePricing(id);
	}

	public List<Pricing> getPriceListBaseOnDate(Long productId, Long brandId, LocalDateTime inputDate) {
		return pricingService.getPriceListBaseOnDate(productId, brandId, inputDate);
	}
}
