package com.springboot.ipl_dashboard.data;

import com.springboot.ipl_dashboard.model.Match;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

@Slf4j
public class MatchDataProcessor implements ItemProcessor <MatchInput, Match> {

    @Override
    public Match process(final MatchInput matchInput) {

        return Match.builder()
                .id(Long.parseLong(matchInput.getId()))
                .season(matchInput.getSeason())
                .city(matchInput.getCity())
                .date(LocalDate.parse(matchInput.getDate()))
                .matchType(matchInput.getMatch_type())
                .playerOfMatch(matchInput.getPlayer_of_match())
                .venue(matchInput.getVenue())
                .team1(matchInput.getTeam1())
                .team2(matchInput.getTeam2())
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
    }
}
