package com.zara.zara5.controller;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zara.zara5.exception.ResourceNotFoundException;
import com.zara.zara5.model.Pricing;
import com.zara.zara5.service.PricingApplicationService;

@RestController
@RequestMapping("/pricing")
public class PricingController {
	
	private final PricingApplicationService pricingApplicationService;

    public PricingController(PricingApplicationService pricingApplicationService) {
        this.pricingApplicationService = pricingApplicationService;
    }

	@GetMapping("/list")
    public ResponseEntity<Pricing> getPricing(
            @RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId) {

        List<Pricing> pricingList = pricingApplicationService.getPriceListBaseOnDate(productId, brandId, applicationDate);
        
        if(pricingList.isEmpty()) 
        	throw new ResourceNotFoundException("No Pricing list found for product:" + productId +
        		" brand:" +brandId +" and dates:" + applicationDate);
		
        //function to get pricing for highest priority
        Pricing pricing = pricingList.stream().min(Comparator.comparingInt(Pricing::getPriority)).orElseThrow(() -> new RuntimeException("No pricing data available."));
 
        
        return new ResponseEntity<>(pricing, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<Pricing> createPricing(@RequestBody Pricing pricing) {
        Pricing createdPricing = pricingApplicationService.createPricing(pricing);
        return new ResponseEntity<>(createdPricing, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pricing> getPricingById(@PathVariable Long id) {
    	return new ResponseEntity<>(pricingApplicationService.getPricingById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Pricing>> getAllPricing() {
        List<Pricing> pricings = pricingApplicationService.getAllPricing();
        return new ResponseEntity<>(pricings, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pricing> updatePricing(@PathVariable Long id, @RequestBody Pricing pricing) {
            return new ResponseEntity<>(pricingApplicationService.updatePricing(id, pricing), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePricing(@PathVariable Long id) {
        pricingApplicationService.getPricingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

