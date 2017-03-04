package za.co.ajk.drivescanner.translators;

import com.fasterxml.jackson.core.JsonProcessingException;
import za.co.ajk.drivescanner.dtos.FileEntryDTO;
import za.co.ajk.drivescanner.dtos.ScanInfoDTO;

import java.io.IOException;

public interface TranslateInformation {

    String translateToXML(FileEntryDTO fileEntryDTO) throws JsonProcessingException;

    /**
     * Translate using JAXB for more control over the output generated.
     *
     * @param fileEntryDTO
     * @return
     */
    public String translateToXMLJaxb(FileEntryDTO fileEntryDTO);

    public String generateFileHeader(ScanInfoDTO scanInfoDTO);

    String translateToJSON(FileEntryDTO fileEntryDTO) throws IOException;
}
