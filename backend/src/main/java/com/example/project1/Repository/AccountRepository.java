package com.example.project1.Repository;

import org.springframework.stereotype.Repository;

import com.example.project1.Entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account,Integer>{

    boolean existsByUsername(String username);

    Account findByUsername(String username);
    
}
