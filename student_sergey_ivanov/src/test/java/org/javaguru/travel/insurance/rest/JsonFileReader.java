package org.javaguru.travel.insurance.rest;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.nio.file.Files;

public class JsonFileReader {

    public static void main(String[] args) {
        JsonFileReader jsonFileReader = new JsonFileReader();
        String s = "TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json";
        System.out.println(jsonFileReader.readJsonFromFile(s));
    }

    public String readJsonFromFile(String filePath) {
        try {
            File file = ResourceUtils.getFile("classpath:" + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
