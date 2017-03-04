package za.co.ajk.drivescanner.translators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.dtos.FileEntryDTO;
import za.co.ajk.drivescanner.dtos.ScanInfoDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

@Component
public class TranslateInformationImpl implements TranslateInformation {

    private Logger logger = LoggerFactory.getLogger(TranslateInformationImpl.class);

    /**
     * Translate the FileEntryDTO to a XML String representation
     *
     * @param fileEntryDTO
     * @return
     * @throws JsonProcessingException
     */
    public String translateToXML(FileEntryDTO fileEntryDTO) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String result = xmlMapper.writeValueAsString(fileEntryDTO);
        logger.info("Generated xml |"+result+"|");

        return result;
    }

    /**
     * Translate using JAXB for more control over the output generated.
     *
     * @param fileEntryDTO
     * @return
     */
    public String translateToXMLJaxb(FileEntryDTO fileEntryDTO) {
        try {
            JAXBContext context = JAXBContext.newInstance(fileEntryDTO.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // exclude the XML Declaratiuon part for record entries.

            StringWriter sw = new StringWriter();
            marshaller.marshal(fileEntryDTO, sw);
            String result = sw.toString();

            logger.info("Generated xml |"+result+"|");

            return result;
        } catch (JAXBException e) {
            logger.error("Error parsing the inpur value. " + e.getLocalizedMessage());
            return null;
        }
    }

    public String generateFileHeader(ScanInfoDTO scanInfoDTO) {
        try {
            JAXBContext context = JAXBContext.newInstance(scanInfoDTO.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // exclude the XML Declaratiuon part for record entries.

            StringWriter sw = new StringWriter();
            marshaller.marshal(scanInfoDTO, sw);
            String result = sw.toString();

            logger.info("Generated xml |"+result+"|");

            return result;
        } catch (JAXBException e) {
            logger.error("Error parsing the input value. " + e.getLocalizedMessage());
            e.printStackTrace();
            return null;
        }
    }


    public String translateToJSON(FileEntryDTO fileEntryDTO) throws IOException {


        return null;
    }
}
