package com.tareq.IPL.controller;

import com.tareq.IPL.model.Match;
import com.tareq.IPL.model.Team;
import com.tareq.IPL.repository.MatchRepository;
import com.tareq.IPL.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
/*Rest Controller*/

@RestController
@CrossOrigin
public class TeamController {
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team")
    public Iterable<Team> getAllTeam(){
        return this.teamRepository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team =  teamRepository.findByTeamName(teamName);
        team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName,4));
        return team;
    }
    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){

        LocalDate startDate = LocalDate.of(year,1,1);
        LocalDate endDate = LocalDate.of(year+1,1,1);
        return this.matchRepository.getMatchesByTeamBetweenDates(teamName,startDate,endDate);
    }

}
