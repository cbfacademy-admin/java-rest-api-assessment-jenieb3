package com.cbfacademy.apiassessment.exceptions;

/**
 * InvestmentValidationException is a custom runtime exception used within the investment service
 * for error scenarios specific to investment validation.
 */

public class InvestmentValidationException extends RuntimeException{
    public InvestmentValidationException(String message) {
        super(message);
    }
}
