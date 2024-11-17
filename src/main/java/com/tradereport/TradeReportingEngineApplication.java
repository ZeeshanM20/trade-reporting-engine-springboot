package com.tradereport;

import java.io.File;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tradereport.service.EventService;
import com.tradereport.service.XmlFileProcessor;
import com.tradereport.service.XmlParser;
import com.tradereport.util.XmlFileReader;

@SpringBootApplication
public class TradeReportingEngineApplication implements CommandLineRunner {

    private final EventService eventService;

    public TradeReportingEngineApplication(EventService eventService) {
        this.eventService = eventService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TradeReportingEngineApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String directoryPath = "src/main/resources/data";
        File[] xmlFiles = XmlFileReader.getXmlFiles(directoryPath);

        if (xmlFiles != null && xmlFiles.length > 0) {
            XmlParser xmlParser = new XmlParser();
            XmlFileProcessor processor = new XmlFileProcessor(xmlParser, eventService);
            processor.processFiles(xmlFiles);
        } else {
            System.out.println("No XML files found in the directory.");
        }
    }
}