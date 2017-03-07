package za.co.ajk.drivescanner.commandline_runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.scanners.FileSystemScanner;

import java.util.List;

/**
 * Sample class to showcase the running of a method after the server completed startup.
 * There are options using the CommandLineRunner, ApplicationRunner or ApplicationEventListener.
 */
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private static Logger logger = LoggerFactory.getLogger(ApplicationRunnerImpl.class);

    @Autowired
    private FileSystemScanner fileSystemScanner;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {

        List<String> notOpList = applicationArguments.getNonOptionArgs();

        logger.info("Started command line runner with args : ");
        for (String str : notOpList) {
            logger.info("ApplicationRunner NonOpt Argument : " + str);
        }

        String[] args = applicationArguments.getSourceArgs();
        for (String ent : args) {
            logger.info("ApplicationRunner Source Args entry : " + ent);
        }

        String entryPoint = args[0];
        fileSystemScanner.scanEntryPoint(entryPoint);

    }
}
