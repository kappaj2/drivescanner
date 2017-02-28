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

package za.co.ajk.drivescanner.fileInfoHandlers;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * Scanner class to scan a drive and extract a collection of information.
 */
@Component
public class DirectoryScanner {

    private Logger logger = LoggerFactory.getLogger(DirectoryScanner.class);

    @Autowired
    private FileInformationExtractor fileInformationExtractor;

    @Autowired
    private DirectoryInformationExtractor directoryInformationExtractor;

    /**
     * Scan the input folder and return the list of directories.
     *
     * @param inputFolder
     * @return
     */
    public List<String> listDirectories(String inputFolder) {

        logger.info("Starting to scan folder entry : " + inputFolder);
        File fileEntry = new File(inputFolder);

        File[] fileArray = fileEntry.listFiles();

        for (File file : fileArray) {

            if (file.isDirectory()) {

            } else if (file.isFile()) {

            } else {
                System.out.println("Unkown entry type : " + file.getName());
            }
        }

        return null;
    }

}

