package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//business logic simple CRUD operations
@Service

public class InvestmentService {
    //@Autowired
    private final InvestmentRepository investmentRepository;
    public InvestmentService(InvestmentRepository investmentRepository) {
        this.investmentRepository = investmentRepository;
    }
    public List<Investment> findAll() {
        return investmentRepository.findAll();
    }
    public Optional<Investment> findById(Long id) {
        return investmentRepository.findById(id);
    }
    public Investment save(Investment investment) {
        return investmentRepository.save(investment);
    }
    public void deleteById(Long id) {
        investmentRepository.deleteById(id);
    }
}
