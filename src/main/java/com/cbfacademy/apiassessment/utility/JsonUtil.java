package com.cbfacademy.apiassessment.utility;

import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.model.InvestmentWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;


//Utility class to handle JSON operations related to investment objects,
public class JsonUtil {
    //ObjectMapper instance for JSON serialization and deserialization
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String FILE_PATH = "./src/data/investment.json";
    // Method to read investments from JSON file.
    public static List<Investment> readInvestmentsFromJson() throws IOException {
        // Use the classloader to get the InputStream for the classpath resource
        try (FileInputStream fileInputStream = new FileInputStream(FILE_PATH)) {
            InvestmentWrapper wrapper = objectMapper.readValue(fileInputStream, InvestmentWrapper.class);
            return wrapper.getInvestments();
        }
    }
    // Method to write investments to the JSON file.
    public static void writeInvestmentsToJson(List<Investment>investments) throws IOException {
        InvestmentWrapper wrapper = new InvestmentWrapper();
        wrapper.setInvestments(investments);
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH)) {
                objectMapper.writeValue(fileOutputStream, wrapper);
            }
        }
    }
