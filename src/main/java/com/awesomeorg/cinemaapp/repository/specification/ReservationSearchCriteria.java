package com.awesomeorg.cinemaapp.repository.specification;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationSearchCriteria {
    // Data class representing a search criteria for reservations

    private String key;    // The attribute or field name to search
    private Object value;  // The value to match for the specified attribute
}
