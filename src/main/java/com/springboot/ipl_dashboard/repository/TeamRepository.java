package com.springboot.ipl_dashboard.repository;

import com.springboot.ipl_dashboard.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team,Long> {
    Team findByTeamName(String teamName);
}
