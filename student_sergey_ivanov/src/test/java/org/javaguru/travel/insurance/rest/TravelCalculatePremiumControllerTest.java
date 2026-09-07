package org.javaguru.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc

public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonFileReader jsonFileReader;

    private String request;
    private String response;


    @Test
    @DisplayName("Test case 1: firstName is not provided")
    public void firstNameNotProvided() throws Exception {
        request = "rest/TravelCalculatePremiumRequest_firstName_not_provided.json";
        response = "rest/TravelCalculatePremiumResponse_firstName_not_provided.json";
        executeAndCompare(request, response);
    }

    @Test
    @DisplayName("Test case 2: lastName is not provided")
    public void lastNameNotProvided() throws Exception {
        request = "rest/TravelCalculatePremiumRequest_lastName_not_provided.json";
        response = "rest/TravelCalculatePremiumResponse_lastName_not_provided.json";
        executeAndCompare(request, response);
    }


    @Test
    @DisplayName("Test case 3: agreementDateFrom is not provided")
    public void agreementDateFromNotProvided() throws Exception {
        request ="rest/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json";
        response ="rest/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json";
        executeAndCompare(request, response);
    }

    @Test
    @DisplayName("Test case 4: agreementDateTo is not provided")
    public void agreementDateToNotProvided() throws Exception {
        request = "rest/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json";
        response = "rest/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json";
        executeAndCompare(request, response);
    }

    @Test
    @DisplayName("Test case 5: all fields is not provided")
    public void allFieldsNotProvided() throws Exception {
        request = "rest/TravelCalculatePremiumRequest_allFields_not_provided.json";
        response = "rest/TravelCalculatePremiumResponse_allFields_not_provided.json";
        executeAndCompare(request, response);
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo < agreementDateFrom")
    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
        request ="rest/TravelCalculatePremiumRequest_dateFrom_lessThen_dateTo.json";
        response = "rest/TravelCalculatePremiumResponse_dateFrom_lessThen_dateTo.json";
        executeAndCompare(request, response);
    }

    @Test
    @DisplayName("Test case 7: success")
    public void success() throws Exception {
        request = "rest/TravelCalculatePremiumRequest_success.json";
        response = "rest/TravelCalculatePremiumResponse_success.json";
        executeAndCompare(request, response);
    }

    @Test
    @DisplayName("Test case 8: agreementDateFromIsNotPresent")
    public void agreementDateFromIsNotPresent() throws Exception {
        request = "rest/TravelCalculatePremiumRequest_agreementDateFromIsNotPresent.json";
        response = "rest/TravelCalculatePremiumResponse_agreementDateFromIsNotPresent.json";
        executeAndCompare(request, response);
    }

    @Test
    @DisplayName("Test case 9: agreementDateToIsNotPresent")
    public void agreementDateToIsNotPresent() throws Exception {
        request = "rest/TravelCalculatePremiumRequest_agreementDateToIsNotPresent.json";
        response = "rest/TravelCalculatePremiumResponse_agreementDateToIsNotPresent.json";
        executeAndCompare(request, response);
    }

    @Test
    @DisplayName("Test case 10: selected_risks_success")
    public void selectedRisksSuccess() throws Exception {
        request = "rest/TravelCalculatePremiumRequest_selected_risks_success.json";
        response = "rest/TravelCalculatePremiumResponse_selected_risks_success.json";
        executeAndCompare(request, response);
    }

    @Test
    @DisplayName("Test case 11: selected_risks_not_provided")
    public void selectedRisksNotProvided() throws Exception {
        request = "rest/TravelCalculatePremiumRequest_selected_risks_not_provided.json";
        response = "rest/TravelCalculatePremiumResponse_selected_risks_not_provided.json";
        executeAndCompare(request, response);
    }

    private void executeAndCompare(String jsonRequestFilePath,
                                   String jsonResponseFilePath) throws Exception {
        String jsonRequest = jsonFileReader.readJsonFromFile(jsonRequestFilePath);

        MvcResult result = mockMvc.perform(post("/insurance/travel/")
                        .content(jsonRequest)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();

        String jsonResponse = jsonFileReader.readJsonFromFile(jsonResponseFilePath);

        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(jsonResponse), mapper.readTree(responseBodyContent));
    }

}