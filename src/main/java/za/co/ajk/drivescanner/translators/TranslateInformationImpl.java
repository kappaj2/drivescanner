package za.co.ajk.drivescanner.translators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.dtos.BaseDTO;
import za.co.ajk.drivescanner.dtos.DirectoryEntryDTO;
import za.co.ajk.drivescanner.dtos.FileEntryDTO;
import za.co.ajk.drivescanner.dtos.ScanInfoDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Component
public class TranslateInformationImpl implements TranslateInformation {

    private Logger logger = LoggerFactory.getLogger(TranslateInformationImpl.class);

//    /**
//     * Translate the FileEntryDTO to a XML String representation
//     *
//     * @param fileEntryDTO
//     * @return
//     * @throws JsonProcessingException
//     */
//    public String translateFileEntryDTOToXML(FileEntryDTO fileEntryDTO) throws JsonProcessingException {
//        XmlMapper xmlMapper = new XmlMapper();
//        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        String result = xmlMapper.writeValueAsString(fileEntryDTO);
//        logger.info("Generated xml |"+result+"|");
//
//        return result;
//    }

    /**
     * Translate using JAXB for more control over the output generated.
     *
     * @param fileEntryDTO
     * @return
     */
    public String translateFileEntryDTOToXML(FileEntryDTO fileEntryDTO) {
        return tranlateDTOToXml(fileEntryDTO, false);
    }

    /**
     * Translate using JAXB for more control over the output generated.
     *
     * @param directoryEntryDTO
     * @return
     */
    public String translateDirectoryEntryDTOToXML(DirectoryEntryDTO directoryEntryDTO) {
        return tranlateDTOToXml(directoryEntryDTO, false);
    }


    /**
     * Scan info handle's slightly different as we need the XML header here as well.
     *
     * @param scanInfoDTO
     * @return
     */
    public String translateScanInfoDTOToXML(ScanInfoDTO scanInfoDTO) {
        return tranlateDTOToXml(scanInfoDTO, true);
    }

    /**
     * Translator method for the DTO's.
     *
     * @param dto
     * @return
     */
    private String tranlateDTOToXml(BaseDTO<? extends BaseDTO> dto, boolean generateHeader) {

        try {
            JAXBContext context = JAXBContext.newInstance(dto.getClass());
            Marshaller marshaller = context.createMarshaller();

            if (generateHeader) {
                marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                        "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            }

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // exclude the XML Declaratiuon part for record entries.

            StringWriter sw = new StringWriter();
            marshaller.marshal(dto, sw);
            String result = sw.toString();

            return result;
        } catch (JAXBException e) {
            logger.error("Error parsing the inpur value. " + e.getLocalizedMessage());
            return null;
        }
    }
}
