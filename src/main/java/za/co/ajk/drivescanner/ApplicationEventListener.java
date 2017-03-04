package za.co.ajk.drivescanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger logger = LoggerFactory.getLogger(ApplicationEventListener.class);

    @Autowired
    private FileSystemScannerService fileSystemScannerService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.info("ApplicationStartEVent = start scanning");
        fileSystemScannerService.setFileSystemEntryPoint("D:\\\\tomcat7_centre");
        fileSystemScannerService.startSCanning();
    }
}
