package com.windrunner.jobscheduler.resource.repository;

import com.windrunner.jobscheduler.resource.JobSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Ceren A. @ 6/11/2019
 * While listening ${SPOT}
 */
@Repository
public interface JobScheduleRepository extends JpaRepository<JobSchedule, Integer> {

}
