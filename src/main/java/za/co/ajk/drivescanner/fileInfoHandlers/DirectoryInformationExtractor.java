package za.co.ajk.drivescanner.fileInfoHandlers;

import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.dtos.DirectoryEntryDTO;

import java.io.File;

@Component
public class DirectoryInformationExtractor {

    public DirectoryEntryDTO extractDirectoryInformation(File file) {

        DirectoryEntryDTO entryDTO = new DirectoryEntryDTO();

        return entryDTO;
    }
}
