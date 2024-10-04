package com.korede.bikerace.service;

import com.korede.bikerace.exception.RaceNotFoundException;
import com.korede.bikerace.model.*;
import com.korede.bikerace.repository.RaceRepository;
import com.korede.bikerace.repository.RaceResultRepository;
import com.korede.bikerace.repository.RiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RaceServiceTest {

    @InjectMocks
    private RaceService raceService;

    @Mock
    private RaceRepository raceRepository;


    private Race race;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        race = new Race();
        race.setId(1L);
    }


    @Test
    void getRidersDidNotFinish_RaceNotFound() {
        when(raceRepository.findById(1L)).thenReturn(Optional.empty());

        RaceNotFoundException exception = assertThrows(RaceNotFoundException.class, () -> {
            raceService.getRidersDidNotFinish(1L);
        });
        assertEquals("Race not found with ID: 1", exception.getMessage());
    }


    @Test
    void getRidersNotInRace_RaceNotFound() {
        when(raceRepository.findById(1L)).thenReturn(Optional.empty());
        RaceNotFoundException exception = assertThrows(RaceNotFoundException.class, () -> {
            raceService.getRidersNotInRace(1L);
        });
        assertEquals("Race not found with ID: 1", exception.getMessage());
    }
}