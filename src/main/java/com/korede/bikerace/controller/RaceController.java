package com.korede.bikerace.controller;

import com.korede.bikerace.model.ApiResponse;
import com.korede.bikerace.model.RiderDto;
import com.korede.bikerace.service.RaceService;
import com.korede.bikerace.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/races")
public class RaceController {
    private final RaceService raceService;
    private final WeatherService weatherService;


    @Autowired
    public RaceController(RaceService raceService, WeatherService weatherService) {
        this.raceService = raceService;
        this.weatherService = weatherService;
    }

    @GetMapping("/{id}/fastest-riders")
    public ResponseEntity<ApiResponse<List<RiderDto>>> getFastestRiders(@PathVariable Long id) {
        List<RiderDto> fastestRiders = raceService.getFastestRidersForRace(id);
        ApiResponse<List<RiderDto>> response = new ApiResponse<>(HttpStatus.OK, "Fastest riders retrieved successfully", fastestRiders);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/{raceId}/did-not-finish")
    public ApiResponse<List<RiderDto>> getRidersDidNotFinish(@PathVariable Long raceId) {
        List<RiderDto> riders = raceService.getRidersDidNotFinish(raceId);
        return new ApiResponse<>(HttpStatus.OK, "Riders who did not finish retrieved successfully", riders);
    }

    @GetMapping("/{raceId}/not-in-race")
    public ApiResponse<List<RiderDto>> getRidersNotInRace(@PathVariable Long raceId) {
        List<RiderDto> riders = raceService.getRidersNotInRace(raceId);
        return new ApiResponse<>(HttpStatus.OK, "Riders not in the race retrieved successfully", riders);
    }


    @GetMapping("/current/{cityName}")
    public ResponseEntity<String> getCurrentWeather(@PathVariable String cityName) {
        String weatherData = weatherService.getCurrentWeather(cityName);
        return ResponseEntity.ok(weatherData);
    }

    @GetMapping("/forecast/{cityName}")
    public ResponseEntity<String> getForecastWeather(@PathVariable String cityName) {
        String forecastData = weatherService.getForecastWeather(cityName);
        return ResponseEntity.ok(forecastData);
    }


}