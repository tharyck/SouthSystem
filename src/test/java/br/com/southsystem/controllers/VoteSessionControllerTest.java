package br.com.southsystem.controllers;

import br.com.southsystem.repository.VotingSessionRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VoteSessionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Test
    public void Test1StartVoteSession() throws Exception{

    }

    @Test
    public void Test2IndexVoteSession() throws Exception{

    }

    @Test
    public void Test3ShowVoteSession() throws Exception{

    }
}
