package com.korede.bikerace.controller;

import com.korede.bikerace.model.ApiResponse;
import com.korede.bikerace.model.RiderDto;
import com.korede.bikerace.service.RaceService;
import com.korede.bikerace.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/races")
@Tag(name = "Racing Sport", description = "Operations related to Mountain Bike Race")
public class RaceController {
    private final RaceService raceService;
    private final WeatherService weatherService;


    @Autowired
    public RaceController(RaceService raceService, WeatherService weatherService) {
        this.raceService = raceService;
        this.weatherService = weatherService;
    }

    @GetMapping("/{raceId}/fastest-riders")
    @Operation(summary = "Get Fastest Riders",
            description = "Retrieves the fastest riders for a given race ID.")
    public ResponseEntity<ApiResponse<List<RiderDto>>> getFastestRiders(@PathVariable Long raceId) {
        List<RiderDto> fastestRiders = raceService.getFastestRidersForRace(raceId);
        ApiResponse<List<RiderDto>> response = new ApiResponse<>(HttpStatus.OK, "Fastest riders retrieved successfully", fastestRiders);
        return ResponseEntity.ok(response);
    }



    @GetMapping("/{raceId}/did-not-finish")
    @Operation(summary = "Get Riders Who Did Not Finish",
            description = "Retrieves a list of riders who did not finish the race for the given race ID.")
    public ApiResponse<List<RiderDto>> getRidersDidNotFinish(@PathVariable Long raceId) {
        List<RiderDto> riders = raceService.getRidersDidNotFinish(raceId);
        return new ApiResponse<>(HttpStatus.OK, "Riders who did not finish retrieved successfully", riders);
    }

    @GetMapping("/{raceId}/not-in-race")
    @Operation(summary = "Get Riders Not in Race",
            description = "Retrieves a list of riders who are not in the race for the given race ID.")
    public ApiResponse<List<RiderDto>> getRidersNotInRace(@PathVariable Long raceId) {
        List<RiderDto> riders = raceService.getRidersNotInRace(raceId);
        return new ApiResponse<>(HttpStatus.OK, "Riders not in the race retrieved successfully", riders);
    }


    @GetMapping("/current/{cityName}")
    @Operation(summary = "Get Current Weather",
            description = "Retrieves current weather information for a specified city.")
    public ResponseEntity<String> getCurrentWeather(@PathVariable String cityName) {
        String weatherData = weatherService.getCurrentWeather(cityName);
        return ResponseEntity.ok(weatherData);
    }

    @GetMapping("/forecast/{cityName}")
    @Operation(summary = "Get Weather Forecast",
            description = "Retrieves the weather forecast for a specified city.")
    public ResponseEntity<String> getForecastWeather(@PathVariable String cityName) {
        String forecastData = weatherService.getForecastWeather(cityName);
        return ResponseEntity.ok(forecastData);
    }


}