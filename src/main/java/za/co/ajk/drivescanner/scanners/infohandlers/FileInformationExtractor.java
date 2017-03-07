package za.co.ajk.drivescanner.scanners.infohandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.dtos.FileEntryDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;

@Component
public class FileInformationExtractor {

    private Logger logger = LoggerFactory.getLogger(FileInformationExtractor.class);

    /**
     * Extract the available information from the file and return a DTO with this information.
     *
     * @param file
     * @return
     * @throws IOException
     */
    public FileEntryDTO extractFileInfomation(File file) {

        FileEntryDTO fileEntryDTO = new FileEntryDTO();

        try {

            fileEntryDTO.setFileName(file.getName());
            fileEntryDTO.setFileSize(file.length());
            fileEntryDTO.setHidden(file.isHidden());

            Path path = file.toPath();

 //           logger.info("File name : "+file.getCanonicalPath());


            BasicFileAttributeView attributes = Files.getFileAttributeView(path, BasicFileAttributeView.class, java.nio.file.LinkOption.NOFOLLOW_LINKS);
//            logger.info(" ********** Basic Attributes : " + attributes.toString());
//            if (attributes != null) {
//                try {
//                    FileOwnerAttributeView ownerAttributeView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);
//                    UserPrincipal owner = ownerAttributeView.getOwner();
//                    logger.info("owner: " + owner.getName());
//
//                    BasicFileAttributes bfa = attributes.readAttributes();
//                    logger.info("bfa creation time  :" + bfa.creationTime());
//                    logger.info("bfa isDirectory    :" + bfa.isDirectory());
//                    logger.info("bfa isOther        :" + bfa.isOther());
//                    logger.info("bfa isRegularFile  :" + bfa.isRegularFile());
//                    logger.info("bfa isSymbolicLink :" + bfa.isSymbolicLink());
//                    logger.info("bfa lastAccessTime :" + bfa.lastAccessTime());
//                    logger.info("bfa size           :" + bfa.size());
//                } catch (java.nio.file.NoSuchFileException nsfe) {
//                }
//            }

            /*
                This is available on Posix (Linux) file systems only.
             */
            //Set<PosixFilePermission> set = Files.getPosixFilePermissions(path, java.nio.file.LinkOption.NOFOLLOW_LINKS);

            PosixFileAttributeView view = Files.getFileAttributeView(path, PosixFileAttributeView.class, java.nio.file.LinkOption.NOFOLLOW_LINKS);
//            if (view != null) {
//                logger.info("Posix Attributes : " + view.toString());
//            }

            file.getCanonicalPath();

          //  logger.info("File DTO : " + fileEntryDTO.toString());
        } catch (IOException ex) {
            logger.error("Error extracting file information: " + ex.getLocalizedMessage(), ex);
        }

        return fileEntryDTO;
    }
}
