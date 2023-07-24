package com.zara.zara5.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zara.zara5.model.Brand;
import com.zara.zara5.service.BrandApplicationService;

@RestController
@RequestMapping("/brand")
public class BrandController {
	private final BrandApplicationService brandApplicationService;

	public BrandController(BrandApplicationService brandApplicationService) {
		this.brandApplicationService = brandApplicationService;
	}

	@GetMapping
	public List<Brand> getAllBrands() {
		return brandApplicationService.getAllBrands();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
		Brand brand = brandApplicationService.getBrandById(id);
		return ResponseEntity.ok(brand);
	}

	@PostMapping
	public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
		Brand createdBrand = brandApplicationService.createBrand(brand);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Brand> updateBrand(@PathVariable Long id, @RequestBody Brand updatedBrand) {
		Brand brand = brandApplicationService.updateBrand(id, updatedBrand);
		return ResponseEntity.ok(brand);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBrand(@PathVariable Long id) {
		brandApplicationService.deleteBrand(id);
		return ResponseEntity.noContent().build();
	}
}
