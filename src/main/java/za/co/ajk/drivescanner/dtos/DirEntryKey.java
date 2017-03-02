package za.co.ajk.drivescanner.dtos;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Setter;

@lombok.Getter
@lombok.Setter
@lombok.ToString
@lombok.EqualsAndHashCode
public class DirEntryKey {
    private int directoryEntry;
    private String directoryName;
}
