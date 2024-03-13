package com.awesomeorg.cinemaapp.repository.specification;

import com.awesomeorg.cinemaapp.entity.Reservation;
import com.awesomeorg.cinemaapp.protocol.ReservationQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ReservationSpecification {

    // Method to create JPA specification based on query parameters
    public static Specification<Reservation> createSpecification(final ReservationQuery query) {
        // Get a list of specifications
        List<Specification<Reservation>> specifications = getSpecificationList(query);
        // Combine specifications using the AND operation
        return specifications.stream().reduce(Specification.where(null), Specification::and);
    }

    // Method to generate a list of specifications based on query parameters
    private static List<Specification<Reservation>> getSpecificationList(final ReservationQuery query) {
        List<Specification<Reservation>> specifications = new ArrayList<>();

        // Add specification for equality of the showtime field if it is not empty
        addEqualsSpecification(specifications, query.getShowtime(), "showtime");
        // Similar actions for other query parameters
        addEqualsSpecification(specifications, query.getClient(), "client");
        addEqualsSpecification(specifications, query.getSeat(), "seat");
        addEqualsSpecification(specifications, query.getBookingStatus(), "bookingStatus");
        addDateSpecification(specifications, query.getReservationDate(), "reservationDate");

        return specifications;
    }

    // Method to add a specification for equality of the field value if it is not empty
    private static <T> void addEqualsSpecification(List<Specification<Reservation>> specifications, T value, String fieldName) {
        if (value != null) {
            specifications.add((root, query, builder) -> builder.equal(root.get(fieldName), value));
        }
    }

    // Method to add a specification for date check on greater than or equal if it is not empty
    private static void addDateSpecification(List<Specification<Reservation>> specifications, LocalDateTime value, String fieldName) {
        if (value != null) {
            specifications.add((root, query, builder) -> builder.greaterThanOrEqualTo(root.get(fieldName), value));
        }
    }
}

