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
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InvestmentControllerTest {

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
	@Description("/test to get all investments")
	public void getAllInvestments_ShouldReturnInvestmentsList() {
		ResponseEntity<Investment[]> response = restTemplate.getForEntity(base.toString(), Investment[].class);

		assertEquals(200, response.getStatusCode().value());
		assertTrue(response.getBody().length >=0);
	}

	@Test
	@Description("/test to get a specific investment by ID")
	public void getInvestmentById_ShouldReturnInvestment() {
		Long id = 1L;
		ResponseEntity<Investment> response = restTemplate.getForEntity(base.toString() + "/" + id, Investment.class);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(id, response.getBody().getId());
	}
	@Test
	@Description("test to create a new bond investment")
	public void createBondInvestment_ShouldReturnCreatedInvestment() {
		Bond bond = new Bond();
		ResponseEntity<Investment> response = restTemplate.postForEntity(base.toString(), bond, Investment.class);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody().getId());
	}
	@Test
	@Description("test to create new stock investment")
	public void createStockInvestment_ShouldReturnCreatedInvestment() {
		Stock stock = new Stock();
		ResponseEntity<Investment> response = restTemplate.postForEntity(base.toString(), stock, Investment.class);
		assertEquals(200, response.getStatusCode().value());
		assertNotNull(response.getBody().getId());

	}
	@Test
	@Description("test to update new stock")
	public void updateStockInvestment_ShouldReturnUpdatedInvestment() {
		Long id = 1L;
		Stock updatedStock = new Stock();
		restTemplate.put(base.toString() + "/" + id, Stock.class);
	}
	@Test
	@Description("test to update new bond")
	public void updateBondInvestment_ShouldReturnUpdatedInvestment() {
		Long id = 1L;
		Bond updatedBond = new Bond();
		restTemplate.put(base.toString() + "/" + id, Bond.class);
	}
	@Test
	@Description("test to delete investment by ID")
	public void deleteInvestment_ShouldReturnDeletedInvestment() {
		Long id = 1L;
		restTemplate.delete(base.toString() + "/" + id);
		ResponseEntity<Investment> response = restTemplate.getForEntity(base.toString() + "/" + id, Investment.class);
		assertEquals(404, response.getStatusCode().value());

	}

}



