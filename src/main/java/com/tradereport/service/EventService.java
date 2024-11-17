package com.tradereport.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tradereport.model.Event;
import com.tradereport.repository.EventRepository;
import com.tradereport.util.AnagramChecker;

/**
 * Service layer for handling business logic related to trade events.
 */
@Service
public class EventService {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves events that meet the specified filter criteria.
     * 
     * @return List of filtered events.
     */
    public List<Event> getFilteredEvents() {
        List<Event> events = repository.findFilteredEvents();
        
        // Filter out events where buyer and seller are anagrams
        return events.stream()
                .filter(event -> !areAnagrams(event.getSellerParty(), event.getBuyerParty()))
                .collect(Collectors.toList());
    }

    /**
     * Checks if two strings are anagrams.
     * 
     * @param str1 First string.
     * @param str2 Second string.
     * @return True if the strings are anagrams; otherwise, false.
     */
    public boolean areAnagrams(String str1, String str2) {
        return AnagramChecker.isAnagram(str1, str2);
    }

    /**
     * Saves a trade event to the database.
     * 
     * @param event The Event object to be saved.
     */
    public void saveEvent(Event event) {
        repository.save(event);
    }
    
}