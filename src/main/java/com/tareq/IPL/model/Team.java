package com.tareq.IPL.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String teamName;
    private Long totalMatches;
    private Long totalWins;

    //latest 3-4 matches @Transient donot put in database

    @Transient
    private List<Match> matches;

    public Team(String teamName, Long totalMatches) {
        this.teamName = teamName;
        this.totalMatches = totalMatches;
    }

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(Long totalMatches) {
        this.totalMatches = totalMatches;
    }

    public Long getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(Long totalWins) {
        this.totalWins = totalWins;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", totalMatches=" + totalMatches +
                ", totalWins=" + totalWins +
                '}';
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
