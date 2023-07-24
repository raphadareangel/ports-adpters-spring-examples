package com.zara.zara5.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zara.zara5.controller.BrandController;
import com.zara.zara5.exception.ResourceNotFoundException;
import com.zara.zara5.model.Brand;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest
public class BrandTests {

	@Autowired
    private BrandController brandController;

    private ResponseEntity<Brand> response;
    
    private Brand brand = new Brand();
    
    //Create
    
    @When("a POST request is made to \\/brands with the following brand details:")
    public void aPostRequestIsMadeToBrandsWithTheFollowingBrandDetails(DataTable dataTable) {
        brand.setName(dataTable.cell(1, 0));
        response = brandController.createBrand(brand);
    }

    @Then("the response body should contain the created brand details")
    public void theResponseBodyShouldContainTheCreatedBrandDetails() {
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    
    //get 
    @When("a GET request is made to \\/brands\\/{int}")
    public void aGetRequestIsMadeToBrandsId(int id) {
        response = brandController.getBrandById((long) id);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Then("the response body should contain the brand details")
    public void theResponseBodyShouldContainTheBrandDetails() {
        assertNotNull(response.getBody());
    }
    
    //update
    @When("I update the brand with id {int}")
    public void updateBrand(long brandId) {
        // Build the updated brand object
        brand.setName("Updated Brand Name");

        ResponseEntity<Brand> response = brandController.updateBrand(brandId, brand);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Then("the brand with id {int} should have the updated values")
    public void verifyUpdatedBrand(long brandId) {
        response = brandController.getBrandById(brandId);
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().getId() == brandId;
        assert response.getBody().getName().equals(brand.getName());
    }
    
    //delete
    @When("I delete the brand with id {int}")
    public void deleteBrand(long brandId) {
    	ResponseEntity<Void> response = brandController.deleteBrand(brandId);
        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }

    @Then("the brand with id {int} should be deleted")
    public void verifyDeletedBrand(long brandId) {
        try{ 
        	brandController.getBrandById(brandId);
        }catch (ResourceNotFoundException ex) {
			assertEquals("Brand not found with id:1", ex.getMessage());
		}
        
    }
}

