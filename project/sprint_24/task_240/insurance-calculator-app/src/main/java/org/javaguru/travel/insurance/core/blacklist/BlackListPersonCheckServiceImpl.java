package org.javaguru.travel.insurance.core.blacklist;
import lombok.extern.slf4j.Slf4j;

import org.javaguru.travel.insurance.core.api.dto.PersonDTO;
import org.javaguru.travel.insurance.core.blacklist.dto.BlackListedPersonCheckRequest;
import org.javaguru.travel.insurance.core.blacklist.dto.BlackListedPersonCheckResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@Profile({"mysql-container", "mysql-local"})
class BlackListPersonCheckServiceImpl implements BlackListPersonCheckService {

    private final String personBlacklistedCheckUrl;

    private final RestTemplate restTemplate;

    BlackListPersonCheckServiceImpl(@Value("${person.blacklisted.check.url}")
                                    String personBlacklistedCheckUrl,
                                    RestTemplate restTemplate) {
        this.personBlacklistedCheckUrl = personBlacklistedCheckUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean isPersonBlacklisted(PersonDTO personDTO) {
        log.info("Blacklisted check for person with code " + personDTO.getPersonCode() + " started!");

        // Set the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        BlackListedPersonCheckRequest request = new BlackListedPersonCheckRequest();
        request.setPersonFirstName(personDTO.getPersonFirstName());
        request.setPersonLastName(personDTO.getPersonLastName());
        request.setPersonCode(personDTO.getPersonCode());

        // Create an HttpEntity object with the request body and headers
        HttpEntity<BlackListedPersonCheckRequest> requestEntity = new HttpEntity<>(request, headers);

        // Make the POST request and expect a BlackListedPersonCheckResponse object in response
        ResponseEntity<BlackListedPersonCheckResponse> responseEntity = restTemplate.postForEntity(personBlacklistedCheckUrl, requestEntity, BlackListedPersonCheckResponse.class);

        BlackListedPersonCheckResponse response = responseEntity.getBody();

        log.info("Blacklisted check for person with code " + personDTO.getPersonCode() + " return " + response.getBlacklisted());

        return response.getBlacklisted();
    }

}
