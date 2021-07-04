package io.org.ipldashboard.data;

import io.org.ipldashboard.model.Match;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.util.Optional;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {


    @Override
    public Match process(final MatchInput matchInput) throws Exception {

        Match match=new Match();

        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(matchInput.getVenue());

        String firstInningsTeam, secondInningteam;
        if("bat".equals(matchInput.getToss_decision())){
            firstInningsTeam=matchInput.getToss_winner();
            secondInningteam=Optional.ofNullable(matchInput.getToss_winner()).
                    map(s1 -> s1.equals(matchInput.getTeam1())).orElse(false)
                    ?matchInput.getTeam2():matchInput.getTeam1();
        }
        else{
            secondInningteam=matchInput.getToss_winner();
            firstInningsTeam=Optional.ofNullable(matchInput.getToss_winner()).
                    map(s1 -> s1.equals(matchInput.getTeam2())).orElse(false)
                    ?matchInput.getTeam1():matchInput.getTeam2();
        }


        match.setTeam1(firstInningsTeam);
        match.setTeam2(secondInningteam);

        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());


        return match;
    }
}
