package za.co.ajk.drivescanner.dtos;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@lombok.ToString
@lombok.EqualsAndHashCode
@XmlRootElement(name = "FILE_ENTRY")
@XmlType(propOrder = {"fileName", "fileSize"})
public final class FileEntryDTO extends BaseDTO<FileEntryDTO> {

    private String fileName;
    private Long fileSize;
    private boolean isHidden;

    @XmlAttribute
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @XmlAttribute
    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    @XmlAttribute
    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
