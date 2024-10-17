package org.example;

public interface IWork {
    public IWork useExecutionTimeProvider(IProvideNextExecutionTime timeProvider);
    public IWork onError(IHandleErrors errorHandler);
    public IWork onSingleActionCompleted(IComplete onSingleActionCompleted);
    public IWork onCompleted(IComplete onCompleted);
    public void Schedule();
    public void execute();
}
