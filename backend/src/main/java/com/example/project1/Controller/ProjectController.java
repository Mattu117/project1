package com.example.project1.Controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.project1.Entity.Account;
import com.example.project1.Service.ProjectService;


@RestController
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
    public int postScore(int score, Account account){
        projectService.postScore(score, account);
        return score;
    }

    @GetMapping("/highscore/{username}")
    public int getHighestScore(@PathVariable String username) {
        int highscore = projectService.getHightScore(username);
        return highscore;
    }

    @PostMapping("/signup")
    public ResponseEntity postMethodName(@RequestBody Account account) {
        //TODO: process POST request
        projectService.createAccount(account);
        return ResponseEntity.status(200).body(account);
    }
    
    
}
