package com.zara.zara5.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.zara.zara5.model.Brand;
import com.zara.zara5.model.Pricing;
import com.zara.zara5.model.Product;

public class PricingMock {
	
	
	public static List<Pricing> getData() {
		
		LocalDateTime startDate = LocalDateTime.of(2023, 6, 14, 10, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 6, 14, 23, 59, 59);
        
		Brand brand1 = new Brand();
		brand1.setName("ZARA");

		Product product1 = new Product(1L, "Product 1");
		Product product2 = new Product(2L, "Product 2");
		
		Pricing pricing1 = new Pricing();
		pricing1.setBrand(brand1);
		pricing1.setPrice(35.50f);
		pricing1.setPriority(1);
		pricing1.setStartDate(startDate);
		pricing1.setEndDate(endDate);
		// Add product to pricing
		pricing1.setProduct(product1);

		Pricing pricing2 = new Pricing();
		pricing2.setBrand(brand1);
		pricing2.setPrice(25.45f);
		pricing2.setPriority(1);
		pricing2.setStartDate(startDate);
		pricing2.setEndDate(endDate);
		// Add product to pricing
		pricing2.setProduct(product2);

		// Add pricing to the brand
		brand1.getPrices().add(pricing1);
		brand1.getPrices().add(pricing2);

		List<Pricing> list = new ArrayList<Pricing>();
		list.add(pricing1);
		list.add(pricing2);
		
		return list;

	}

}
