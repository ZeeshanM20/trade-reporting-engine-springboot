package com.tradereport.util;

import java.io.File;
import java.io.FilenameFilter;

public class XmlFileReader {

    public static File[] getXmlFiles(String directoryPath) {
        File directory = new File(directoryPath);

        // Only filter .xml files
        FilenameFilter xmlFilter = (dir, name) -> name.toLowerCase().endsWith(".xml");

        return directory.listFiles(xmlFilter);
    }
}