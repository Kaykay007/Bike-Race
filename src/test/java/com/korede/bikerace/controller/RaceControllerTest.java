package com.korede.bikerace.controller;

import com.korede.bikerace.exception.RaceNotFoundException;
import com.korede.bikerace.model.ApiResponse;
import com.korede.bikerace.model.RiderDto;
import com.korede.bikerace.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RaceControllerTest {

    @InjectMocks
    private RaceController raceController;

    @Mock
    private RaceService raceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getFastestRiders_Success() {
        List<RiderDto> fastestRiders = getRiderDtos();
        when(raceService.getFastestRidersForRace(1L)).thenReturn(fastestRiders);
        ResponseEntity<ApiResponse<List<RiderDto>>> result = raceController.getFastestRiders(1L);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        System.out.println("this is result" + result.getBody());
        assertEquals("Fastest riders retrieved successfully", Objects.requireNonNull(result.getBody()).getMessage());
        assertEquals(3, result.getBody().getData().size());
        assertEquals("John Doe", result.getBody().getData().get(0).getName());
        assertEquals("Jane Smith", result.getBody().getData().get(1).getName());
        assertEquals("Chudi Sani", result.getBody().getData().get(2).getName());
    }

    private static List<RiderDto> getRiderDtos() {
        LocalDateTime startTime1 = LocalDateTime.parse("2024-10-10T09:00:00");
        LocalDateTime finishTime1 = LocalDateTime.parse("2024-10-10T09:02:30");
        LocalDateTime finishTime2 = LocalDateTime.parse("2024-10-10T09:03:45");
        LocalDateTime finishTime4 = LocalDateTime.parse("2024-10-10T09:04:40");

        return List.of(
                new RiderDto(1L, "John Doe", startTime1, finishTime1),
                new RiderDto(2L, "Jane Smith", startTime1, finishTime2),
                new RiderDto(6L, "Chudi Sani", startTime1, finishTime4)

        );

    }

    @Test
    void getFastestRiders_RaceNotFound() {
        when(raceService.getFastestRidersForRace(1L)).thenThrow(new RaceNotFoundException("Race not found with ID: 1"));

        Exception exception = assertThrows(RaceNotFoundException.class, () -> {
            raceController.getFastestRiders(1L);
        });
        assertEquals("Race not found with ID: 1", exception.getMessage());
    }

    @Test
    void getRidersDidNotFinish_Success() {

        List<RiderDto> riders = List.of(new RiderDto(1L, "John Doe", null, null));
        when(raceService.getRidersDidNotFinish(1L)).thenReturn(riders);
        ApiResponse<List<RiderDto>> response = raceController.getRidersDidNotFinish(1L);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Riders who did not finish retrieved successfully", response.getMessage());
        assertEquals(1, response.getData().size());
        assertEquals("John Doe", response.getData().get(0).getName());
    }

    @Test
    void getRidersNotInRace_Success() {
        List<RiderDto> riders = List.of(new RiderDto(2L, "Jane Doe", null, null));
        when(raceService.getRidersNotInRace(1L)).thenReturn(riders);
        ApiResponse<List<RiderDto>> response = raceController.getRidersNotInRace(1L);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Riders not in the race retrieved successfully", response.getMessage());
        assertEquals(1, response.getData().size());
        assertEquals("Jane Doe", response.getData().get(0).getName());
    }

    @Test
    void getRidersDidNotFinish_RaceNotFound() {
        when(raceService.getRidersDidNotFinish(1L)).thenThrow(new RaceNotFoundException("Race not found with ID: 1"));
        Exception exception = assertThrows(RaceNotFoundException.class, () -> {
            raceController.getRidersDidNotFinish(1L);
        });
        assertEquals("Race not found with ID: 1", exception.getMessage());
    }

    @Test
    void getRidersNotInRace_RaceNotFound() {
        when(raceService.getRidersNotInRace(1L)).thenThrow(new RaceNotFoundException("Race not found with ID: 1"));

        Exception exception = assertThrows(RaceNotFoundException.class, () -> {
            raceController.getRidersNotInRace(1L);
        });
        assertEquals("Race not found with ID: 1", exception.getMessage());
    }
}