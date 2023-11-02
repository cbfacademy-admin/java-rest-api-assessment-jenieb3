package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.repository.InvestmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

// Business logic for CRUD operations
@Service

public class InvestmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentService.class);
    private final InvestmentRepository investmentRepository;
    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }
    // This method finds all investments
    public List<Investment> findAll() {
        LOGGER.info("Attempting to fetch all investments");
        try {
            List<Investment> investments = investmentRepository.findAll();
            LOGGER.info("Successfully fetched {} investments" ,investments.size());
            return investments;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all investments", e);
            throw new RuntimeException("Unable to fetch investments", e);
        }
    }
    //This method finds investments based on their ID.
    public Optional<Investment> findById(Long id) {
        LOGGER.info("Attempting to find investment with ID: {}", id);
        try {
            Optional<Investment> investment = investmentRepository.findById(id);
            if (investment.isPresent()) {
                Investment inv = investment.get();
                LOGGER.info("Investment found: {}", inv.getName());
            } else {
                LOGGER.warn("No investment found with ID: {}", id);
            }
            return investment;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching investment with ID: {}.", id, e);
            throw new RuntimeException("Unable to fetch investment.", e);
        }
    }
    // This method saves a new investment.
    public Investment save(Investment investment) {
        LOGGER.info("Attempting to save Investment");
        try {
            Investment savedInvestment = investmentRepository.save(investment);
            LOGGER.info("Investment saved with ID: {}", savedInvestment.getId());
            return savedInvestment;
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving investment.", e);
            throw new RuntimeException("Unable to save investment.", e);
        }
    }
        // This method deletes an investment by its ID.
    public void deleteById(Long id) {
        LOGGER.info("Attempting to delete investment with ID: {}", id);
        try {
            investmentRepository.deleteById(id);
            LOGGER.info("Successfully  deleted investment ID: {}", id);
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting investment with ID: {}", id, e);
            throw new RuntimeException("Unable to delete investment", e);
        }
    }
}
