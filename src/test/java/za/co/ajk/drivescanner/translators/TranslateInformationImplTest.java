package za.co.ajk.drivescanner.translators;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import za.co.ajk.drivescanner.DriveScannerApplication;
import za.co.ajk.drivescanner.dtos.FileEntryDTO;
import za.co.ajk.drivescanner.dtos.ScanInfoDTO;

import javax.xml.bind.JAXBException;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DriveScannerApplication.class)
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
        fixture.whenTranslateToXMLJaxb();
        fixture.thenXMLStringShouldBe(expectedString);

    }

    @Test
    public void testScanInfoObjectTpXML() throws Exception {

        ScanInfoDTO dto = new ScanInfoDTO();
        dto.setScannedFolder("c:/test");
        dto.setDateFolderScanStarted(LocalDateTime.now());
        dto.setDateFolderScanCompleted(LocalDateTime.now());

        String expectedString = "<FILE_ENTRY fileName=\"TestFileName\" fileSize=\"120\" hidden=\"false\"/>";
        fixture.givenThatScanEntryDTO(dto);
        fixture.whenGenerateScanHeader();
        fixture.thenXMLStringShouldBe(expectedString);

    }

    private class Fixture {

        private FileEntryDTO fileEntryDTO;
        private ScanInfoDTO scanInfoDTO;

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

        public void whenTranslateToXMLJaxb() throws JAXBException {
            xmlString = translateInformation.translateToXMLJaxb(this.fileEntryDTO);
        }

        public void whenGenerateScanHeader() throws JAXBException {
            xmlString = translateInformation.generateFileHeader(scanInfoDTO);
        }

        public void whenTranslateToXML() throws JsonProcessingException {
            xmlString = translateInformation.translateToXML(this.fileEntryDTO);
        }

        public void thenXMLStringShouldBe(String xmlString) {
            assertEquals(xmlString, this.xmlString);
        }
    }
}
