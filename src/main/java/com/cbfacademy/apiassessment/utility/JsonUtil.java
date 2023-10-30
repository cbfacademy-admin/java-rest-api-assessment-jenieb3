package com.cbfacademy.apiassessment.utility;

import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.model.InvestmentWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;


//Utility class to handle JSON operations related to investment objects,
public class JsonUtil {
    //ObjectMapper instance for JSON serialization and deserialization
    private static final ObjectMapper objectMapper = new ObjectMapper();
    // Method to read investments from JSON file.
    public static List<Investment> readInvestmentsFromJson() throws IOException {
        // Use the classloader to get the InputStream for the classpath resource
        try (InputStream is = JsonUtil.class.getResourceAsStream("/investment.json")) {
            InvestmentWrapper wrapper = objectMapper.readValue(is, InvestmentWrapper.class);
            return wrapper.getInvestments();
        }
    }
    // Method to write investments to the JSON file.
    public static void writeInvestmentsToJson(List<Investment>investments) throws IOException{
        URL resourceUrl = JsonUtil.class.getResource("/investment.json");
        File file;
        if (resourceUrl != null) {
            try {
                file = new File(resourceUrl.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new FileNotFoundException("Could not find 'investment.json' on the classpath.");
        }
        InvestmentWrapper wrapper = new InvestmentWrapper();
        wrapper.setInvestments(investments);
        objectMapper.writeValue(file, wrapper);
    }
}
