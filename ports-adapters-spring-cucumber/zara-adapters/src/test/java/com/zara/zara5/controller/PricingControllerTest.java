package com.zara.zara5.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zara.zara5.model.Pricing;
import com.zara.zara5.service.PricingApplicationService;
import com.zara.zara5.util.PricingMock;

@WebMvcTest(PricingController.class)
class PricingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PricingApplicationService pricingService;
    
    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mapper.registerModule(new JavaTimeModule());
    }
    
    @Test
    @DisplayName("request at 10:00 on day 14 of product 35455 for brand 1")
    void testGetPricing1() throws Exception {
        LocalDateTime inputDate = LocalDateTime.of(2023, 6, 14, 10, 00);
        List<Pricing> pricings = PricingMock.getData();

        when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate))
                .thenReturn(pricings);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing/list")
                .param("applicationDate", "2023-06-14T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricings.get(0).getPrice(), pricingResponse.getPrice());
    }
    
    @Test
    @DisplayName("request at 4:00 p.m. on day 14 of product 35455 for brand 1")
    void testGetPricing2() throws Exception {
        LocalDateTime inputDate = LocalDateTime.of(2023, 6, 14, 16, 00);
        List<Pricing> pricings = PricingMock.getData();

        when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate))
                .thenReturn(pricings);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing/list")
                .param("applicationDate", "2023-06-14T16:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricings.get(0).getPrice(), pricingResponse.getPrice());
    }
    
    @Test
    @DisplayName("request at 9:00 p.m. on day 14 of product 35455 for brand 1")
    void testGetPricing3() throws Exception {
        LocalDateTime inputDate = LocalDateTime.of(2023, 6, 14, 21, 00);
        List<Pricing> pricings = PricingMock.getData();

        when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate))
                .thenReturn(pricings);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing/list")
                .param("applicationDate", "2023-06-14T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricings.get(0).getPrice(), pricingResponse.getPrice());
    }
    
    @Test
    @DisplayName("request at 10:00 on day 15 of product 35455 for brand 1")
    void testGetPricing4() throws Exception {
        LocalDateTime inputDate = LocalDateTime.of(2023, 6, 15, 10, 00);
        List<Pricing> pricings = PricingMock.getData();

        when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate))
                .thenReturn(pricings);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing/list")
                .param("applicationDate", "2023-06-15T10:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricings.get(0).getPrice(), pricingResponse.getPrice());
    }
    
    @Test
    @DisplayName("request at 9:00 p.m. on the 16th of product 35455 for brand 1")
    void testGetPricing5() throws Exception {
        LocalDateTime inputDate = LocalDateTime.of(2023, 6, 16, 21, 00);
        List<Pricing> pricings = PricingMock.getData();

        when(pricingService.getPriceListBaseOnDate(35455L, 1L, inputDate))
                .thenReturn(pricings);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pricing/list")
                .param("applicationDate", "2023-06-16T21:00:00")
                .param("productId", "35455")
                .param("brandId", "1")).andReturn();
        
        Pricing pricingResponse = extractResponse(result, Pricing.class);
        assertThat(pricingResponse).isNotNull();
		assertEquals(pricings.get(0).getPrice(), pricingResponse.getPrice());
    }
    
    private <T> T extractResponse(MvcResult mvcResult, Class<T> clazz) throws Exception {
		return mapper.readValue(mvcResult.getResponse().getContentAsString(), clazz);
	}

}

