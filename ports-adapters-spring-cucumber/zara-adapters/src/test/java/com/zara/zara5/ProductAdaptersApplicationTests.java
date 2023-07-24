package com.zara.zara5;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zara.zara5.controller.BrandController;
import com.zara.zara5.controller.PricingController;
import com.zara.zara5.controller.ProductController;
import com.zara.zara5.service.BrandService;
import com.zara.zara5.service.PricingService;
import com.zara.zara5.service.ProductService;

@SpringBootTest
class ProductAdaptersApplicationTests {
		 @Autowired
		    private BrandController myBrandController;
		 @Autowired
		    private PricingController myPricingController;
		 @Autowired
		    private ProductController myProductController;

		    @Autowired
		    private BrandService brandService;
		    @Autowired
		    private PricingService pricingService;
		    @Autowired
		    private ProductService productService;

		@Test
		void contextLoads() 
			throws Exception {
		        assertThat(myBrandController).isNotNull();
		        assertThat(brandService).isNotNull();
		        assertThat(myPricingController).isNotNull();
		        assertThat(pricingService).isNotNull();
		        assertThat(myProductController).isNotNull();
		        assertThat(productService).isNotNull();
		}

	}