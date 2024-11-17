package com.tradereport.service;

import java.io.File;
import java.util.List;

import com.tradereport.model.Event;

/**
 * Service for processing XML files and persisting parsed events.
 */
public class XmlFileProcessor {

    private final XmlParser xmlParser;
    private final EventService eventService;

    public XmlFileProcessor(XmlParser xmlParser, EventService eventService) {
        this.xmlParser = xmlParser;
        this.eventService = eventService;
    }

    /**
     * Processes an array of XML files and saves the parsed events.
     * 
     * @param xmlFiles Array of XML files.
     */
    public void processFiles(File[] xmlFiles) {
        for (File xmlFile : xmlFiles) {
            try {
                List<Event> events = xmlParser.parseXml(xmlFile);
                for (Event event : events) {
                    eventService.saveEvent(event);
                }
            } catch (Exception e) {
                System.err.println("Error parsing file " + xmlFile.getName() + ": " + e.getMessage());
            }
        }
    }

}