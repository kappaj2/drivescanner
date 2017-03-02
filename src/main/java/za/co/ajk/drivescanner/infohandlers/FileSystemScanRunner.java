package za.co.ajk.drivescanner.infohandlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * This class will form the entry point to run the scanner and then generate the XML Outout.
 */
@Component
public class FileSystemScanRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(FileSystemScanRunner.class);

    @Autowired
    private FileSystemScanner fileSystemScanner;

    @Override
    public void run(String... args) throws Exception {

        logger.info("LOGGER -> Inside  directoryScanner run method....");
        fileSystemScanner.scanEntryPoint("D:\\\\Go\\blog\\content");
    }
}
