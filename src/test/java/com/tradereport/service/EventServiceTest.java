package com.tradereport.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.tradereport.model.Event;
import com.tradereport.repository.EventRepository;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFilteredEvents_MatchingCriteria() {
        List<Event> mockEvents = List.of(
                new Event(1L, "PARTY_A", "EMU_BANK", 100.0, "AUD"),
                new Event(2L, "PARTY_B", "BISON_BANK", 200.0, "USD")
        );

        when(eventRepository.findFilteredEvents()).thenReturn(mockEvents);

        List<Event> result = eventService.getFilteredEvents();
        assertEquals(2, result.size());
        verify(eventRepository, times(1)).findFilteredEvents();
    }

    @Test
    void testGetFilteredEvents_EmptyDatabase() {
        when(eventRepository.findFilteredEvents()).thenReturn(List.of());
        List<Event> result = eventService.getFilteredEvents();
        assertEquals(0, result.size());
    }

    @Test
    void testGetFilteredEvents_NullFields() {
        List<Event> mockEvents = List.of(
                new Event(1L, null, "EMU_BANK", 100.0, "AUD"),
                new Event(2L, "PARTY_B", null, 200.0, "USD")
        );

        when(eventRepository.findFilteredEvents()).thenReturn(mockEvents);

        List<Event> result = eventService.getFilteredEvents();
        assertEquals(2, result.size());
    }
}