package com.windrunner.jobscheduler.service.crud;

import com.windrunner.jobscheduler.resource.JobSchedule;
import com.windrunner.jobscheduler.resource.repository.JobScheduleRepository;
import com.windrunner.jobscheduler.service.jobschedule.crud.JobScheduleService;
import com.windrunner.jobscheduler.service.jobschedule.crud.resource.GetRS;
import com.windrunner.jobscheduler.service.jobschedule.crud.resource.UpdateRS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Ceren A. @ 6/13/2019
 * While listening ${SPOT}
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class JobScheduleServiceTest {
    @InjectMocks
    JobScheduleService jobScheduleService;

    @Mock
    JobScheduleRepository jobScheduleRepository;

    @Test
    public void get_all_job() {
        JobSchedule jobScheduleOne = JobSchedule.builder()
                .className("1")
                .id(1)
                .build();
        JobSchedule jobScheduleTwo = JobSchedule.builder()
                .className("2")
                .id(2)
                .build();
        JobSchedule jobScheduleThree = JobSchedule.builder()
                .className("3")
                .id(3)
                .build();

        List<JobSchedule> jobScheduleList = Stream.of(jobScheduleOne, jobScheduleTwo,jobScheduleThree).collect(Collectors.toList());
        when(jobScheduleRepository.findAll()).thenReturn(jobScheduleList);

        jobScheduleService.readAll();

        assertEquals(3, jobScheduleList.size());
        verify(jobScheduleRepository, times(1)).findAll();
    }

    @Test
    public void get_job_by_id() {
        when(jobScheduleRepository.findById(1)).thenReturn(Optional.ofNullable(JobSchedule.builder()
                .className("1")
                .id(1)
                .build()));

        GetRS readResponse = jobScheduleService.read(1);

        assertEquals("1", readResponse.getClassName());
        assertEquals(Integer.valueOf(1), readResponse.getId());
    }

   @Test
   public void update_job(){
       JobSchedule jobSchedule = JobSchedule.builder()
               .id(1)
               .className("1")
               .build();
       JobSchedule jobUpdatedSchedule = JobSchedule.builder()
               .className("2")
               .id(1)
               .build();
       when(jobScheduleRepository.findById(1)).thenReturn(Optional.ofNullable(jobSchedule));
       when(jobScheduleRepository.save(jobUpdatedSchedule)).thenReturn(jobUpdatedSchedule);
       UpdateRS updateResponse = jobScheduleService.update(jobUpdatedSchedule);
       assertEquals("2", updateResponse.getJobSchedule().getClassName());
       assertEquals(Integer.valueOf(1), updateResponse.getJobSchedule().getId());
   }

    @Test
    public void delete_job(){
        JobSchedule jobSchedule = JobSchedule.builder()
                .id(1)
                .className("1")
                .build();
        jobScheduleService.delete(jobSchedule);
        verify(jobScheduleRepository, times(1)).delete(jobSchedule);
    }
}
