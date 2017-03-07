package za.co.ajk.drivescanner.translators;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.ajk.drivescanner.TestConfiguration;
import za.co.ajk.drivescanner.dtos.DirectoryEntryDTO;
import za.co.ajk.drivescanner.dtos.FileEntryDTO;
import za.co.ajk.drivescanner.dtos.ScanInfoDTO;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class TranslateInformationImplTest {

    @Autowired
    private TranslateInformationImpl translateInformation;

    private Fixture fixture;

    @Before
    public void setup() throws Exception {
        fixture = new Fixture();
    }

    @Test
    public void testFileEntryObjectToXML() throws Exception {

        FileEntryDTO dto = new FileEntryDTO();
        dto.setFileSize(120l);
        dto.setFileName("TestFileName");
        String expectedString = "<FILE_ENTRY fileName=\"TestFileName\" fileSize=\"120\" hidden=\"false\"/>";

        fixture.givenThatFileEntryDTO(dto);
        fixture.whenTranslateFileEntryDTOToXML();
        fixture.thenXMLStringShouldBe(expectedString);

    }

    // @Test
    public void testScanInfoObjectTpXML() throws Exception {

        ScanInfoDTO dto = new ScanInfoDTO();
        dto.setScannedFolder("c:/test");
        dto.setDateFolderScanStarted(LocalDateTime.now());
        dto.setDateFolderScanCompleted(LocalDateTime.now());

        String expectedString = "<FILE_ENTRY fileName=\"TestFileName\" fileSize=\"120\" hidden=\"false\"/>";
        fixture.givenThatScanEntryDTO(dto);
        fixture.whenTranslateScanInfoDTOToXML();
        fixture.thenXMLStringShouldBe(expectedString);

    }

    @Test
    public void testDirectoryObjectTpXML() throws Exception {

        LocalDateTime specificDate = LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30);

        DirectoryEntryDTO dto = new DirectoryEntryDTO();
        dto.setCanExecute(true);
        dto.setCanRead(true);
        dto.setCanWrite(false);
        dto.setHidden(false);
        dto.setExists(true);
        dto.setDirectoryName("C:/Testing123");
        dto.setFreeSpace(123456L);
        dto.setTotalSpace(2345L);
        dto.setUsableSpace(345445555L);
        dto.setLength(221123L);
        dto.setLastModified(specificDate);

        String expectedString = "<DIRECTORY_ENTRY directoryName=\"C:/Testing123\" length=\"221123\" freeSpace=\"123456\" totalSpace=\"2345\" usableSpace=\"345445555\" lastModified=\"2014-01-01 10:10:30\" canExecute=\"true\" canRead=\"true\" canWrite=\"false\" exists=\"true\" hidden=\"false\"/>";

        fixture.givenThatDirectoryEntryDTO(dto);
        fixture.whenTranslateDirectoryEntryDTOToXML();
        fixture.thenXMLStringShouldBe(expectedString);

    }

    private class Fixture {

        private FileEntryDTO fileEntryDTO;
        private ScanInfoDTO scanInfoDTO;
        private DirectoryEntryDTO directoryEntryDTO;

        private String xmlString;

        public Fixture() throws Exception {
            //initMocks(this);
            //translateInformation = Mockito.mock(TranslateInformationImpl.class);
        }


        public void givenThatFileEntryDTO(FileEntryDTO fileEntryDTO) {
            this.fileEntryDTO = fileEntryDTO;
        }

        public void givenThatScanEntryDTO(ScanInfoDTO scanInfoDTO) {
            this.scanInfoDTO = scanInfoDTO;
        }

        public void givenThatDirectoryEntryDTO(DirectoryEntryDTO directoryEntryDTO) {
            this.directoryEntryDTO = directoryEntryDTO;
        }

        public void whenTranslateFileEntryDTOToXML() throws JAXBException {
            xmlString = translateInformation.translateFileEntryDTOToXML(fileEntryDTO);
        }

        public void whenTranslateScanInfoDTOToXML() throws JAXBException {
            xmlString = translateInformation.translateScanInfoDTOToXML(scanInfoDTO);
        }

        public void whenTranslateDirectoryEntryDTOToXML() throws JAXBException {
            xmlString = translateInformation.translateDirectoryEntryDTOToXML(directoryEntryDTO);
        }


        public void thenXMLStringShouldBe(String xmlString) {
            assertEquals(xmlString, this.xmlString);
        }
    }
}
