package com.example.safe.repeaters;

import java.util.HashMap;
import java.util.Map;

public class RepeaterExceptionRegistry  implements IRepeaterExceptionRegistry{
    private final static RepeaterExceptionRegistry instance;
    static{
        instance = new RepeaterExceptionRegistry();
    }

    public static RepeaterExceptionRegistry getInstance(){ return instance; }

    private final Map<String, RegistryEntry> registry = new HashMap<>();

    private RepeaterExceptionRegistry(){}

    @Override
    public void add(Class<? extends Throwable> exceptionClass, int retries, long delay) {
        registry.put(exceptionClass.getName(), new RegistryEntry(exceptionClass.getName(), delay, retries));
    }

    @Override
    public  RegistryEntry EntryFor(Throwable exception) {
        var entry = registry.get(exception.getClass().getName());
        if(entry == null)
            return new RegistryEntry(exception.getClass().getName(), 0, 0);
        else
            return entry;
    }
}
