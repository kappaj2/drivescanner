package za.co.ajk.drivescanner;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import za.co.ajk.drivescanner.event_listeners.ApplicatioListenerImpl;

/**
 * Configuration class for testing. Exclude the ApplicationEventListener class as we don't want that to fire during unit testing.
 */
@Configuration()
@ComponentScan(basePackages = {"za.co.ajk.drivescanner"},
        excludeFilters = @ComponentScan.Filter(value = ApplicatioListenerImpl.class, type = FilterType.ASSIGNABLE_TYPE)
)
public class TestConfiguration {


}
