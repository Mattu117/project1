package com.example.project1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="Score")
public class Score{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int score;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    // Contructors
    public Score(){};

    public Score(int score){
        this.score = score;
    }


    // Getters and Setters
    public void setAccount(Account account){
        this.account = account;
    }

    public Account getAccount(){
        return account;
    }

    public int getScore(){
        return score;
    }

    public Integer getId(){
        return id;
    } 
}
