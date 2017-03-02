package za.co.ajk.drivescanner.dtos;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ResultDirectoryStructure {

    /**
     * Local data structure that will use the directory name as the key.
     */
    private Map<String, List<FileEntryDTO>> directoryMap = new TreeMap<>(
            (o1, o2) -> o1.compareTo(o2)
    );

    /**
     * Add and entry to a directory if the directory exists
     *
     * @param directoryName
     * @param fileEntryDTO
     */
    public void addEntry(String directoryName, FileEntryDTO fileEntryDTO) {

        if (directoryMap.containsKey(directoryName)) {
            directoryMap.get(directoryName).add(fileEntryDTO);
        } else {
            List<FileEntryDTO> fileList = new ArrayList<>(Arrays.asList(fileEntryDTO));
            directoryMap.put(directoryName, fileList);
        }
    }

    /**
     * Return the full Map data structure.
     *
     * @return
     */
    public Map<String, List<FileEntryDTO>> getFullStructure() {
        return directoryMap;
    }
}
