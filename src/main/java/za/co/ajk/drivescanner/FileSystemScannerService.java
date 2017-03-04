package za.co.ajk.drivescanner;

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
