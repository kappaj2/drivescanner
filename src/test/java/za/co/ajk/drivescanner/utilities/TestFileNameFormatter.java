package za.co.ajk.drivescanner.utilities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for FileName formatter.
 */
public class TestFileNameFormatter {

    @Test
    public void testWindowsFileNameFormat(){
        /*
            Start with Linux format
         */
        String fileName = "d://test123/";
        String expected = "d:\\\\test123\\";

        String newName = StringFormatters.formatOsFileNameFormat(fileName);

        if(System.getProperty("os.name").toLowerCase().contains("windows")) {
            assertEquals(expected, newName);
        }
    }
}
