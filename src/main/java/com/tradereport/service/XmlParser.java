package com.tradereport.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.tradereport.model.Event;

/**
 * Utility class for parsing trade events from XML files.
 */
public class XmlParser {
    
    /**
     * Parses an XML file and extracts trade event data into a list of Event objects.
     * @param file The XML file to be parsed.
     * @return A list of Event objects.
     */

    public List<Event> parseXml(File file) {
        List<Event> events = new ArrayList<>();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(file);
            XPath xpath = XPathFactory.newInstance().newXPath();

            NodeList buyerParties = (NodeList) xpath.evaluate("//buyerPartyReference/@href", doc, XPathConstants.NODESET);
            NodeList sellerParties = (NodeList) xpath.evaluate("//sellerPartyReference/@href", doc, XPathConstants.NODESET);
            NodeList amounts = (NodeList) xpath.evaluate("//paymentAmount/amount", doc, XPathConstants.NODESET);
            NodeList currencies = (NodeList) xpath.evaluate("//paymentAmount/currency", doc, XPathConstants.NODESET);

            for (int i = 0; i < buyerParties.getLength(); i++) {
                String buyer = buyerParties.item(i).getTextContent();
                String seller = sellerParties.item(i).getTextContent();
                double amount = Double.parseDouble(amounts.item(i).getTextContent());
                String currency = currencies.item(i).getTextContent();

                events.add(new Event(null, buyer, seller, amount, currency));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
}