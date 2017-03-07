package za.co.ajk.drivescanner.dtos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import za.co.ajk.drivescanner.scanners.FileSystemScanner;

import java.util.*;

@Component
public class DirectoryStructureMap {

    private Logger logger = LoggerFactory.getLogger(DirectoryStructureMap.class);

    private Set<String> directorySet = new TreeSet<>();

    public void addEntry(String directory){

        /*
            This directory's parent is the string up to the last /
         */
        int lastPos = directory.lastIndexOf('\\');
        logger.info("Directory value |"+directory+"| and pos is "+lastPos);

        String parentDirectory = directory.substring(0, lastPos);
        if(!directorySet.contains(directory)) {
            directorySet.add(directory);
        }
    }

    public Set<String> getDirectorySet(){
        return directorySet;
    }
}
