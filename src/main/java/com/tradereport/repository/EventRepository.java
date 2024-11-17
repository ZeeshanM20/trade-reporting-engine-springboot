package com.tradereport.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tradereport.model.Event;

/**
 * Repository interface for managing Event entities.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    /**
     * Finds filtered events based on predefined criteria.
     * 
     * @return List of events matching the criteria.
     */
    @Query("SELECT e FROM Event e WHERE " +
           "((e.sellerParty = 'EMU_BANK' AND e.premiumCurrency = 'AUD') OR " +
           "(e.sellerParty = 'BISON_BANK' AND e.premiumCurrency = 'USD')) " 
           )
    List<Event> findFilteredEvents();
}