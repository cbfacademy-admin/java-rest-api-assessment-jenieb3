package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.exceptions.InvestmentValidationException;
import com.cbfacademy.apiassessment.model.Bond;
import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.model.Stock;
import com.cbfacademy.apiassessment.repository.InvestmentRepository;
import com.cbfacademy.apiassessment.utility.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * InvestmentService provides the business logic for investment operations.
 * It interacts with InvestmentRepository for data access and performs validations
 * and business rule enforcement.
 * @Service Marks the class a service provider, used for business logic.
 */

// Business logic for CRUD operations
@Service

public class InvestmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentService.class);
    private final InvestmentRepository investmentRepository;
    private final JsonUtil jsonUtil;

    @Autowired
    public InvestmentService(InvestmentRepository investmentRepository, JsonUtil jsonUtil) {
        this.investmentRepository = investmentRepository;
        this.jsonUtil = jsonUtil;
    }


    // This method finds all investments
    public List<Investment> findAll() {
        LOGGER.info("Attempting to fetch all investments");
        try {
            List<Investment> investments = investmentRepository.findAll();
            LOGGER.info("Successfully fetched {} investments", investments.size());
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
        validateInvestment(investment);
        try {
            Investment savedInvestment = investmentRepository.save(investment);
            LOGGER.info("Investment saved with ID: {}", savedInvestment.getId());

            // Write the updated investments list to JSON file
            List<Investment> allInvestments = findAll();
            jsonUtil.writeInvestmentsToJson(allInvestments, true);

            return savedInvestment;
        } catch (Exception e) {
            LOGGER.error("Error occurred while saving investment.", e);
            throw new RuntimeException("Unable to save investment.", e);
        }
    }

    private void validateInvestment(Investment investment) {
        // Validation logic here - added corrections
        if (investment.getId() == null || investment.getId() <= 0) {
            throw new InvestmentValidationException("Investment ID must be positive and non-null.");
        }
        if (investment.getName() == null || investment.getName().trim().isEmpty()) {
            throw new InvestmentValidationException("Investment name must not be empty.");
        }
        if (investment.getQuantity() < 0) {
            throw new InvestmentValidationException("Quantity must be non-negative.");
        }
        if (investment.getPurchasePrice() < 0 || investment.getCurrentPrice() < 0) {
            throw new InvestmentValidationException("Purchase price and current price must be non-negative.");
        }

    }

    // This method deletes an investment by its ID.
    public void deleteById(Long id) {
        LOGGER.info("Attempting to delete investment with ID: {}", id);
        try {
            investmentRepository.deleteById(id);
            LOGGER.info("Successfully  deleted investment ID: {}", id);

            // Write the updated investments list to JSON file
            List<Investment> allInvestments = findAll();
            jsonUtil.writeInvestmentsToJson(allInvestments, true);

        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting investment with ID: {}", id, e);
            throw new RuntimeException("Unable to delete investment", e);
        }
    }

    // Methods to get all Stocks and Bonds separately
    public List<Stock> findAllStocks() {
        LOGGER.info("Attempting to fetch all stocks");
        List<Investment> investments = investmentRepository.findAllStocks();
        return investments.stream()
                .map(investment -> (Stock) investment)
                .collect(Collectors.toList());
    }

    public List<Bond> findAllBonds() {
        LOGGER.info("Attempting to fetch all bonds");
        List<Investment> investments = investmentRepository.findAllBonds();
        return investments.stream()
                .map(investment -> (Bond) investment)
                .collect(Collectors.toList());
    }

    // Method to find the investment with the highest returns
    public Investment findInvestmentWithHighestReturns() {
        LOGGER.info("Attempting to fetch investment with the highest returns");
        return investmentRepository.findInvestmentWithHighestReturn();
   }
   // Algorithm to Filter and Sort Investments
    //method signature and constructors
    public List<Investment> filterAndSortInvestments(String type, Double minReturn, String sortBy) {
        List<Investment> investments = investmentRepository.findAll();//fetching
        return investments.stream()//converts list into stream
                .filter(investment -> {
                    if (type == null) {
                        return true;
                    } else if ("Stock".equalsIgnoreCase(type)) {
                        return investment instanceof Stock;
                    } else if ("Bond".equalsIgnoreCase(type)) {
                        return investment instanceof Bond;
                    }
                    return false;
                })
                .filter(investment -> minReturn == null || investment.getReturns() >= minReturn)//2nd filter
                .sorted(getComparator(sortBy))
                .collect(Collectors.toList());

    }
    private Comparator<Investment> getComparator(String sortBy) {
        switch (sortBy != null ? sortBy.toLowerCase() : "") {
            case "returns": return Comparator.comparing(Investment::getReturns);
            case "currentprice": return Comparator.comparing(Investment::getCurrentPrice);
            case "purchaseprice": return Comparator.comparing(Investment::getPurchasePrice);
            case "quantity": return Comparator.comparing(Investment::getQuantity);
            default: return Comparator.comparing(Investment::getId);
        }
    }
}