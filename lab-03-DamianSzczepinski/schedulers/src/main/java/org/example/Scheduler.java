package org.example;

import java.util.ArrayList;
import java.util.List;

public class Scheduler implements IScheduleWork {
    private List<IWork> jobs = new ArrayList<>();
    private static Scheduler instance;

    private Scheduler() {}

    static {
        instance = new Scheduler();
    }

    public static Scheduler getInstance(){return instance;}

    @Override
    public IWork forAction(IRunNotSafeAction action) {
        return new Job(action, instance);
    }

    @Override
    public List<IWork> getJobs() {
        return jobs;
    }

    @Override
    public void addJob(IWork job) {
        jobs.add(job);
    }
}


