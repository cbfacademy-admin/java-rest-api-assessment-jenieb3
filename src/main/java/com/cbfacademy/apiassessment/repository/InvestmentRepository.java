package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.model.Investment;
import java.io.IOException;
import java.util.Optional;
import com.cbfacademy.apiassessment.utility.JsonUtil;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
//Mock repository to simulate data storage for Investments.
public class InvestmentRepository {
    // Logger to log messages and exceptions.
    private static final Logger logger = LoggerFactory.getLogger(InvestmentRepository.class);
    //In-memory storage for investments.
    private final Map<Long, Investment> investments = new HashMap<>();
    private final JsonUtil jsonUtil;
    public InvestmentRepository(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }
    //Initialise the investments map with data from a JSON file.
    @PostConstruct
    public void init() {
        try {
            // Read investments from a JSON file.
            List<Investment>investmentList = jsonUtil.readInvestmentsFromJson();
            // Populate the investments map.
            for (Investment investment : investmentList) {
                investments.put(investment.getId(), investment);
            }
        } catch (IOException e) {
            // log the exception with an appropriate error message
            logger.error("Error reading investments from JSON", e);
        }
    }
    // Fetch all investments from in-memory storage.
    public List<Investment> findAll() {
        return new ArrayList<>(investments.values());
    }
    // Fetch a specific investment by its ID.
    public Optional<Investment> findById(Long id) {
        return Optional.ofNullable(investments.get(id));
    }
    // Save or update an investment. After saving in memory, it also updates the JSON file.
    public Investment save(Investment investment) {
        investments.put(investment.getId(),investment);
        try {
            jsonUtil.writeInvestmentsToJson(new ArrayList<>(investments.values()));
        } catch (IOException e) {
            logger.error("Error saving investment with ID: " + investment.getId(), e);
        }
        return investment;
    }
    // Delete a specific investment by its ID. After deleting from memory, it also updates the JSON file
    public void deleteById(Long id) {
        investments.remove(id);
        try {
            jsonUtil.writeInvestmentsToJson(new ArrayList<>(investments.values()));
        } catch (IOException e)  {
            logger.error("Error deleting investment with ID: " + id, e);

        }
    }
}
