package com.awesomeorg.cinemaapp.service_tests;

import com.awesomeorg.cinemaapp.entity.Seat;
import com.awesomeorg.cinemaapp.protocol.SeatOccupancyRequest;
import com.awesomeorg.cinemaapp.repository.SeatRepository;
import com.awesomeorg.cinemaapp.service.SeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class SeatServiceTest {
    @Mock
    private SeatRepository seatRepository;

    @InjectMocks
    private SeatService seatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateOccupiedSeats() {
        // Arrange
        SeatOccupancyRequest request = new SeatOccupancyRequest();

        // Act
        List<Seat> occupiedSeats = seatService.generateAndSaveOccupiedSeats(request);

        // Assert
        assertNotNull(occupiedSeats);
        assertEquals(1, occupiedSeats.size());

        Seat occupiedSeat = occupiedSeats.get(0);

        assertEquals(request.getRow(), occupiedSeat.getRow());
        assertEquals(request.getNumber(), occupiedSeat.getNumber());
        assertTrue(occupiedSeat.isOccupied());

        // Verify that the occupied seat is saved in the repository
        verify(seatRepository, times(1)).save(ArgumentMatchers.any(Seat.class));
    }

}


