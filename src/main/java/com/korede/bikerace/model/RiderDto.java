package com.korede.bikerace.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@AllArgsConstructor
@Data
public class RiderDto {
    private Long id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;

}