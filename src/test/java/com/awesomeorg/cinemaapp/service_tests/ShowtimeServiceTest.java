package com.awesomeorg.cinemaapp.service_tests;

import com.awesomeorg.cinemaapp.entity.Movie;
import com.awesomeorg.cinemaapp.entity.Showtime;
import com.awesomeorg.cinemaapp.repository.ShowtimeRepository;
import com.awesomeorg.cinemaapp.service.ShowtimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ShowtimeServiceTest {
    @Mock
    private ShowtimeRepository showtimeRepository;

    @InjectMocks
    private ShowtimeService showtimeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllMovies() {
        // Arrange
        when(showtimeRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Movie> allMovies = showtimeService.getAllMovies();

        // Assert
        assertNotNull(allMovies);
        assertTrue(allMovies.isEmpty(), "All movies list should be empty");
    }

    @Test
    void testGetMovieById() {
        // Arrange
        Long movieId = 1L;
        when(showtimeRepository.findById(movieId)).thenReturn(Optional.of(new Showtime(new Movie())));

        // Act
        Movie movie = showtimeService.getMovieById(movieId);

        // Assert
        assertNotNull(movie);
        assertEquals(null, movie.getTitle(), "Incorrect movie title");
    }

}
