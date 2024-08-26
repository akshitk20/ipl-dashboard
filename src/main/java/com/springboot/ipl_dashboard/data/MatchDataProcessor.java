package com.springboot.ipl_dashboard.data;

import com.springboot.ipl_dashboard.model.Match;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

@Slf4j
public class MatchDataProcessor implements ItemProcessor <MatchInput, Match> {

    @Override
    public Match process(final MatchInput matchInput) {
        log.info("starting MatchDataProcessor step");
        Match match = Match.builder()
                .id(Long.parseLong(matchInput.getId()))
                .season(matchInput.getSeason())
                .city(matchInput.getCity())
                .date(LocalDate.parse(matchInput.getDate()))
                .matchType(matchInput.getMatch_type())
                .playerOfMatch(matchInput.getPlayer_of_match())
                .venue(matchInput.getVenue())
                .tossWinner(matchInput.getToss_winner())
                .tossDecision(matchInput.getToss_decision())
                .matchWinner(matchInput.getWinner())
                .result(matchInput.getResult())
                .resultMargin(matchInput.getResult_margin())
                .targetRuns(matchInput.getTarget_runs())
                .targetOvers(matchInput.getTarget_overs())
                .superOver(matchInput.getSuper_over())
                .umpire1(matchInput.getUmpire1())
                .umpire2(matchInput.getUmpire2())
                .build();

        // set team1 and team2 depending on innings order
        String firstInningsTeam, secondInningsTeam;
        if ("bat".equals(matchInput.getToss_decision())) {
            firstInningsTeam = matchInput.getToss_winner();
            secondInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2() : matchInput.getTeam1();
        } else {
            secondInningsTeam = matchInput.getToss_winner();
            firstInningsTeam = matchInput.getToss_winner().equals(matchInput.getTeam1())
                    ? matchInput.getTeam2() : matchInput.getTeam1();
        }
        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningsTeam);

        log.info("completed MatchDataProcessor step");
        return match;
    }
}
