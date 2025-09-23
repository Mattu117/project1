package com.example.project1.Service;

//import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project1.Entity.Account;
import com.example.project1.Entity.Score;
import com.example.project1.Repository.AccountRepository;
import com.example.project1.Repository.ScoreAndName;
import com.example.project1.Repository.ScoreRepository;

@Service
public class ProjectService {
    private final AccountRepository accountRepository;
    private final ScoreRepository scoreRepository;
    // Spring 4.3+ automaticcaly uses contructor as dependency injection if there is only one contructor
    // @Autowired
    public ProjectService(AccountRepository accountRepository, ScoreRepository scoreRepository){
        this.accountRepository = accountRepository;
        this.scoreRepository = scoreRepository;
    }

    // Add score
    public int postScore(int score, Account account){
        Score createdScore = new Score(score);
        account.addScore(createdScore);
        accountRepository.save(account);
        return 0;
    }

    // Get Highest Score
    public int getHightScore(Account account){
        
        return account.getHighestScore();
    }
    // Get all scores
    public List<ScoreAndName> getAllScores(){
        List<ScoreAndName> scores = scoreRepository.findAllBy();
        return scores;
    }
    // Get all scores from account
}
