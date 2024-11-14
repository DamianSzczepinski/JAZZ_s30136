package com.example.safe;


import com.example.safe.repeaters.IRepeater;

public class SafeInvoker implements SafeInvoking{
    private final IRepeater repeater;

    public SafeInvoker(IRepeater repeater) {
        this.repeater = repeater;
    }


    @Override
    public InvokerResult SafeInvoke(NotSafeAction action) {
        boolean success = true;
        Exception exception = null;
        try {
            action.execute();
        } catch (Exception ex) {
            repeater.For(ex).waiting();
            success = false;
            exception = ex;
            while(repeater.shouldRetry()) {
                try {
                    action.execute();
                    success = true;
                    exception = null;

                } catch (Exception ex2) {
                    repeater.For(ex2).waiting().retry();
                    if(!repeater.shouldRetry()) {
                        success = false;
                        exception = ex2;
                    }
                }
            }
        }
        return new InvokerResult(exception, success);
    }
}
