package com.windrunner.jobscheduler.service.jobschedule.crud.resource;

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
public class CreateRS  {
    private Integer id;
    private String className;
    private String result;
}
