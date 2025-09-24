package com.example.project1.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="account")
public class Account {
    // Id for account
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer accountId;

    //  Username and password for login, not secure
    @Column(unique=true)
    private String username;

    private String password;

    // One to many relationship 
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Score> scores = new ArrayList<>();

    // Constructors
    public Account(){}

    public Account(String username, String password){
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    // Account Id
    public Integer getAccountId(){
        return accountId;
    }

    // username
    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    // password
    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    // Scores 
    public List<Score> getScores(){
        return scores;
    }
    
    public void addScore(Score score){
        scores.add(score);
        score.setAccount(this);
    }

    public void removeScore(Score score){
        scores.remove(score);
        score.setAccount(null);
    }

    // Helper
    public int getHighestScore(){
        return scores.stream().mapToInt(Score::getScore).max().orElse(0);
    }
}
