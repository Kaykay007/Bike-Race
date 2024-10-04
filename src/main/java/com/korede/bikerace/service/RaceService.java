package com.korede.bikerace.service;



import com.korede.bikerace.exception.RaceNotFoundException;
import com.korede.bikerace.model.*;
import com.korede.bikerace.repository.RaceRepository;
import com.korede.bikerace.repository.RaceResultRepository;
import com.korede.bikerace.repository.RiderRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RaceService {
    private final RaceRepository raceRepository;
    private final RaceResultRepository resultRepository;
    private final RiderRepository riderRepository;
    private final RaceResultRepository raceResultRepository;

    @Autowired
    public RaceService(RaceRepository raceRepository, RaceResultRepository resultRepository,
                       RiderRepository riderRepository, RaceResultRepository raceResultRepository) {
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
        this.riderRepository = riderRepository;
        this.raceResultRepository = raceResultRepository;
    }

    public List<RiderDto> getFastestRidersForRace(Long raceId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new RaceNotFoundException("Race not found with ID: " + raceId));

        List<RaceResult> results = raceResultRepository.findTop3ByRaceIdOrderByFinishTimeAsc(raceId);

        if (results.isEmpty()) {
            return Collections.emptyList();
        }

        return results.stream()
                .filter(result -> result.getFinishTime() != null)
                .map(result -> new RiderDto(
                        result.getRider().getId(),
                        result.getRider().getName(),
                        result.getStartTime(),
                        result.getFinishTime()))
                .collect(Collectors.toList());
    }


    public List<RiderDto> getRidersDidNotFinish(Long raceId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new RaceNotFoundException("Race not found with ID: " + raceId));

        List<RaceResult> results = resultRepository.findByRace(race);
        return results.stream()
                .filter(RaceResult::isDidNotFinish)
                .map(result -> new RiderDto(
                        result.getRider().getId(),
                        result.getRider().getName(),
                        result.getStartTime(),
                        result.getFinishTime()
                ))
                .collect(Collectors.toList());
    }

    public List<RiderDto> getRidersNotInRace(Long raceId) {
        Race race = raceRepository.findById(raceId)
                .orElseThrow(() -> new RaceNotFoundException("Race not found with ID: " + raceId));

        return riderRepository.findByRacesNotContains(race).stream()
                .map(rider -> new RiderDto(
                        rider.getId(),
                        rider.getName(),
                        null,
                        null))
                .collect(Collectors.toList());
    }


}