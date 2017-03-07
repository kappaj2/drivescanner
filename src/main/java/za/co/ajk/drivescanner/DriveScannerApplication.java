package za.co.ajk.drivescanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
public class DriveScannerApplication {

    private static Logger logger = LoggerFactory.getLogger(DriveScannerApplication.class);


    /**
     * Create a main method to execute when the Springboot application starts.
     * @param args
     */
    public static void main(String[] args) {

        if (logger.isInfoEnabled()) {
            logger.info("Starting application");
        }

        final ConfigurableApplicationContext context = SpringApplication.run(DriveScannerApplication.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(11);
        executor.setQueueCapacity(1);
        return executor;
    }


}
