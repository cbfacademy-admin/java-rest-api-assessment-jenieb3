package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.exceptions.InvestmentValidationException;
import com.cbfacademy.apiassessment.model.Bond;
import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.model.Stock;
import com.cbfacademy.apiassessment.utility.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvestmentRepositoryTest {
    @Mock
    private JsonUtil jsonUtil;
    @InjectMocks
    private InvestmentRepository investmentRepository;
    // Concrete implementation of Investments
    private Bond sampleBond;
    private Stock sampleStock;

    @BeforeEach
    void setup() throws IOException {
        sampleBond = new Bond();
        sampleBond.setId(1L);
        sampleBond.setName("Sample Bond");
        sampleBond.setQuantity(15);

        sampleStock = new Stock();
        sampleStock.setId(2L);
        sampleStock.setName("Sample Stock");
        sampleStock.setQuantity(20);

        // Mocking the JsonUtil response
        lenient().when(jsonUtil.readInvestmentsFromJson()).thenReturn(Arrays.asList(sampleBond, sampleStock));
        investmentRepository.init();
    }
    @Test
    // Initialize the repository and verify contents
    void initTest() {

        List<Investment> investments = investmentRepository.findAll();
        assertTrue(investments.contains(sampleBond));
        assertTrue(investments.contains(sampleStock));
        assertEquals(2, investments.size());
    }
    @Test
    void findAllTest() {
        // Test to retrieve all Investments
        List<Investment> investments = investmentRepository.findAll();
        assertFalse(investments.isEmpty());
        assertTrue(investments.contains(sampleBond));
        assertTrue(investments.contains(sampleStock));
        assertEquals(2, investments.size());
    }
    @Test
    void findByIdTest() {
        // Test for finding investments by ID
        investmentRepository.init(); // Make sure the repository is initialized
        Optional<Investment> foundBond = investmentRepository.findById(sampleBond.getId());
        assertTrue(foundBond.isPresent());
        assertEquals(sampleBond, foundBond.get());

        Optional<Investment> foundStock = investmentRepository.findById(sampleStock.getId());
        assertTrue(foundStock.isPresent());
        assertEquals(sampleStock, foundStock.get());
    }
    @Test
    void saveTest() throws IOException {
        // Test saving a new investment
        Stock newStock = new Stock();
        newStock.setId(2L);
        newStock.setName("New Investment");
        investmentRepository.save(newStock);
        verify(jsonUtil).writeInvestmentsToJson(any(List.class), eq(false));
        assertEquals(newStock, investmentRepository.findById(2L).orElse(null));
    }
    @Test
    void saveWithInvalidIdTest () {
        Bond invalidBond = new Bond();
        invalidBond.setId(0L);
        invalidBond.setName("Invalid Bond");

        Exception exception = assertThrows(InvestmentValidationException.class, () -> {
            investmentRepository.save(invalidBond);
        });

        String expectedMessage = "Investment ID must be greater than 0";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
    @Test
    void deleteByIdTest() throws IOException {
        //Test for deleting an investment by ID
        investmentRepository.init();
        assertTrue(investmentRepository.findById(sampleBond.getId()).isPresent());

        investmentRepository.deleteById(sampleBond.getId());
        verify(jsonUtil).writeInvestmentsToJson(new ArrayList<>(investmentRepository.findAll()),  false);
        assertFalse(investmentRepository.findById(sampleBond.getId()).isPresent());
    }
}
