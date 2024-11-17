package com.tradereport.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testEventCreation() {
        Event event = new Event(1L, "PARTY_A", "EMU_BANK", 100.0, "AUD");

        assertEquals("PARTY_A", event.getBuyerParty());
        assertEquals("EMU_BANK", event.getSellerParty());
        assertEquals(100.0, event.getPremiumAmount());
        assertEquals("AUD", event.getPremiumCurrency());
    }
}