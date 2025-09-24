package com.example.project1;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.project1.Entity.Account;
import com.example.project1.Repository.AccountRepository;
import com.example.project1.Repository.ScoreRepository;
import com.example.project1.Service.ProjectService;

@SpringBootTest
@ActiveProfiles("test")
public class UsernameCheckTest {
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private ScoreRepository scoreRepository;

    @Test
    public void testCheckUsername(){
        Account account = new Account("Test-User", "test-pass");
        accountRepository.save(account);

        ProjectService projectService = new ProjectService(accountRepository, scoreRepository);

        boolean check = projectService.checkUsernameExists("Test-User");
        assertThat(check).isEqualTo(true);
    }

}
