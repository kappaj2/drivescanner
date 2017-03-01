package za.co.ajk.drivescanner.utilities;

/**
 * Convert the input filename to an OS specific format.
 */
public final class StringFormatters {

    /**
     * Convert the file name to the OS specific format.
     * @param inputFileName
     * @return
     */
    public static String formatOsFileNameFormat(String inputFileName){

        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.contains("windows")) {
            return inputFileName.replaceAll("/", "\\\\");
        } else {
            return inputFileName.replaceAll("\\\\", "/");
        }
    }
}
