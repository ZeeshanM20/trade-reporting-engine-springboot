package com.tradereport.repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tradereport.model.Event;

@SpringBootTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testFindFilteredEvents() {
        List<Event> events = eventRepository.findFilteredEvents();
        assertEquals(6, events.size());
    }
}