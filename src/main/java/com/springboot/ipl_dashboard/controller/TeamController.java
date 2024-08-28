package com.springboot.ipl_dashboard.controller;

import com.springboot.ipl_dashboard.model.Match;
import com.springboot.ipl_dashboard.model.Team;
import com.springboot.ipl_dashboard.repository.MatchRepository;
import com.springboot.ipl_dashboard.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TeamController {

    private final TeamRepository teamRepository;

    private final MatchRepository matchRepository;

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        List<Match> matches = matchRepository.findLatestMatchesByTeam(teamName, 4);
        team.setMatches(matches);
        return team;
    }
}
