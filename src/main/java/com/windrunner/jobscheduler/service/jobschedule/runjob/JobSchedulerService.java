package com.windrunner.jobscheduler.service.jobschedule.runjob;

import com.windrunner.jobscheduler.common.resource.JobOne;
import com.windrunner.jobscheduler.common.resource.JobThree;
import com.windrunner.jobscheduler.common.resource.JobTwo;
import com.windrunner.jobscheduler.resource.repository.JobScheduleRepository;
import com.windrunner.jobscheduler.service.jobschedule.crud.JobScheduleService;
import com.windrunner.jobscheduler.service.jobschedule.crud.resource.ListRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Ceren A. @ 6/12/2019
 * While listening ${SPOT}
 */
@Service
public class JobSchedulerService {
    @Autowired
    JobScheduleService jobScheduleService;

    @Autowired
    JobScheduleRepository jobScheduleRepository;

    @Autowired
    JobOne jobOne;

    @Autowired
    JobTwo jobTwo;

    @Autowired
    JobThree jobThree;

    @Transactional
    @Scheduled(cron = "${job.schedule.cron}")
    public void execute() {
        ListRS jobInThatMin = jobScheduleService.readAll();
        jobInThatMin.getJobSchedules().forEach(job->{
            switch (job.getClassName()) {
                case "1":
                    jobOne.print();
                    break;
                case "2":
                    jobTwo.print();
                    break;
                case "3":
                    jobThree.print();
                    break;
            }
            jobScheduleRepository.delete(job);
        });

    }
}
