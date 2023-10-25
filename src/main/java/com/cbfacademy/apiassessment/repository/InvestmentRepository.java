package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.model.Investment;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
//mock repository to store data
public class InvestmentRepository {
    private final Map<Long, Investment> investments = new HashMap<>();
    public List<Investment> findAll() {
        return new ArrayList<>(investments.values());
    }
    public Optional<Investment> findById(Long id) {
        return Optional.ofNullable(investments.get(id));
    }
    public Investment save(Investment investment) {
        investments.put(investment.getId(),investment);
        return investment;
    }
    public void deleteById(Long id) {
        investments.remove(id);
    }
}
