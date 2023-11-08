package com.cbfacademy.apiassessment.utility;

import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.model.InvestmentWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.List;

@Component
//Utility class to handle JSON operations related to investment objects,
public class JsonUtil {
    private final ObjectMapper objectMapper;
    private final ResourceLoader resourceLoader;
    private final String filePath;

    public JsonUtil(ObjectMapper objectMapper, ResourceLoader resourceLoader, @Value("${json.file.path}") String filePath) {
        this.objectMapper = objectMapper;
        this.resourceLoader = resourceLoader;
        this.filePath = filePath;
    }

    // Method to read investments from JSON file.
    public List<Investment> readInvestmentsFromJson() throws IOException {
        Resource resource = resourceLoader.getResource(filePath);
        try (InputStream inputStream = resource.getInputStream()) {
            InvestmentWrapper wrapper = objectMapper.readValue(inputStream, InvestmentWrapper.class);
            return wrapper.getInvestments();
        }
    }
    // Method to write investments to the JSON file.
    public void writeInvestmentsToJson(List<Investment>investments) throws IOException {
        Resource resource = resourceLoader.getResource(filePath);
        File file = resource.getFile();
        try (OutputStream fileOutputStream = new FileOutputStream(file)) {
            InvestmentWrapper wrapper = new InvestmentWrapper();
            wrapper.setInvestments(investments);
            // Configure ObjectMapper to use the default pretty printer
            //objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileOutputStream, wrapper);
            objectMapper.writeValue(fileOutputStream, wrapper);
            }
        }
    }

