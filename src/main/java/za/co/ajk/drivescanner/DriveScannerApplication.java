package za.co.ajk.drivescanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import za.co.ajk.drivescanner.fileInfoHandlers.DirectoryScanner;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class DriveScannerApplication {

    @Autowired
    private static DirectoryScanner directoryScanner;

    private static Logger logger = LoggerFactory.getLogger(DriveScannerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DriveScannerApplication.class, args);

        if (logger.isInfoEnabled()) {
            logger.info("Started application");
        }

        //String fileName = args[0];
        String fileName = "C:/";
        directoryScanner.listDirectories(fileName);
    }
}
