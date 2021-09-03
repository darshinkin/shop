package com.shop.orderservice.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.shop.orderservice.OrderServiceApplication;
import com.shop.orderservice.controllers.dto.CartRequest;
import com.shop.orderservice.domain.Cart;
import com.shop.orderservice.domain.Product;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { OrderServiceApplication.class })
@WebAppConfiguration
@TestPropertySource("classpath:application.properties")
public class OrderControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SqsClient sqsClient;

    @MockBean
    private SendMessageRequest.Builder sendMessageRequestBuilder;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("orderController"));
    }

    @Test
    public void givenWithoutCartBody_whenMockMVC_thenReturnsBadRequest() throws Exception {
        this.mockMvc.perform(post("/v1/order"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenValidCart_whenMockMVC_thenReturnsOk() throws Exception {
        CartRequest cartRequest = CartRequest.builder()
                .cart(Cart.builder()
                        .cartId(1L)
                        .products(Set.of(Product.builder().productId(1L).productName("apple").build(),
                                Product.builder().productId(2L).productName("pear").build()))
                        .build())
                .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cartRequest);

        Mockito.when(sendMessageRequestBuilder.messageBody(Mockito.anyString())).thenReturn(SendMessageRequest.builder());
        Mockito.when(sqsClient.sendMessage(Mockito.any(SendMessageRequest.class))).thenReturn(SendMessageResponse.builder().messageId("1").build());

        this.mockMvc.perform(post("/v1/order")
                        .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
