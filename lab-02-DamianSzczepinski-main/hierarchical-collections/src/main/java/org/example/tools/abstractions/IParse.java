package org.example.model.abstraction;

@FunctionalInterface
public interface IParse<T> {
    T parse(String data);
}
