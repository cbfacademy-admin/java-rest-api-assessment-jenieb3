package com.cbfacademy.apiassessment.model;

import java.util.List;

/**
 * InvestmentWrapper isa Data Transfer Object (DTO) used to wrap a list of investments.
 * It facilitates the serialization and deserialization of investment data for JSON processing.
 */

//Wrapper class for deserialization and serialization of JSON
public class InvestmentWrapper {
    //List to hold the investments
    private List<Investment> investments;
    //Gets the list of investments

    public List<Investment> getInvestments() {
        return investments;
    }
    //Sets the list of investments

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }
}
