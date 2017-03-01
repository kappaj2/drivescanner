/*
 *
 *  * Copyright (c) PCMS Group plc 2016. All Rights Reserved.
 *  * This source code is copyright of PCMS Group plc. The information
 *  * contained herein is proprietary and confidential to PCMS Group plc.
 *  * This proprietary and confidential information, either in whole or in
 *  * part, shall not be used for any purpose unless permitted by the terms
 *  * of a valid license agreement.
 *
 */

package za.co.ajk.drivescanner.infohandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.dtos.FileEntryDTO;
import za.co.ajk.drivescanner.dtos.ResultDirectoryStructure;
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


        Map<String, List<FileEntryDTO>> directoryMap = resultDirectoryStructure.getFullStructure();

        for (Map.Entry<String, List<FileEntryDTO>> entry: directoryMap.entrySet()){
            logger.info("Key is > "+entry.getKey());
            for(FileEntryDTO dto: entry.getValue()){
                logger.info("      dto.fileName : "+dto.getFileName());
                logger.info("      dto.size     : "+dto.getFileSize());
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
            logger.info("Mount point is null. Mount point name is : "+startMountPoint.getName());
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

                if (file.isDirectory()) {

                    addFiles(file, fileList);

                } else if (directoryFiles.get(ii).isFile() ) {

                    if(!file.isHidden()) {

                        FileEntryDTO fileDTO = fileInformationExtractor.extractFileInfomation(file);
                        resultDirectoryStructure.addEntry(parent, fileDTO);
                    }

                    fileList.add(file);
                }
            }
        } else if (startMountPoint.isFile()) {

            FileEntryDTO fileDTO = fileInformationExtractor.extractFileInfomation(startMountPoint);
            logger.info("FileDTO is : "+fileDTO.toString());

            logger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
            fileList.add(startMountPoint);

        } else {
            logger.error("Cannot determine : " + startMountPoint.getName());
        }
    }

}

