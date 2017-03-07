package za.co.ajk.drivescanner.scanners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.dtos.*;
import za.co.ajk.drivescanner.scanners.infohandlers.DirectoryInformationExtractor;
import za.co.ajk.drivescanner.scanners.infohandlers.FileInformationExtractor;
import za.co.ajk.drivescanner.translators.TranslateInformationImpl;
import za.co.ajk.drivescanner.utilities.StringFormatters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Scanner class to scan a drive/mount point and extract a collection of information.
 */
@Component
public class FileSystemScanner {

    private Logger logger = LoggerFactory.getLogger(FileSystemScanner.class);

    @Autowired
    private FileInformationExtractor fileInformationExtractor;

    @Autowired
    private DirectoryInformationExtractor directoryInformationExtractor;

    @Autowired
    private ResultDirectoryStructure resultDirectoryStructure;

    @Autowired
    private TranslateInformationImpl translateInformation;

    @Autowired
    private DirectoryStructureMap directoryStructureMap;

    /**
     * Scan the input folder and return the list of directory file entries.
     *
     * @param startPoint
     */
    public List<File> scanEntryPoint(String startPoint) {

        String osSpecificDirectory = StringFormatters.formatOsFileNameFormat(startPoint);

        logger.info("Starting to scan at entry point : " + osSpecificDirectory);

        List<File> filesList = new ArrayList<>();

        Path startPath = Paths.get(osSpecificDirectory);

        if (startPath.toFile().isFile() || startPath.toFile().isDirectory()) {
            addFiles(startPath.toFile(), filesList);
        }

        logger.info("Final size is >" + filesList.size());

        /*
            Dump the structure to logfile for now..
         */
        logger.info("Starting to dump final structure");

        logger.info("Dumping directory map");
        Set<String> dirSet = directoryStructureMap.getDirectorySet();

        for (String setEntry:dirSet){
            logger.info("  :> "+setEntry);
        }

        Map<DirectoryEntryDTO, List<? super BaseDTO>> directoryMap = resultDirectoryStructure.getFullStructure();

        for (Map.Entry<DirectoryEntryDTO, List<? super BaseDTO>> entry : directoryMap.entrySet()) {

            List<? super BaseDTO> values = entry.getValue();

            for (Object listValue : values) {

                if (listValue instanceof FileEntryDTO) {
                    String res = translateInformation.translateFileEntryDTOToXML((FileEntryDTO) listValue);
                    logger.info(res);
                }else if (listValue instanceof DirectoryEntryDTO) {
                    String res = translateInformation.translateDirectoryEntryDTOToXML((DirectoryEntryDTO)listValue);
                    logger.info(res);
                } else {
                    logger.info("Unknown entry type !!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
                }
            }
        }
        return filesList;
    }

    /**
     * Add a file in the directory passed in. Scan the directory and if it is a file, add the file to the list.
     * If it is a directory, then traverse and add the sub-folder folders. Step in all the sub-folders.
     *
     * @param startMountPoint
     * @param fileList
     * @throws IOException
     */
    public void addFiles(File startMountPoint, List<File> fileList) {

        if (startMountPoint.listFiles() == null) {
            logger.info("Mount point is null. Mount point name is : " + startMountPoint.getName());
            return;
        }

        if (startMountPoint.isDirectory()) {
            /*
                If it is a directory, then iterate through it
             */
            List<File> directoryFiles = new ArrayList<>(Arrays.asList(startMountPoint.listFiles()));

            for (int ii = 0; ii < directoryFiles.size(); ii++) {

                File file = directoryFiles.get(ii);
                String parent = file.getParent();
                File parentFile = file;

                if (file.isDirectory()) {

                    addFiles(file, fileList);

                    /*
                        Add to final result structure
                     */
                    directoryStructureMap.addEntry(file.getAbsolutePath());
                    DirectoryEntryDTO dirDTO = directoryInformationExtractor.extractDirectoryInformation(file);
                    resultDirectoryStructure.addEntry(dirDTO, directoryInformationExtractor.extractDirectoryInformation(file));

                } else if (directoryFiles.get(ii).isFile()) {

                    if (!file.isHidden()) {

                        /*
                            Add to final result structure
                         */
                        DirectoryEntryDTO parentDTO = directoryInformationExtractor.extractDirectoryInformation(parentFile);
                        resultDirectoryStructure.addEntry(parentDTO, fileInformationExtractor.extractFileInfomation(file));
                    }

                    fileList.add(file);
                }
            }
        } else if (startMountPoint.isFile()) {

            logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            logger.info("$$$$   Start at a mountpoint and not a file $$$$");
            logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        } else {
            logger.error("Cannot determine : " + startMountPoint.getName());
        }
    }

}

