package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SimpleMethod implements IMethodFacade{
    private Method method;

    public SimpleMethod(Method method) {
        this.method = method;
    }


    @Override
    public boolean isPublic() {
        return Modifier.isPublic(method.getModifiers());
    }

    @Override
    public boolean paramsCountEquals(int n) {
        return method.getParameterCount() == n;
    }

    @Override
    public boolean startsWith(String prefix) {
        return method.getName().startsWith(prefix);
    }

    @Override
    public boolean isVoid() {
        return method.getReturnType().equals(Void.TYPE);
    }

    @Override
    public boolean isSetter() {
        return (Modifier.isPublic(method.getModifiers())) && (method.getParameterCount() == 1) && (startsWith("set")) && isVoid();
    }

    @Override
    public boolean isGetter() {
        return (Modifier.isPublic(method.getModifiers())) && (method.getParameterCount() == 0) && (startsWith("get") || startsWith("is")) && !isVoid();
    }

    @Override
    public String getFieldName() {
        return method.getName().replaceAll("^set|^get|^is", "").toLowerCase();
    }

    @Override
    public Method GetUnderlyingMethod() {
        return this.method;
    }
}
