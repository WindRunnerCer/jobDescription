package com.windrunner.jobscheduler.service.jobschedule.crud.resource;

import com.windrunner.jobscheduler.resource.JobSchedule;
import lombok.*;

/**
 * Created by Ceren A. @ 6/11/2019
 * While listening ${SPOT}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateRS{
    private JobSchedule jobSchedule;
    private String result;
}
