package org.example;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleClass implements IClassFacade {
    private Class<?> classs;

    public SimpleClass(Class<?> clazz) {
        this.classs = clazz;
    }

    @Override
    public List<IMethodFacade> getDeclaredMethods() {
        return Stream.of(classs.getDeclaredMethods())
                .map(SimpleMethod::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<IMethodFacade> getPublicSetters() {
        return getDeclaredMethods().stream()
                .filter(method -> method.isPublic() && method.isSetter())
                .toList();
    }

    @Override
    public List<IMethodFacade> getPublicGetters() {
        return getDeclaredMethods().stream()
                .filter(method -> method.isPublic() && method.isGetter())
                .toList();
    }

    @Override
    public List<Field> getFieldsForPublicProperties() {
        List<IMethodFacade> getters = getPublicGetters();
        List<IMethodFacade> setters = getPublicSetters();

        return getters.stream()
                .flatMap(getter -> setters.stream()
                        .filter(setter -> getter.getFieldName().equals(setter.getFieldName()))
                        .map(setter -> {
                            String fieldName = getter.getFieldName();
                            try {
                                return getter.GetUnderlyingMethod().getDeclaringClass().getDeclaredField(fieldName);
                            } catch (NoSuchFieldException e) {
                                try {
                                    return getter.GetUnderlyingMethod().getDeclaringClass()
                                            .getDeclaredField("is" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1));
                                } catch (NoSuchFieldException ex) {
                                    System.out.println("brak danego pola ");
                                    return null;
                                }
                            }
                        })
                        .filter(field -> field != null))
                .collect(Collectors.toList());
    }

    @Override
    public List<IMethodFacade> getPublicDeclaredMethods() {
        return getDeclaredMethods().stream()
                .filter(IMethodFacade::isPublic)
                .collect(Collectors.toList());
    }
}
