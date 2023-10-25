package com.cbfacademy.apiassessment.controller;

import com.cbfacademy.apiassessment.model.Investment;
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
    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable Long id) {
        Optional<Investment> optionalInvestment = investmentService.findById(id);
        return optionalInvestment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Investment createInvestment(@RequestBody Investment investment) {
        return investmentService.save(investment);
    }
    @PutMapping("/{id}")
    public Investment updateInvestment(@PathVariable Long id, @RequestBody Investment updatedInvestment) {
       return investmentService.save(updatedInvestment);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        investmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
