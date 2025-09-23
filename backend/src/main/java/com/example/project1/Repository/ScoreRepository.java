package com.example.project1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project1.Entity.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer>{

        List<ScoreAndName> findAllBy();
}
