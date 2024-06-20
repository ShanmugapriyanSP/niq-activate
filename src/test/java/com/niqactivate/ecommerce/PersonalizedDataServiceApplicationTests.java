package com.niqactivate.ecommerce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niqactivate.ecommerce.controller.ExternalController;
import com.niqactivate.ecommerce.controller.InternalController;
import com.niqactivate.ecommerce.dto.*;
import com.niqactivate.ecommerce.model.Product;
import com.niqactivate.ecommerce.model.Shopper;
import com.niqactivate.ecommerce.model.ShopperProduct;
import com.niqactivate.ecommerce.service.ProductService;
import com.niqactivate.ecommerce.service.ShopperService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest({ExternalController.class, InternalController.class})
@AutoConfigureMockMvc
public class PersonalizedDataServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@MockBean
	private ShopperService shopperService;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new ExternalController(productService), new InternalController(productService, shopperService)).build();
	}

	@Test
	void getProductsByShopper() throws Exception {
		Product product = new Product((long) 1, "P-100", "Babies", "Babyom");
		given(productService.findProductsByShopper(Mockito.any(ProductFilterDto.class)))
				.willReturn(Collections.singletonList(product));

		mockMvc.perform(get("/api/external/products")
						.param("shopperId", "s-1000")
						.param("category", "Babies")
						.param("brand", "Babyom")
						.param("limit", "10")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("[{\"productId\":\"P-100\",\"category\":\"Babies\",\"brand\":\"Babyom\"}]"));
	}

	@Test
	void saveShopper() throws Exception {
		// Request
		ProductShelfItemDto productShelfItemDto = ProductShelfItemDto.builder()
				.productId("MD-543564697")
				.relevancyScore(31.089209569320897)
				.build();
		ShopperRequestDto requestDto = ShopperRequestDto.builder()
				.shopperId("s-1000")
				.shelf(Collections.singletonList(productShelfItemDto))
				.build();

		// Response
		Shopper shopper = new Shopper((long) 1, "s-1000");
		Product product = new Product((long) 1, "P-100", "Babies", "Babyom");
		ShopperProduct shopperProduct = ShopperProduct.builder()
				.shopper(shopper)
				.product(product)
				.build();
		ShopperProductResponseDto responseDto = ShopperProductResponseDto.builder()
				.shopperId("s-1000")
				.shopperProduct(Collections.singleton(shopperProduct))
				.build();


		given(shopperService.saveShopperPersonalizedList(Mockito.any(ShopperRequestDto.class)))
				.willReturn(responseDto);

		mockMvc.perform(post("/api/internal/shopper")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDto)))
				.andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
	}

	@Test
	void saveProduct() throws Exception {
		ProductMetadataRequestDto requestDto = new ProductMetadataRequestDto("P-100", "Babies", "Babyom");
		Product product = new Product((long) 1, "P-100", "Babies", "Babyom");

		given(productService.saveProduct(Mockito.any(ProductMetadataRequestDto.class)))
				.willReturn(product);

		mockMvc.perform(post("/api/internal/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(requestDto)))
				.andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(product)));
	}
}
