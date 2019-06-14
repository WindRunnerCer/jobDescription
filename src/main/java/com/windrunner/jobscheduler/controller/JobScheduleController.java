package com.windrunner.jobscheduler.controller;

import com.windrunner.jobscheduler.resource.JobSchedule;
import com.windrunner.jobscheduler.service.jobschedule.crud.*;
import com.windrunner.jobscheduler.service.jobschedule.crud.resource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Ceren A. @ 6/11/2019
 * While listening ${SPOT}
 */
@RestController
@RequestMapping("/jobSchedule")
public class JobScheduleController {
    @Autowired
    JobScheduleService jobScheduleService;

    @PostMapping
    public CreateRS createJob(@RequestBody @Valid CreateRQ jobRequest){
        return jobScheduleService.create(jobRequest);
    }

    @DeleteMapping("/{jobId}")
    public DeleteRS deleteClient(@PathVariable Integer jobId){
        JobSchedule jobSchedule = JobSchedule.builder()
                .id(jobId)
                .build();
        return jobScheduleService.delete(jobSchedule);
    }

    @PutMapping("/{jobId}")
    public UpdateRS updateJob(@RequestBody String className,@PathVariable Integer jobId) {
        JobSchedule updateRQ = JobSchedule.builder()
                .className(className)
                .id(jobId)
                .build();
        return jobScheduleService.update(updateRQ);
    }

    @GetMapping
    public ListRS listJobs(){
        return jobScheduleService.readAll();
    }

    @GetMapping("/{jobId}")
    public GetRS getJob(@PathVariable Integer jobId){
        return jobScheduleService.read(jobId);
    }
}
