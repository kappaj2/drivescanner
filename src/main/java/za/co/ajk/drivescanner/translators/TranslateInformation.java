package za.co.ajk.drivescanner.translators;

import za.co.ajk.drivescanner.dtos.DirectoryEntryDTO;
import za.co.ajk.drivescanner.dtos.FileEntryDTO;
import za.co.ajk.drivescanner.dtos.ScanInfoDTO;

public interface TranslateInformation {

    String translateFileEntryDTOToXML(FileEntryDTO fileEntryDTO);

    String translateDirectoryEntryDTOToXML(DirectoryEntryDTO directoryEntryDTO);

    String translateScanInfoDTOToXML(ScanInfoDTO scanInfoDTO);

}
