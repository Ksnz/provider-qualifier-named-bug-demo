package example.micronaut.service;

import io.micronaut.context.BeanProvider;
import jakarta.inject.Provider;


public class BarService {
    private final Provider<FooService> fooServiceProvider;
    private final BeanProvider<FooService> fooServiceBeanProvider;

    public BarService(Provider<FooService> fooServiceProvider, BeanProvider<FooService> fooServiceBeanProvider) {
        this.fooServiceProvider = fooServiceProvider;
        this.fooServiceBeanProvider = fooServiceBeanProvider;
    }
}
