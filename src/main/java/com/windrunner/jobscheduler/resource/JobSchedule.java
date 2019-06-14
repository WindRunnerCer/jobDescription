package com.windrunner.jobscheduler.resource;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Ceren A. @ 6/11/2019
 * While listening ${SPOT}
 */
@Entity
@Table(name = "job_schedule")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String className;
}
