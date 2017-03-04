package za.co.ajk.drivescanner;

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
