package com.cbfacademy.apiassessment.model;

public class Stock implements Investment {
    private Long id;
    private String name;
    private int quantity;
    private double purchasePrice;
    private double currentPrice;

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
        return (currentPrice - purchasePrice) * quantity;
    }
}