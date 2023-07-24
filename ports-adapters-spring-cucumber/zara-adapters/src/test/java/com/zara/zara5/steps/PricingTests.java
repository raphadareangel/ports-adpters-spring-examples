package com.zara.zara5.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zara.zara5.controller.BrandController;
import com.zara.zara5.controller.PricingController;
import com.zara.zara5.controller.ProductController;
import com.zara.zara5.model.Brand;
import com.zara.zara5.model.Pricing;
import com.zara.zara5.model.Product;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest
@CucumberContextConfiguration
public class PricingTests {

    private ResponseEntity<Pricing> response;
    private LocalDateTime applicationDate;
    private Long productId;
    private Long brandId;
    Product p2Product = new Product();
    Product p3Product = new Product();

    @Autowired
    private PricingController pricingController;
    
    @Autowired
    private BrandController brandController;

    private ResponseEntity<Brand> brandResponse;
    
    @Autowired
    private ProductController productController;

    private ResponseEntity<Product> productResponse;
    
    @Given("a POST request is made to \\/brands with the following brand details:")
    public void aPostRequestIsMadeToBrandsWithTheFollowingBrandDetails(DataTable dataTable) {
        Brand brand = new Brand(); 
        brand.setName(dataTable.cell(1, 0));
        brandResponse = brandController.createBrand(brand);
    }

    @Given("a POST request is made to \\/products with the following product details:")
    public void aPostRequestIsMadeToProductsWithTheFollowingProductDetails(DataTable dataTable) {
        Product product = new Product(); 
        product.setName(dataTable.cell(1, 0));
        productResponse = productController.createProduct(product);
    }

    @Given("the following pricing request:")
    public void theFollowingPricingRequest(DataTable dataTable) {
    	Pricing p1 = new Pricing(LocalDateTime.parse(dataTable.cell(1, 0)), LocalDateTime.parse(dataTable.cell(1, 1)), 
    			dataTable.cell(1, 2), Integer.parseInt(dataTable.cell(1, 3)),
    			Float.parseFloat(dataTable.cell(1, 4)), dataTable.cell(1, 6));
    	p1.setProduct(productResponse.getBody());
    	p1.setBrand(brandResponse.getBody());
    	pricingController.createPricing(p1);
    	
    	Pricing p2 = new Pricing(LocalDateTime.parse(dataTable.cell(2, 0)), LocalDateTime.parse(dataTable.cell(2, 1)), 
    			dataTable.cell(2, 2), Integer.parseInt(dataTable.cell(2, 3)),
    			Float.parseFloat(dataTable.cell(2, 4)), dataTable.cell(2, 6));
    	p2Product.setName("Product B");
    	productController.createProduct(p2Product);
    	
    	p2.setProduct(p2Product);
    	p2.setBrand(brandResponse.getBody());
    	pricingController.createPricing(p2);
    	
    	Pricing p3 = new Pricing(LocalDateTime.parse(dataTable.cell(3, 0)), LocalDateTime.parse(dataTable.cell(3, 1)), 
    			dataTable.cell(3, 2), Integer.parseInt(dataTable.cell(3, 3)),
    			Float.parseFloat(dataTable.cell(3, 4)), dataTable.cell(3, 6));
    	p3Product.setName("Product C");
    	productController.createProduct(p3Product);
    	
    	p3.setProduct(p3Product);
    	p3.setBrand(brandResponse.getBody());
    	pricingController.createPricing(p3);
    }

    @When("I send a GET request to \\/pricing\\/list with the parameters:")
    public void sendGetRequest(DataTable dataTable) {
        applicationDate = LocalDateTime.parse(dataTable.cell(1, 0));
        productId = Long.parseLong(dataTable.cell(1, 1));
        brandId = Long.parseLong(dataTable.cell(1, 2));

        response = pricingController.getPricing(applicationDate, productId, brandId);
    }

    @Then("the response status code for pricing should be {int}")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        HttpStatus expectedStatus = HttpStatus.valueOf(expectedStatusCode);
        HttpStatus actualStatus = HttpStatus.valueOf(response.getStatusCode().value());
        assertEquals(expectedStatus, actualStatus, "Unexpected response status code");
    }

    @Then("the response body should contain the pricing details:")
    public void verifyResponseBody(DataTable dataTable) {
        Pricing expectedPricing = new Pricing(LocalDateTime.parse(dataTable.cell(1, 0)), LocalDateTime.parse(dataTable.cell(1, 1)), 
    			dataTable.cell(1, 2), Integer.parseInt(dataTable.cell(1, 3)),
    			Float.parseFloat(dataTable.cell(1, 4)), dataTable.cell(1, 6));
        expectedPricing.setProduct(p3Product);
        expectedPricing.setBrand(brandResponse.getBody());
        Pricing actualPricing = response.getBody();

        // Compare the pricing details
        assertEquals(expectedPricing.getStartDate(), actualPricing.getStartDate());
        assertEquals(expectedPricing.getEndDate(), actualPricing.getEndDate());
        assertEquals(expectedPricing.getPriceList(), actualPricing.getPriceList());
        assertEquals(expectedPricing.getPriority(), actualPricing.getPriority());
        assertEquals(expectedPricing.getPrice(), actualPricing.getPrice());
        assertEquals(expectedPricing.getCurrency(), actualPricing.getCurrency());
        assertEquals(expectedPricing.getProduct().getId(), actualPricing.getProduct().getId());
        assertEquals(expectedPricing.getBrand().getId(), actualPricing.getBrand().getId());
    }

}

