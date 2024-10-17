package org.example;

public class Job implements IWork{
    private IRunNotSafeAction action;
    private IProvideNextExecutionTime nextTimeProvider = ()->null;
    private IHandleErrors handleExcepions = ex->{};
    private IComplete singleActionCompleted = ()->{};
    private IComplete completed = ()->{};
    private IScheduleWork scheduler;
    @Override
    public IWork useExecutionTimeProvider(IProvideNextExecutionTime timeProvider) {
        this.nextTimeProvider = timeProvider;
        return this;
    }

    public Job(IRunNotSafeAction action, IScheduleWork scheduler) {
        this.action = action;
        this.scheduler = scheduler;
    }

    @Override
    public IWork onError(IHandleErrors errorHandler) {
        this.handleExcepions = errorHandler;
        return this;
    }

    @Override
    public IWork onSingleActionCompleted(IComplete onSingleActionCompleted) {
        this.singleActionCompleted = onSingleActionCompleted;
        return this;
    }

    @Override
    public IWork onCompleted(IComplete onCompleted) {
        completed = onCompleted;
        return this;
    }

    @Override
    public void Schedule() {
        scheduler.addJob(this);
    }

    @Override
    public void execute() {
        try {
            action.executeNotSafeAction();
            singleActionCompleted.complete();
            if(nextTimeProvider.provideNextExecutionTime() == null) {
                completed.complete();
            }
        } catch (Exception e) {
            handleExcepions.handle(e);
        }
    }
}
