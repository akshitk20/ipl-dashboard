package com.springboot.ipl_dashboard.controller;

import com.springboot.ipl_dashboard.model.Team;
import com.springboot.ipl_dashboard.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamRepository teamRepository;
    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        return teamRepository.findByTeamName(teamName);
    }
}
