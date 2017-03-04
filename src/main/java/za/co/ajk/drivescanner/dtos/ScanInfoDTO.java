package za.co.ajk.drivescanner.dtos;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import za.co.ajk.drivescanner.translators.LocalDateTimeAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "FILE_ENTRY")
@XmlType(propOrder = {"scannedFolder", "dateFolderScanStarted", "dateFolderScanCompleted"})
public final class ScanInfoDTO {

    private String scannedFolder;

    private LocalDateTime dateFolderScanStarted;

    private LocalDateTime  dateFolderScanCompleted;

    @JacksonXmlProperty(isAttribute = true)
    public String getScannedFolder() {
        return scannedFolder;
    }

    public void setScannedFolder(String scannedFolder) {
        this.scannedFolder = scannedFolder;
    }

   @JacksonXmlProperty(isAttribute = true)
   @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime  getDateFolderScanStarted() {
        return dateFolderScanStarted;
    }

    public void setDateFolderScanStarted(LocalDateTime dateFolderScanStarted) {
        this.dateFolderScanStarted = dateFolderScanStarted;
    }

   @JacksonXmlProperty(isAttribute = true)
   @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime  getDateFolderScanCompleted() {
        return dateFolderScanCompleted;
    }

    public void setDateFolderScanCompleted(LocalDateTime  dateFolderScanCompleted) {
        this.dateFolderScanCompleted = dateFolderScanCompleted;
    }
}
