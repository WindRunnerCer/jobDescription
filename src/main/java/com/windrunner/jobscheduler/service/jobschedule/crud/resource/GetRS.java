package com.windrunner.jobscheduler.service.jobschedule.crud.resource;

import lombok.*;

/**
 * Created by Ceren A. @ 6/12/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetRS {
    private Integer id;
    private String className;
    private String result;
}
