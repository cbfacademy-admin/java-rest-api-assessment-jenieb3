package com.cbfacademy.apiassessment.model;

// investment interface to represent any kind of investment
public interface Investment {
    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    int getQuantity();
    void setQuantity(int quantity);
    double getPurchasePrice();
    void setPurchasePrice(double purchasePrice);
    double getCurrentPrice();
    void setCurrentPrice(double currentPrice);
    double getReturns();

}
