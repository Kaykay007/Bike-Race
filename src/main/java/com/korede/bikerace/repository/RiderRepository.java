package com.korede.bikerace.repository;

import com.korede.bikerace.model.Race;
import com.korede.bikerace.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    List<Rider> findByRacesNotContains(Race race);
}