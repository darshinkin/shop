package com.shop.orderservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@SpringBootTest
class OrderServiceApplicationTests {

	@MockBean
	private SendMessageRequest.Builder sendMessageRequestBuilder;

	@Test
	void contextLoads() {
	}

}
