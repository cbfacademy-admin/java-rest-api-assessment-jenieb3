package com.cbfacademy.apiassessment.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Using Jackson annotations to help in serialization/deserialization of subclasses.
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bond.class, name = "Bond"),
        @JsonSubTypes.Type(value = Stock.class, name = "Stock")
})


// Investment interface to represent any kind of investment
public interface Investment {
    // Abstract methods for basic Investment properties.
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
