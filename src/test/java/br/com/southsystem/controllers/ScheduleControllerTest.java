package br.com.southsystem.controllers;

import br.com.southsystem.dto.input.ScheduleInput;
import br.com.southsystem.service.AssociatedService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AssociatedService associatedService;

    @Test
    public void Test1createSchedule() throws Exception {

        ScheduleInput schedule = new ScheduleInput();
        schedule.setName("Schedule Test");
        schedule.setDescription("Schedule Test Description");

//        this.mockMvc.perform(post("/api/v1/schedule")
//                .content(new Gson().toJson(schedule))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
//    }

    }
}
