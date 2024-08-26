package com.springboot.ipl_dashboard.config;

import com.springboot.ipl_dashboard.model.Team;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
@EnableBatchProcessing
public class JobCompletionNotificationListener implements JobExecutionListener {

    private final EntityManager em;

    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            Map<String, Team> teamData = em.createQuery("select m.team1, count(*) from Match m group by m.team1", Object[].class)
                    .getResultList()
                    .stream()
                    .map(e -> new Team((String) e[0], (long) e[1]))
                    .collect(Collectors.toMap(Team::getTeamName, team -> team));

            em.createQuery("select m.team2, count(*) from Match m group by m.team2", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
                    });

            em.createQuery("select m.matchWinner, count(*) from Match m group by m.matchWinner", Object[].class)
                    .getResultList()
                    .forEach(e -> {
                        Team team = teamData.get((String) e[0]);
                        if (team != null)
                            team.setTotalWins(team.getTotalWins() + (long) e[1]);
                    });

            teamData.values().forEach(em::persist);
            em.flush();
            List<Object[]> list = em.createQuery("select t.teamName,t.totalWins,t.totalMatches from Team t", Object[].class)
                    .getResultList()
                    .stream()
                    .toList();
            log.info(String.valueOf(list));
        }
    }
}
