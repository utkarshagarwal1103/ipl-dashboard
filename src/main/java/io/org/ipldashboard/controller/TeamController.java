package io.org.ipldashboard.controller;

import io.org.ipldashboard.model.Team;
import io.org.ipldashboard.repository.MatchRepository;
import io.org.ipldashboard.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    /*public TeamController(TeamRepository teamRepository){
        this.teamRepository=teamRepository;
    }*/

    @GetMapping("team/{teamName}")
    public Team getTeam(@PathVariable String teamName){
        Team team= this.teamRepository.findByTeamName(teamName);
        team.setMatches(this.matchRepository.findLatestMatchesByTeam(teamName,4));
        return team;
    }


}
