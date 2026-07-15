package org.javaguru.travel.insurance.jobs;
import lombok.extern.slf4j.Slf4j;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.javaguru.travel.insurance.core.api.command.TravelGetAgreementCoreCommand;
import org.javaguru.travel.insurance.core.api.dto.AgreementDTO;
import org.javaguru.travel.insurance.core.services.TravelGetAgreementService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

@Component
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AgreementXmlExporter {

    @Value( "${agreement.xml.exporter.job.path}" )
    private String agreementExportPath;

    private final TravelGetAgreementService agreementService;

    void exportAgreement(String agreementUuid) {
        try {
            log.info("AgreementXmlExporterJob started for uuid = " + agreementUuid);
            AgreementDTO agreement = getAgreementData(agreementUuid);
            String agreementXml = convertAgreementToXml(agreement);
            storeXmlToFile(agreementUuid, agreementXml);
            log.info("AgreementXmlExporterJob finished for uuid = " + agreementUuid);
        } catch (Exception e) {
            log.info("AgreementXmlExporterJob failed for agreement uuid = " + agreementUuid, e);
        }
    }

    private AgreementDTO getAgreementData(String agreementUuid) {
        TravelGetAgreementCoreCommand command = new TravelGetAgreementCoreCommand(agreementUuid);
        return agreementService.getAgreement(command).getAgreement();
    }

    private String convertAgreementToXml(AgreementDTO agreement) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(AgreementDTO.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        jaxbMarshaller.marshal(agreement, sw);
        return sw.toString();
    }

    private void storeXmlToFile(String agreementUuid,
                                String agreementXml) throws IOException {
        File file = new File(agreementExportPath + "/agreement-" + agreementUuid + ".xml");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(agreementXml);
        bw.close();
    }

}
