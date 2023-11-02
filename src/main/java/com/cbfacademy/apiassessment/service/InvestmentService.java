package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.utility.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Business logic for CRUD operations
@Service

public class InvestmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentService.class);

    public InvestmentService() {
    }

    public List<Investment> findAll() {
        // Retrieve all investments from the JSON file
        try {
            return JsonUtil.readInvestmentsFromJson();
        } catch (IOException e) {
            LOGGER.error("Error reading investments from JSON", e);
            // If there's an error reading the JSON, return an empty list.
            return new ArrayList<>();
        }
    }
    public Optional<Investment> findById(Long id) {
        // Retrieve all investments and find the specific one by its ID
        return findAll().stream().filter(investment -> investment.getId().equals(id)).findFirst();
    }
    // This method saves a new investment.
    public Investment save(Investment investment) {
        List<Investment> investments = findAll();
        investments.add(investment);
        try {
            JsonUtil.writeInvestmentsToJson(investments);
        } catch (IOException e) {
            LOGGER.error("Error writing investments to JSON", e);
        }
        return investment;
    }
    // This method deletes an investment by its ID.
    public void deleteById(Long id) {
        List<Investment> investments = findAll();
        investments.removeIf(investment -> investment.getId().equals(id));
        try {
            JsonUtil.writeInvestmentsToJson(investments);
        } catch (IOException e) {
            LOGGER.error("Error deleting investment from JSON by ID: {}", id, e);
        }
    }
}
//Parsing algorithm to read customer input
//Converting between excel and JSON
