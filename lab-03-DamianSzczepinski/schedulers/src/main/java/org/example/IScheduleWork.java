package org.example;

import java.util.List;

public interface IScheduleWork {
    public IWork forAction(IRunNotSafeAction action);
    public List<IWork> getJobs();
    public void addJob(IWork job);
}
