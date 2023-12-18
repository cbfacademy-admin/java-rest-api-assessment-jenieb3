package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.model.Bond;
import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Description;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.net.URL;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for Investment API endpoints.
 * This class tests the application's RESTful service layer, ensuring that each endpoint behaves
 * as expected under various scenarios.
 * It uses {@link TestRestTemplate} for making HTTP requests and assert responses.
 * (@SpringbootTest) Boots the application for integration testing.
 * (@ActiveProfiles) Specifies the 'test' profile for the test environment.
 */


@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class AppTest {
	// Class fields and setup methods

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/api/investment");
	}

	/**
	 * Test the retrieval of all investments. Ensures the GET request to '/api/investment' returns a list
	 * of investments and that the response status is OK(200)
	 */
	@Test
	@Description("test to get all investments")
	public void getAllInvestments_ShouldReturnInvestmentsList() {
		ResponseEntity<Investment[]> response = restTemplate.getForEntity(base.toString(), Investment[].class);
		assertEquals(200, response.getStatusCode().value());
		assertTrue(Objects.requireNonNull(response.getBody()).length > 0);
	}
	/**
	 * Test the retrieval of specific investment by its ID. Ensures the GET request to '/api/investment/{id}'
	 * returns the correct investment and that the response status is OK(200)
	 */
	@Test
	@Description("test to get a specific investment by ID")
	public void getInvestmentById_ShouldReturnInvestment() {
		ResponseEntity<List<Investment>> responseList = restTemplate.exchange(
				base.toString(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<>() {
				}
		);
		Long id = Objects.requireNonNull(responseList.getBody()).get(0).getId();

		ResponseEntity<Investment> response = restTemplate.getForEntity(base.toString() + "/" + id, Investment.class);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(id, Objects.requireNonNull(response.getBody()).getId());
	}
	/**
	 * Test the creation of a new bond investment. Ensures the POST request to '/api/investment' correctly
	 * creates a bond and that the response status is OK(200)
	 */
	@Test
	@Description("test to create a new bond investment")
	public void createBondInvestment_ShouldReturnCreatedInvestment() {
		Bond bond = new Bond();
		bond.setId(28L);
		bond.setName("JupBond ");
		bond.setQuantity(65);
		bond.setPurchasePrice(600.0);
		bond.setCurrentPrice(1050.0);

		ResponseEntity<Investment> response = restTemplate.postForEntity(base.toString(), bond, Investment.class);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(Objects.requireNonNull(response.getBody()).getId());
	}
	/**
	 * Test the creation of a new stock investment. Ensures the POST request to '/api/investment' correctly
	 * creates a stock and that the response status is OK(200)
	 */
	@Test
	@Description("test to create new stock investment")
	public void createStockInvestment_ShouldReturnCreatedInvestment() {
		Stock stock = new Stock();
		stock.setId(21L);
		stock.setName("DIHStock");
		stock.setQuantity(53);
		stock.setPurchasePrice(100.0);
		stock.setCurrentPrice(110.0);

		ResponseEntity<Investment> response = restTemplate.postForEntity(base.toString(), stock, Investment.class);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(Objects.requireNonNull(response.getBody()).getId());
	}
	/**
	 * Test to updating an existing stock investment. Ensures the PUT request to '/api/investment/{id}' correctly
	 * updates a stock details and the updated information is reflected in the response.
	 */
	@Test
	@Description("test to update new stock")
	public void updateStockInvestment_ShouldReturnUpdatedInvestment() {
		Long id = 11L;
		Stock updatedStock = new Stock();
		updatedStock.setId(id);
		updatedStock.setName("OrchidStock");
		updatedStock.setQuantity(120);
		updatedStock.setPurchasePrice(110.0);
		updatedStock.setCurrentPrice(115.0);

		restTemplate.put(base.toString() + "/" + id, updatedStock, Stock.class);
		ResponseEntity<Stock> response = restTemplate.getForEntity(base.toString() + "/" + id, Stock.class);
		assertEquals(200, response.getStatusCode().value());
		assertEquals("OrchidStock", Objects.requireNonNull(response.getBody()).getName());
		assertEquals(120, response.getBody().getQuantity());
	}
	/**
	 * Test to updating an existing bond investment. Ensures the PUT request to '/api/investment/{id}' correctly
	 * updates a bond details and the updated information is reflected in the response.
	 */
	@Test
	@Description("test to update new bond")
	public void updateBondInvestment_ShouldReturnUpdatedInvestment() {
		Long id = 11L;
		Bond updatedBond = new Bond();
		updatedBond.setId(id);
		updatedBond.setName("OrchidBond");
		updatedBond.setQuantity(120);
		updatedBond.setPurchasePrice(1000.0);
		updatedBond.setCurrentPrice(995.0);

		restTemplate.put(base.toString() + "/" + id, updatedBond, Bond.class);
		ResponseEntity<Bond> response = restTemplate.getForEntity(base.toString() + "/" + id, Bond.class);
		assertEquals(200, response.getStatusCode().value());
		assertEquals("OrchidBond", Objects.requireNonNull(response.getBody()).getName());
		assertEquals(120, response.getBody().getQuantity());
	}
	/**
	 * Tests the deletion of an investment by its ID. Ensures the DELETE request to '/api/investment/{id}'
	 * removes the investment and the subsequent GET response for the same ID return a NOT FOUND(404)
	 * status.
	 */
	@Test
	@Description("test to delete investment by ID")
	public void deleteInvestment_ShouldReturnDeletedInvestment() {
		Long id = 21L;
		restTemplate.delete(base.toString() + "/" + id);
		ResponseEntity<Investment> response = restTemplate.getForEntity(base.toString() + "/" + id, Investment.class);
		assertEquals(404, response.getStatusCode().value());
	}

}




