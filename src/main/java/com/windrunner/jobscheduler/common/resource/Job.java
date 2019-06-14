package com.windrunner.jobscheduler.common.resource;

/**
 * Created by Ceren A. @ 6/11/2019
 * While listening ${SPOT}
 */
public interface Job extends Runnable {
    public void print();
}
