package com.springboot.ipl_dashboard.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String teamName;
    private long totalWins;
    private long totalMatches;

    public Team(String teamName,  long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }
}
