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

package za.co.ajk.drivescanner.executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import za.co.ajk.drivescanner.scanners.FileSystemScanner;

@Service
public class FileSystemScannerService {

    private String fileSystemEntryPoint;

    public void setFileSystemEntryPoint(String fileSystemEntryPoint) {
        this.fileSystemEntryPoint = fileSystemEntryPoint;
    }

    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private FileSystemScanner fileSystemScanner;

    public void startSCanning() {
        FileSystemScannerTask task = new FileSystemScannerTask(fileSystemScanner, fileSystemEntryPoint);
        taskExecutor.execute(task);
    }
}
