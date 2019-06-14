package com.windrunner.jobscheduler.service.jobschedule.crud.resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by Ceren A. @ 6/12/2019
 * While listening ${SPOT}
 */
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRQ {
    @Min(value = 1 ,message = "Should be bigger than 1")
    @Max(value = 3 ,message = "Should be smaller than 3")
    private String className;
    @ApiModelProperty(hidden = true) private Integer id;
}
