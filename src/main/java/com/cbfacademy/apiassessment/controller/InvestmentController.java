package com.cbfacademy.apiassessment.controller;

import com.cbfacademy.apiassessment.model.Bond;
import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.model.Stock;
import com.cbfacademy.apiassessment.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investment")
public class InvestmentController {
    private final InvestmentService investmentService;

    @Autowired
    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    // Fetch all investments.
    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentService.findAll();
    }

    // Fetch investments by ID
    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable Long id) {
        Optional<Investment> optionalInvestment = investmentService.findById(id);
        return optionalInvestment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new investment.
    @PostMapping
    public Investment createInvestment(@RequestBody Investment investment) {
        return investmentService.save(investment);
    }

    // Update an investment.
    @PutMapping("/{id}")
    public Investment updateInvestment(@PathVariable Long id, @RequestBody Investment updatedInvestment) {
        return investmentService.save(updatedInvestment);
    }

    //Delete an investment.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        investmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stocks")
    public List<Stock> getAllStocks() {
        return investmentService.findAllStocks();
    }

    @GetMapping("/bonds")
    public List<Bond> getAllBonds() {
        return investmentService.findAllBonds();
    }

    @GetMapping("/highest-returns")
    public Investment getInvestmentWithHighestReturns() {
        return investmentService.findInvestmentWithHighestReturns();
    }

    //New endpoint for filtered and sorted investments
    @GetMapping("/filter")
    public List<Investment> getFilteredAndSortedInvestments(
            @RequestParam(required = false) String type,
            @RequestParam (required = false) Double minReturn,
            @RequestParam(required = false) String sortBy) {
        return investmentService.filterAndSortInvestments(type, minReturn, sortBy);

    }
}
