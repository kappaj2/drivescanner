package za.co.ajk.drivescanner.commandline_runners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Sample class to showcase the running of a method after the server completed startup.
 * There are options using the CommandLineRunner, ApplicationRunner or ApplicationEventListener.
 */
@Component
public class CommandLineRunnerImpl implements org.springframework.boot.CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(CommandLineRunnerImpl.class);

    @Override
    public void run(String... strings) throws Exception {

        for (String ent : strings) {
            logger.info("CommandLineRunner lstrings  entry : " + ent);
        }
    }
}
