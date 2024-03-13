package com.awesomeorg.cinemaapp.service_tests;

import com.awesomeorg.cinemaapp.entity.*;
import com.awesomeorg.cinemaapp.protocol.CreateReservationRequest;
import com.awesomeorg.cinemaapp.protocol.UpdateReservationRequest;
import com.awesomeorg.cinemaapp.repository.*;
import com.awesomeorg.cinemaapp.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.awesomeorg.cinemaapp.entity.Reservation.BookingStatus.CONFIRMED;
import static com.awesomeorg.cinemaapp.entity.Reservation.BookingStatus.UPDATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ShowtimeRepository showtimeRepository;

    @InjectMocks
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateReservation() {
        // Arrange
        CreateReservationRequest request = new CreateReservationRequest();
        request.setShowtimeId(1L);
        request.setTicketId(1L);
        request.setBookingStatus(CONFIRMED);

        Long clientId = 1L;

        Client client = new Client();
        Showtime showtime = new Showtime();
        Ticket ticket = new Ticket();

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));
        when(showtimeRepository.findById(request.getShowtimeId())).thenReturn(Optional.of(showtime));
        when(ticketRepository.findById(request.getTicketId())).thenReturn(Optional.of(ticket));
        when(reservationRepository.save(any(Reservation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Reservation reservation = reservationService.createReservation(request, clientId);

        // Assert
        assertNotNull(reservation, "The created reservation should not be null");
        assertEquals(request.getBookingStatus(), reservation.getBookingStatus());
        assertEquals(client, reservation.getClient());
        assertEquals(showtime, reservation.getShowtime());
        assertEquals(ticket, reservation.getTicket());
    }


    @Test
    void testCancelReservation() {
        // Arrange
        Long reservationId = 1L;
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(reservation));

        // Act
        reservationService.cancelReservation(reservationId);

        // Assert
        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void testGetCinemaSchedule() {
        // Arrange
        List<Showtime> expectedShowtimes = Arrays.asList(new Showtime(), new Showtime());
        when(reservationRepository.findActiveShowtimes()).thenReturn(expectedShowtimes);

        // Act
        List<Showtime> cinemaSchedule = reservationService.getCinemaSchedule();

        // Assert
        assertEquals(expectedShowtimes, cinemaSchedule, "Cinema schedule does not match expected showtimes");
    }

    @Test
    void testGetRecommendationsBasedOnHistory() {
        // Arrange
        Long clientId = 1L;
        List<Movie> expectedMovies = Arrays.asList();

        when(reservationRepository.findMoviesByClientId(clientId)).thenReturn(expectedMovies);

        List<Movie> allMovies = Arrays.asList();
        when(movieRepository.findAll()).thenReturn(allMovies);

        // Act
        List<Movie> recommendations = reservationService.getRecommendationsBasedOnHistory(clientId);

        // Assert
        assertEquals(expectedMovies.size(), recommendations.size(), "Recommendations size does not match expected size");
        assertThat(recommendations, containsInAnyOrder(expectedMovies.toArray(new Movie[0])));
    }


    @Test
    void testUpdateReservation() {
        // Arrange
        Long reservationId = 1L;
        Long clientId = 2L;

        UpdateReservationRequest request = new UpdateReservationRequest();
        request.setShowtime(new Showtime());
        request.setSeat(new Seat());
        request.setBookingStatus(UPDATED);

        Reservation existingReservation = new Reservation();
        when(reservationRepository.findById(reservationId)).thenReturn(Optional.of(existingReservation));
        when(clientRepository.findById(clientId)).thenReturn(Optional.of(new Client()));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(existingReservation);

        // Act
        Reservation updatedReservation = reservationService.updateReservation(reservationId, request, clientId);

        // Assert
        assertNotNull(updatedReservation, "The updated reservation should not be null");
        assertEquals(request.getBookingStatus(), updatedReservation.getBookingStatus());
    }
}
