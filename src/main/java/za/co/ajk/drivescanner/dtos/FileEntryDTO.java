package za.co.ajk.drivescanner.dtos;

@lombok.Getter
@lombok.Setter
@lombok.ToString
@lombok.EqualsAndHashCode
public class FileEntryDTO {

    private String fileName;
    private Long fileSize;
    private boolean isHidden;
}
