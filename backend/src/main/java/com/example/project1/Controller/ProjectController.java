package com.example.project1.Controller;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.Entity.Account;
import com.example.project1.Repository.ScoreAndName;
import com.example.project1.Service.ProjectService;
import com.example.project1.DTO.UserScore;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectController {
    private final ProjectService projectService;


    // Contructors
    // Spring 4.3+ automaticcaly uses contructor as dependency injection if there is only one contructor
    //@Autowired 
    public ProjectController(ProjectService projectService){
        this.projectService = projectService;
    }

    @PostMapping("/login")
    public boolean login(String username, String password){
        boolean check = projectService.login(username, password);
        return check;
    }

    @PostMapping("/score")
    public int postScore(@RequestBody UserScore userScore){
        Account account = new Account(userScore.getUsername());
        projectService.postScore(userScore.getUserScore(), account);
        return userScore.getUserScore();
    }

    @GetMapping("/highscore/{username}")
    public int getHighestScore(@PathVariable String username) {
        int highscore = projectService.getHightScore(username);
        return highscore;
    }

    @GetMapping("/leaderboard")
    public List<ScoreAndName> getLeaderboard() {
        List<ScoreAndName> leaderboard = projectService.getAllScores();
        return leaderboard;
    }

    @PostMapping("/signup")
    public ResponseEntity postMethodName(@RequestBody Account account) {
        //TODO: process POST request
        projectService.createAccount(account);
        return ResponseEntity.status(200).body(account);
    }
    
    
}
