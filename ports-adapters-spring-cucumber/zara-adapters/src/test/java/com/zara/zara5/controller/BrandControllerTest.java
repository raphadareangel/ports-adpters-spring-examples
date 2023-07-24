package com.zara.zara5.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zara.zara5.model.Brand;
import com.zara.zara5.service.BrandApplicationService;

class BrandControllerTest {

    @Mock
    private BrandApplicationService brandService;

    private BrandController brandController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        brandController = new BrandController(brandService);
    }

    @Test
    void testGetAllBrands() {
        Brand brand1 = new Brand(1L, "Brand 1");
        Brand brand2 = new Brand(2L, "Brand 2");
        List<Brand> brands = Arrays.asList(brand1, brand2);

        when(brandService.getAllBrands()).thenReturn(brands);

        List<Brand> result = brandController.getAllBrands();

        assertEquals(brands, result);
        verify(brandService, times(1)).getAllBrands();
    }

    @Test
    void testGetBrandById() {
        Long brandId = 1L;
        Brand brand = new Brand(brandId, "Brand 1");

        when(brandService.getBrandById(brandId)).thenReturn(brand);

        ResponseEntity<Brand> response = brandController.getBrandById(brandId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(brand, response.getBody());
        verify(brandService, times(1)).getBrandById(brandId);
    }

    @Test
    void testCreateBrand() {
        Brand brand = new Brand(null, "Brand 1");
        Brand createdBrand = new Brand(1L, "Brand 1");

        when(brandService.createBrand(brand)).thenReturn(createdBrand);

        ResponseEntity<Brand> response = brandController.createBrand(brand);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdBrand, response.getBody());
        verify(brandService, times(1)).createBrand(brand);
    }

    @Test
    void testUpdateBrand() {
        Long brandId = 1L;
        Brand updatedBrand = new Brand(brandId, "Updated Brand 1");

        when(brandService.updateBrand(brandId, updatedBrand)).thenReturn(updatedBrand);

        ResponseEntity<Brand> response = brandController.updateBrand(brandId, updatedBrand);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBrand, response.getBody());
        verify(brandService, times(1)).updateBrand(brandId, updatedBrand);
    }

    @Test
    void testDeleteBrand() {
        Long brandId = 1L;

        ResponseEntity<Void> response = brandController.deleteBrand(brandId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(brandService, times(1)).deleteBrand(brandId);
    }
}

