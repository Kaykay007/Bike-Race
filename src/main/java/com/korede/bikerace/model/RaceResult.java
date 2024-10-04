package com.korede.bikerace.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "race_result")
@ToString
public class RaceResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Rider rider;
    @ManyToOne
    private Race race;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private boolean didNotFinish;



}