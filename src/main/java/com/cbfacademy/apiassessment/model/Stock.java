package com.cbfacademy.apiassessment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Implementation of the Investment interface for Stocks.
@JsonIgnoreProperties(ignoreUnknown = true)

public class Stock implements Investment {
    // Class properties for a Stock
    private Long id;
    private String name;
    private int quantity;
    private double purchasePrice;
    private double currentPrice;
// Constructor
    public Stock(Long id, String name, int quantity, double purchasePrice, double currentPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
    }

    public Stock() {

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
      this.quantity = quantity;
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
    @Override
    public double getReturns() {
        //Calculation for stock returns
        return (currentPrice - purchasePrice) * quantity;
    }
}