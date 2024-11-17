package com.tradereport.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradereport.model.Event;
import com.tradereport.service.EventService;

/**
 * REST Controller to handle API requests related to trade events.
 */
@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * Fetches filtered trade events based on the defined criteria.
     * 
     * @return ResponseEntity containing a list of filtered events.
     */
    @GetMapping("/filtered")
    public ResponseEntity<List<Event>>getFilteredEvents() {
        try {
            List<Event> events = eventService.getFilteredEvents();
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

}
