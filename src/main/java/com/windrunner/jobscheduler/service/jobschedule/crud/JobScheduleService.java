package com.windrunner.jobscheduler.service.jobschedule.crud;

import com.windrunner.jobscheduler.resource.JobSchedule;
import com.windrunner.jobscheduler.resource.repository.JobScheduleRepository;
import com.windrunner.jobscheduler.service.jobschedule.crud.resource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Ceren A. @ 6/11/2019
 * While listening ${SPOT}
 */
@Service
public class JobScheduleService  {
    @Autowired
    JobScheduleRepository jobScheduleRepository;

    public CreateRS create(CreateRQ createRQ){
        JobSchedule jobSchedule = JobSchedule.builder()
                .className(createRQ.getClassName())
                .build();
        jobSchedule = jobScheduleRepository.save(jobSchedule);
        return CreateRS.builder()
                .id(jobSchedule.getId())
                .className(jobSchedule.getClassName())
                .result("ok")
                .build();
    }

    public GetRS read(Integer getRQ){
        Optional<JobSchedule> jobSchedule =  jobScheduleRepository.findById(getRQ);
        return GetRS.builder()
                .className(jobSchedule.isPresent()?jobSchedule.get().getClassName():null)
                .id(jobSchedule.isPresent()?jobSchedule.get().getId():null)
                .result(jobSchedule.isPresent()?"ok":"Element not found")
                .build();
    }

    public UpdateRS update(JobSchedule updateRQ){
        if(jobScheduleRepository.findById(updateRQ.getId()).isPresent()) {
            return UpdateRS.builder()
                    .jobSchedule(jobScheduleRepository.save(updateRQ))
                    .result("ok")
                    .build();
        }else return UpdateRS.builder().result("Element does not exist").build();
    }

    public DeleteRS delete(JobSchedule deleteRQ){
        jobScheduleRepository.delete(deleteRQ);
        return DeleteRS.builder()
                .result("ok")
                .build();
    }

    public ListRS readAll(){
        ListRS listRS = ListRS.builder()
                .jobSchedules(jobScheduleRepository.findAll())
                .result("ok")
                .build();
        return listRS;
    }
}

