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

import za.co.ajk.drivescanner.scanners.FileSystemScanner;

public class FileSystemScannerTask implements Runnable {

    private FileSystemScanner fileSystemScanner;
    private String entryPoint;

    public FileSystemScannerTask(FileSystemScanner fileSystemScanner, String entryPoint) {
        this.fileSystemScanner = fileSystemScanner;
        this.entryPoint = entryPoint;
    }

    @Override
    public void run() {
        fileSystemScanner.scanEntryPoint(entryPoint);
    }

}
