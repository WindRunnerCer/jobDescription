package com.windrunner.jobscheduler.common.resource;

import org.springframework.stereotype.Component;

/**
 * Created by Ceren A. @ 6/11/2019
 * While listening ${SPOT}
 */
@Component
public class JobThree implements Job {
    @Override
    public void print() {
        System.out.println("This is job three");
    }

    @Override
    public void run() {

    }
}
