package com.cbfacademy.apiassessment.model;

public class Bond implements Investment {
    private Long id;
    private String name;
    private int quantity;
    private double purchasePrice;
    private double currentPrice;
    private double interestRate;
    // getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id =id;
    }
    public String getName() {
        return name;
    }
    public void setName (String name) {
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
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    @Override
    public double getReturns() {
        //calculation for bond returns factoring in the interest rate
        return((currentPrice -purchasePrice) + (purchasePrice * (interestRate /100))) * quantity;
    }
}
