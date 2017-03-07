
package za.co.ajk.drivescanner.utilities;

import org.apache.xerces.dom.DocumentImpl;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public final class FileListing {

    private static Document xmldoc;

    @Test
    public void test12() throws Exception {

        File startingDirectory = new File("c:\\tmp\\");
        final Date date = new Date();
        String dateFormated;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy_hh-mm-ss");
        dateFormated = myFormat.format(date);

        xmldoc = new DocumentImpl();
        Element root = xmldoc.createElement("DIRECTORY_LISTING");

        recursiveList(startingDirectory, root, startingDirectory.getName());

        xmldoc.appendChild(root);

        FileOutputStream fos = new FileOutputStream("d:\\\\temp\\2222222.xml");
        // XERCES 1 or 2 additionnal classes.
        OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
        of.setIndent(1);
        of.setIndenting(true);
        XMLSerializer serializer = new XMLSerializer(fos, of);
        // As a DOM Serializer
        serializer.asDOMSerializer();
        serializer.serialize(xmldoc.getDocumentElement());
        fos.close();

    }

    private void recursiveList(File currentFile, Element parent, String parentdir) {
        File[] listOfFiles = currentFile.listFiles();
        String fileType;

        for (File aFile : listOfFiles) {

            fileType = (aFile.isDirectory()) ? "folder" : "file";

            String fType;
            if(fileType.equals("file")){
                fType = "FILE_ENTRY";
            }else{
                fType = "DIRECTORY_ENTRY";
            }
            Element e = xmldoc.createElementNS(null, "File_Entry");




            e.setAttributeNS(null, "FileType", fileType);
            e.setAttributeNS(null, "ParentDirectory", parentdir);
            e.setAttributeNS(null, "FileName", aFile.getName());

            if (aFile.isDirectory()) {
                recursiveList(aFile, e, aFile.getName());
                parent.appendChild(e);
            }
        }

    }
}