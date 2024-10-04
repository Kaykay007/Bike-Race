package com.korede.bikerace.repository;

import com.korede.bikerace.model.Race;
import com.korede.bikerace.model.RaceResult;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceResultRepository extends JpaRepository<RaceResult, Long> {
    List<RaceResult> findByRace(Race race);
    List<RaceResult> findTop3ByRaceIdOrderByFinishTimeAsc(Long raceId);
}