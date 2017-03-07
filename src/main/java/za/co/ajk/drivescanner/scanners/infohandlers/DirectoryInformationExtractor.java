package za.co.ajk.drivescanner.scanners.infohandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.dtos.DirectoryEntryDTO;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

/**
 * Component to extract the directory information and create a new DTO with the information.
 */
@Component
public class DirectoryInformationExtractor {

    private Logger logger = LoggerFactory.getLogger(DirectoryInformationExtractor.class);

    public DirectoryEntryDTO extractDirectoryInformation(File file) {

        DirectoryEntryDTO entryDTO = new DirectoryEntryDTO();
        try {
//        logger.info("Canonical path is : +"+file.getCanonicalPath());
//        logger.info("Absoluut is : "+file.getAbsolutePath());
//        logger.info("Parent is : "+file.getParent());

        entryDTO.setDirectoryName(file.getParent());
        entryDTO.setHidden(file.isHidden());
        entryDTO.setExists(file.exists());
        entryDTO.setCanExecute(file.canWrite());
        entryDTO.setCanRead(file.canRead());
        entryDTO.setCanWrite(file.canWrite());
        entryDTO.setLength(file.length());
        entryDTO.setTotalSpace(file.getTotalSpace());
        entryDTO.setUsableSpace(file.getUsableSpace());
        entryDTO.setFreeSpace(file.getFreeSpace());
        entryDTO.setUsableSpace(file.getUsableSpace());

        entryDTO.setLastModified(LocalDateTime.ofInstant(Instant.ofEpochSecond(file.lastModified()), TimeZone
                .getDefault().toZoneId()));
        } catch (Exception ioe) {
            logger.error("Error extracting directory information. " + ioe.getLocalizedMessage(), ioe);
      }
//
//            logger.info("Directory name        : " + file.getName());
//            logger.info("Directory length      : " + file.length());
//            logger.info("Directory isHidden    : " + file.isHidden());
//            logger.info("Directory canExecute  : " + file.canExecute());
//            logger.info("Directory canRead     : " + file.canRead());
//            logger.info("Directory canWrite    : " + file.canWrite());
//            logger.info("Directory exists      : " + file.exists());
//            logger.info("Directory length      : " + file.length());
//            logger.info("DirectoryfreeSpace    : " + file.getFreeSpace());
//            logger.info("Directory totalSpace  : " + file.getTotalSpace());
//            logger.info("Directory usableSpace : " + file.getUsableSpace());
//            logger.info("Directory lastModified: " + file.lastModified());
//
//
//            Path path = file.toPath();
//
//            logger.info("File name : " + file.getCanonicalPath());
//        } catch (IOException ioe) {
//            logger.error("Error extracting directory information. " + ioe.getLocalizedMessage(), ioe);
//        }
        return entryDTO;
    }
}
