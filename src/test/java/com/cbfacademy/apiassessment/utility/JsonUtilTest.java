package com.cbfacademy.apiassessment.utility;

import com.cbfacademy.apiassessment.model.Bond;
import com.cbfacademy.apiassessment.model.Investment;
import com.cbfacademy.apiassessment.model.InvestmentWrapper;
import com.cbfacademy.apiassessment.model.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JsonUtilTest {
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private ResourceLoader resourceLoader;
    @Mock
    private Resource resource;
    private JsonUtil jsonUtil;
    private final String filePath = "classpath:data/test-investment.json";
    @BeforeEach
    void setup() {
        // Setting up necessary objects before each test
        jsonUtil = new JsonUtil(objectMapper, resourceLoader, filePath);
    }
    @Test
    void readInvestmentsFromJsonTest() throws Exception {
        // Testing the read functionality from JSON

        // Creating mock objects for Bond and Stock implementations
        Investment bond = mock(Bond.class);
        Investment stock = mock(Stock.class);
        List<Investment> investmentList = Arrays.asList(bond, stock);
        InvestmentWrapper wrapper = new InvestmentWrapper();
        wrapper.setInvestments(investmentList);

        String exampleJson = "[{\"type\":\"Bond\"}, {\"type\":\"Stock\"}]";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(exampleJson.getBytes());

        // Mocking methods of resourceLoader and objectMapper
        when(resourceLoader.getResource(filePath)).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(inputStream);
        when(objectMapper.readValue(any(InputStream.class), eq(InvestmentWrapper.class))).thenReturn(wrapper);

        // Calling the method under test
        List<Investment> result = jsonUtil.readInvestmentsFromJson();
        //Asserting the expected results
        assertEquals(investmentList, result);
    }
    @Test
    void writeInvestmentsToJsonTest() throws Exception {
        //Testing the write functionality to JSON

        List<Investment> investmentList = Arrays.asList(mock(Investment.class), mock(Investment.class));
        InvestmentWrapper wrapper = new InvestmentWrapper();
        wrapper.setInvestments(investmentList);
        File tempFile = Files.createTempFile("test-investment", ".json").toFile();
        when(resourceLoader.getResource(filePath)).thenReturn(resource);
        when(resource.getFile()).thenReturn(tempFile);
        doNothing().when(objectMapper).writeValue(any(FileOutputStream.class), any(InvestmentWrapper.class));

        //Calling the method under test
        jsonUtil.writeInvestmentsToJson(wrapper.getInvestments());
        //Asserting that the objectMapper's writeValue method was called
        verify(objectMapper).writeValue(any(FileOutputStream.class), any(InvestmentWrapper.class));
        //Cleaning up by deleting the temporary file
        assertTrue(tempFile.delete(), "Temporary file deletion failed");
    }
}
