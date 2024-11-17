package com.tradereport.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tradereport.model.Event;
import com.tradereport.service.EventService;

class EventControllerTest {

    private final EventService eventService = Mockito.mock(EventService.class);
    private final EventController eventController = new EventController(eventService);

    @Test
    void testGetFilteredEvents() {
        List<Event> mockEvents = List.of(
                new Event(1L, "PARTY_A", "EMU_BANK", 100.0, "AUD"),
                new Event(2L, "PARTY_B", "BISON_BANK", 200.0, "USD")
        );

        when(eventService.getFilteredEvents()).thenReturn(mockEvents);

        ResponseEntity<List<Event>> response = eventController.getFilteredEvents();

        assertNotNull(response.getBody(), "Response body should not be null");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals("EMU_BANK", response.getBody().get(0).getSellerParty());
    }
}