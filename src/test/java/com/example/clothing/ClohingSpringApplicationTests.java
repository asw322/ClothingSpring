package com.example.clothing;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClohingSpringApplicationTests {

	@Autowired
	private ProcessingInterface PROCESSING_INTERFACE;

	@Test
	void contextLoads() {
		testInsertProductTokens();
	}

	void testInsertProductTokens() {
		ArrayList<ProductToken> product_arr = new ArrayList<>();

		product_arr.add(
			new ProductToken(			// Sample product
				"P2",
				"test2",
				"test2",
				"test2",
				"test2",
				11.00,
				"test2",
				"test2",
				"test2",
				"test2",
				"test2",
				"test2",
				null
			)
		);

		System.out.println("Adding product");
		int totalAdded = PROCESSING_INTERFACE.insertProductToken(product_arr);
		System.out.println("Finished adding " + totalAdded + " product");
	}

}
