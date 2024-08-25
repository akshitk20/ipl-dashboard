package com.springboot.ipl_dashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Match {
    @Id
    private long id;
    private String season;
    private String city;
    private LocalDate date;
    private String matchType;
    private String playerOfMatch;
    private String venue;
    private String team1;
    private String team2;
    private String tossWinner;
    private String tossDecision;
    private String matchWinner;
    private String result;
    private String resultMargin;
    private String targetRuns;
    private String targetOvers;
    private String superOver;
    private String umpire1;
    private String umpire2;
}
