package com.zara.zara5.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zara.zara5.exception.GenericException;
import com.zara.zara5.exception.ResourceNotFoundException;
import com.zara.zara5.model.Brand;
import com.zara.zara5.model.Pricing;
import com.zara.zara5.model.Product;
import com.zara.zara5.repository.PricingRepository;

@Service
@Transactional
public class PricingServiceImpl implements PricingService{
	private final PricingRepository pricingRepository;
	private final ProductService productService;
	private final BrandService brandService;

	public PricingServiceImpl(PricingRepository pricingRepository, ProductService productService,
			BrandService brandService) {
		this.pricingRepository = pricingRepository;
		this.productService = productService;
		this.brandService = brandService;
	}

	@Override
	public Pricing createPricing(Pricing pricing) {
		try {
			return pricingRepository.save(pricing);
		} catch (Exception e) {
			throw new GenericException("Check if brand and product exists in system!!");
		}
	}

	@Override
	public Pricing getPricingById(Long id) {
		Optional<Pricing> pricingDB = pricingRepository.findById(id);
		if (pricingDB.isPresent()) {
			return pricingDB.get();
		}
		throw new ResourceNotFoundException("Pricing with id not found:" + id);
	}

	public List<Pricing> getAllPricing() {
		return pricingRepository.findAll();
	}

	@Override
	public Pricing updatePricing(Long id, Pricing updatedPricing) {
		Pricing dbPricing = getPricingById(id);
		dbPricing.setStartDate(updatedPricing.getStartDate());
		dbPricing.setEndDate(updatedPricing.getEndDate());
		dbPricing.setPriceList(updatedPricing.getPriceList());
		dbPricing.setPriority(updatedPricing.getPriority());
		dbPricing.setPrice(updatedPricing.getPrice());
		dbPricing.setBrand(updatedPricing.getBrand());

		return pricingRepository.save(updatedPricing);
	}

	@Override
	public void deletePricing(Long id) {
		getPricingById(id);
		pricingRepository.deleteById(id);
	}

	@Override
	public List<Pricing> getPriceListBaseOnDate(Long productId, Long brandId, LocalDateTime inputDate) {
		List<Pricing> output = new ArrayList<>();
		Product product = productService.getProductById(productId);
		Brand brand = brandService.getBrandById(brandId);

		List<Pricing> pricings = brand.getPrices();
		for (Pricing p : pricings) {
			if (p.getProduct().getId().equals(product.getId()) && p.getStartDate().isBefore(inputDate)
					&& p.getEndDate().isAfter(inputDate)) {
				output.add(p);
			}
		}

		return output;
	}
}
