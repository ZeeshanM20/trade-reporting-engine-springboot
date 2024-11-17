package com.tradereport.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.tradereport.service.EventService;

class EventUtilityTest {

    private final EventService eventService = new EventService(null);

    @Test
    void testAnagramCheck() {
        assertTrue(eventService.areAnagrams("EMU_BANK", "KANMU_EB"));
        assertFalse(eventService.areAnagrams("EMU_BANK", "PARTY_A"));
        assertTrue(eventService.areAnagrams("EMU_BANK", "EMU_BANK"));
    }
}