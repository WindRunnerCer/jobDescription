package com.windrunner.jobscheduler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.windrunner.jobscheduler.resource.JobSchedule;
import com.windrunner.jobscheduler.resource.repository.JobScheduleRepository;
import com.windrunner.jobscheduler.service.jobschedule.crud.resource.CreateRQ;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Ceren A. @ 6/12/2019
 * While listening ${SPOT}
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class JobScheduleControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    JobScheduleRepository jobScheduleRepository;

    @Test
    public void create_job_ok() throws Exception {
        JobSchedule jobSchedule = JobSchedule.builder()
                .className("1")
                .id(1)
                .build();
        given(jobScheduleRepository.save(any(JobSchedule.class))).willReturn(jobSchedule);
        mvc.perform( MockMvcRequestBuilders
                .post("/jobSchedule")
                .content(asJsonString(CreateRQ.builder()
                        .className("1")
                        .build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.className").value(1));
    }

    @Test
    public void delete_job_OK() throws Exception {
        JobSchedule jobSchedule = JobSchedule.builder()
                .className("1")
                .id(1)
                .build();
        given(jobScheduleRepository.save(jobSchedule)).willReturn(jobSchedule);
        mvc.perform(MockMvcRequestBuilders.delete("/jobSchedule/{jobId}", 1) )
                .andExpect(status().isOk());
    }

    @Test
    public void update_job_OK() throws Exception {
        JobSchedule jobSchedule = JobSchedule.builder()
                .className("1")
                .id(1)
                .build();
        given(jobScheduleRepository.findById(1)).willReturn(Optional.of(jobSchedule));
        mvc.perform( MockMvcRequestBuilders
                .put("/jobSchedule/{jobId}", 1)
                .content(asJsonString(JobSchedule.builder().className("1").build()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("ok"));

    }

    @Test
    public void list_job_OK() throws Exception {
        JobSchedule jobScheduleOne = JobSchedule.builder()
                .className("1")
                .id(1)
                .build();
        JobSchedule jobScheduleTwo = JobSchedule.builder()
                .className("2")
                .id(2)
                .build();
        List<JobSchedule> jobScheduleList = Stream.of(jobScheduleOne, jobScheduleTwo).collect(Collectors.toList());
        given(jobScheduleRepository.findAll()).willReturn(jobScheduleList);
        mvc.perform(MockMvcRequestBuilders
                .get("/jobSchedule")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobSchedules").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobSchedules").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.jobSchedules[*].className").isNotEmpty());
    }

    @Test
    public void get_job_OK() throws Exception {
        JobSchedule jobSchedule = JobSchedule.builder()
                .className("1")
                .id(1)
                .build();
        given(jobScheduleRepository.findById(1)).willReturn(Optional.ofNullable(jobSchedule));
        mvc.perform(MockMvcRequestBuilders
                .get("/jobSchedule/{jobId}","1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.className").value(1));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
