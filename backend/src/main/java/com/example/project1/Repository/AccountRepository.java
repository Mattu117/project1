package com.example.project1.Repository;

import org.springframework.stereotype.Repository;

import com.example.project1.Entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{
    
}
