package com.tareq.IPL.data;

import com.tareq.IPL.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
       Match match = new Match();
       // values that are same
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(match.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(match.getPlayerOfMatch());
        match.setVenue(matchInput.getVenue());

        //Set team 1 and team 2 depending on the innings order
        String firstInningsTeam,secondInningsTeam;

       return match;
    }

}