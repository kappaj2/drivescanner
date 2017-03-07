package za.co.ajk.drivescanner.event_listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Sample class to showcase the running of a method after the server completed startup.
 * There are options using the CommandLineRunner, ApplicationRunner or ApplicationEventListener.
 */
@Component
public class ApplicatioListenerImpl implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger logger = LoggerFactory.getLogger(ApplicatioListenerImpl.class);

//    @Autowired
//    private FileSystemScannerService fileSystemScannerService;

    /**
     * Implemented event handler to run.
     *
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        logger.info("ApplicationStartEVent = start scanning");
//        fileSystemScannerService.setFileSystemEntryPoint("D:\\\\tomcat7_centre");
//        fileSystemScannerService.startSCanning();
    }
}
