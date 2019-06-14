package com.windrunner.jobscheduler.service.jobschedule.runjob.resource;

import lombok.*;

/**
 * Created by Ceren A. @ 6/12/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendScheduleResultRS {
    private String result;
}
