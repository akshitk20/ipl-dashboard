package com.springboot.ipl_dashboard.controller;

import com.springboot.ipl_dashboard.model.Match;
import com.springboot.ipl_dashboard.model.Team;
import com.springboot.ipl_dashboard.repository.MatchRepository;
import com.springboot.ipl_dashboard.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getAllMatchesForTeam(@PathVariable String teamName,
                                            @RequestParam(name = "year") int year) {

        return matchRepository.getByTeamNameAndSeason(teamName,String.valueOf(year));
    }
}
