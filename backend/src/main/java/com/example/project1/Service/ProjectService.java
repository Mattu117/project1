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
    public int getHightScore(String username){
        if(!checkUsernameExists(username)){
            return 0;
        }
        Account account = getAccountByUsername(username);
        return account.getHighestScore();
    }

    // Get all scores
    public List<ScoreAndName> getAllScores(){
        List<ScoreAndName> scores = scoreRepository.findAllBy();
        return scores;
    }

    // Check if username exists
    public boolean checkUsernameExists(String username){
        boolean exists = accountRepository.existsByUsername(username);
        return exists;
    }

    // Get Account by login
    public Account getAccountByUsername(String username){
        Account account = accountRepository.findByUsername(username);
        return account;
    }

    // Login

    public boolean login(String username, String password){
        if(!checkUsernameExists(username)){
            return false;
        }
        Account account = getAccountByUsername(username);
        if(account.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public boolean createAccount(Account account){
        if(checkUsernameExists(account.getUsername())){
            return false;
        }
        accountRepository.save(account);
        return true;
    }
    // Get all scores from account
   // public List<Score> getAccountScores(){
       // List<Score> scores = scoreRepository.findAll();
      //  return scores;
   // }
}
