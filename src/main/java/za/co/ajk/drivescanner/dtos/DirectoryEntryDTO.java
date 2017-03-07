package za.co.ajk.drivescanner.dtos;

import za.co.ajk.drivescanner.translators.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@lombok.ToString
@lombok.EqualsAndHashCode
@XmlRootElement(name = "DIRECTORY_ENTRY")
@XmlType(propOrder = {"directoryName", "length", "freeSpace", "totalSpace", "usableSpace", "lastModified"})
public final class DirectoryEntryDTO extends BaseDTO<DirectoryEntryDTO> {

    private String directoryName;
    private Long length;
    private boolean isHidden;
    private boolean canExecute;
    private boolean canRead;
    private boolean canWrite;
    private boolean exists;
    private Long freeSpace;
    private Long totalSpace;
    private Long usableSpace;
    private LocalDateTime lastModified;


    @XmlAttribute
    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    @XmlAttribute
    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @XmlAttribute
    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    @XmlAttribute
    public boolean isCanExecute() {
        return canExecute;
    }

    public void setCanExecute(boolean canExecute) {
        this.canExecute = canExecute;
    }

    @XmlAttribute
    public boolean isCanRead() {
        return canRead;
    }

    public void setCanRead(boolean canRead) {
        this.canRead = canRead;
    }

    @XmlAttribute
    public boolean isCanWrite() {
        return canWrite;
    }

    public void setCanWrite(boolean canWrite) {
        this.canWrite = canWrite;
    }

    @XmlAttribute
    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    @XmlAttribute
    public Long getFreeSpace() {
        return freeSpace;
    }

    public void setFreeSpace(Long freeSpace) {
        this.freeSpace = freeSpace;
    }

    @XmlAttribute
    public Long getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(Long totalSpace) {
        this.totalSpace = totalSpace;
    }

    @XmlAttribute
    public Long getUsableSpace() {
        return usableSpace;
    }

    public void setUsableSpace(Long usableSpace) {
        this.usableSpace = usableSpace;
    }

    @XmlAttribute
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
