package za.co.ajk.drivescanner.fileInfoHandlers;

import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.dtos.FileEntryDTO;

import java.io.File;

@Component
public class FileInformationExtractor {

    public FileEntryDTO extractFileInfomation(File file) {
        FileEntryDTO fileEntryDTO = new FileEntryDTO();

        return fileEntryDTO;
    }
}
