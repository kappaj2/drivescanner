package za.co.ajk.drivescanner.dtos;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ResultDirectoryStructure {

    /**
     * Local data structure that will use the directory name as the key. Directory name is the unique key
     */
    private Map<DirectoryEntryDTO, List<? super BaseDTO>> directoryMap = new TreeMap<>(
            (o1, o2) -> o1.getDirectoryName().compareTo(o2.getDirectoryName())
    );

    /**
     * Add and entry to a directory if the directory exists
     *
     * @param directoryDTO
     * @param <?            extends BaseDTO>
     */
    public void addEntry(DirectoryEntryDTO directoryDTO, BaseDTO<? extends BaseDTO> dto) {

        if (directoryMap.containsKey(directoryDTO)) {
              directoryMap.get(directoryDTO).add(dto);
        } else {
            List<? super BaseDTO> fileList = new ArrayList<>(Arrays.asList(dto));
            directoryMap.put(directoryDTO, fileList);
        }
    }

    /**
     * Return the full Map data structure.
     *
     * @return
     */
    public Map<DirectoryEntryDTO, List<? super BaseDTO>> getFullStructure() {
        return directoryMap;
    }
}
