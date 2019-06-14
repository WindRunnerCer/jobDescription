package com.windrunner.jobscheduler.service.runjob;

import com.windrunner.jobscheduler.common.resource.JobOne;
import com.windrunner.jobscheduler.common.resource.JobThree;
import com.windrunner.jobscheduler.common.resource.JobTwo;
import com.windrunner.jobscheduler.resource.JobSchedule;
import com.windrunner.jobscheduler.resource.repository.JobScheduleRepository;
import com.windrunner.jobscheduler.service.jobschedule.crud.JobScheduleService;
import com.windrunner.jobscheduler.service.jobschedule.crud.resource.ListRS;
import com.windrunner.jobscheduler.service.jobschedule.runjob.JobSchedulerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
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
public class JobSchedulerServiceTest {
    @InjectMocks
    JobSchedulerService jobSchedulerService;

    @Spy
    JobScheduleService jobScheduleService;
    @Spy
    JobOne jobOne;
    @Spy
    JobTwo jobTwo;
    @Spy
    JobThree jobThree;

    @Spy
    JobScheduleRepository jobScheduleRepository;

    @Test
    public void does_job_run(){
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
        ListRS listRs = ListRS.builder()
                .jobSchedules(jobScheduleList)
                .build();

        doReturn(listRs).when(jobScheduleService).readAll();
        doNothing().when(jobOne).print();
        doNothing().when(jobTwo).print();
        doNothing().when(jobThree).print();

        jobSchedulerService.execute();

        assertEquals(3, jobScheduleList.size());
        verify(jobScheduleRepository, times(0)).findAll();
        verify(jobScheduleRepository, times(1)).delete(jobScheduleOne);
        verify(jobScheduleRepository, times(1)).delete(jobScheduleTwo);
        verify(jobScheduleRepository, times(1)).delete(jobScheduleThree);
    }
}

