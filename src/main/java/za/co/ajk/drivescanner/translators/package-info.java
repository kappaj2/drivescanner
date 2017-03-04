@XmlJavaTypeAdapters({
        @XmlJavaTypeAdapter(type=LocalDateTime.class,
                value=LocalDateTimeAdapter.class),
})
package za.co.ajk.drivescanner.translators;

import java.time.LocalDateTime;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
