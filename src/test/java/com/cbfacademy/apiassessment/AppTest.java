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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

// Test class for the App's investment API endpoints.

@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppTest {

	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/api/investment");
	}

	@Test
	@Description("test to get all investments")
	public void getAllInvestments_ShouldReturnInvestmentsList() {
		ResponseEntity<Investment[]> response = restTemplate.getForEntity(base.toString(), Investment[].class);
		assertEquals(200, response.getStatusCode().value());
		assertTrue(Objects.requireNonNull(response.getBody()).length > 0);
	}

	@Test
	@Description("test to get a specific investment by ID")
	public void getInvestmentById_ShouldReturnInvestment() {
		ResponseEntity<List<Investment>> responseList = restTemplate.exchange(
				base.toString(),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Investment>>() {
				}
		);
		Long id = Objects.requireNonNull(responseList.getBody()).get(0).getId();

		ResponseEntity<Investment> response = restTemplate.getForEntity(base.toString() + "/" + id, Investment.class);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(id, Objects.requireNonNull(response.getBody()).getId());
	}

	@Test
	@Description("test to create a new bond investment")
	public void createBondInvestment_ShouldReturnCreatedInvestment() {
		Bond bond = new Bond();
		bond.setId(5L);
		bond.setName("TealBond ");
		bond.setQuantity(100);
		bond.setPurchasePrice(1000.0);
		bond.setCurrentPrice(1050.0);

		ResponseEntity<Investment> response = restTemplate.postForEntity(base.toString(), bond, Investment.class);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(Objects.requireNonNull(response.getBody()).getId());
	}

	@Test
	@Description("test to create new stock investment")
	public void createStockInvestment_ShouldReturnCreatedInvestment() {
		Stock stock = new Stock();
		stock.setId(1L);
		stock.setName("GoogleStock");
		stock.setQuantity(50);
		stock.setPurchasePrice(100.0);
		stock.setCurrentPrice(110.0);

		ResponseEntity<Investment> response = restTemplate.postForEntity(base.toString(), stock, Investment.class);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(Objects.requireNonNull(response.getBody()).getId());
	}

	@Test
	@Description("test to update new stock")
	public void updateStockInvestment_ShouldReturnUpdatedInvestment() {
		Long id = 1L;
		Stock updatedStock = new Stock();
		updatedStock.setId(id);
		updatedStock.setName("GoogleStock");
		updatedStock.setQuantity(80);
		updatedStock.setPurchasePrice(110.0);
		updatedStock.setCurrentPrice(115.0);

		restTemplate.put(base.toString() + "/" + id, updatedStock, Stock.class);
		ResponseEntity<Stock> response = restTemplate.getForEntity(base.toString() + "/" + id, Stock.class);
		assertEquals(200, response.getStatusCode().value());
		assertEquals("GoogleStock", Objects.requireNonNull(response.getBody()).getName());
		assertEquals(80, response.getBody().getQuantity());
	}

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

	@Test
	@Description("test to delete investment by ID")
	public void deleteInvestment_ShouldReturnDeletedInvestment() {
		Long id = 21L;
		restTemplate.delete(base.toString() + "/" + id);
		ResponseEntity<Investment> response = restTemplate.getForEntity(base.toString() + "/" + id, Investment.class);
		assertEquals(404, response.getStatusCode().value());
	}

}




