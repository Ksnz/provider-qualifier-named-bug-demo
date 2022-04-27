package example.micronaut;

import example.micronaut.qualifier.Foo;
import example.micronaut.service.BarService;
import example.micronaut.service.FooService;
import io.micronaut.context.BeanProvider;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Prototype;
import jakarta.inject.Named;
import jakarta.inject.Provider;

@Factory
public class BeanFactory {

    @Bean
    @Prototype
    @Foo
    @Named("foo")
    public FooService fooService() {
        return new FooService();
    }

    /**
     * Not working
     */
    @Bean
    @Context
    public BarService barService1(@Foo @Named("foo") Provider<FooService> fooServiceProvider,
                                  @Foo @Named("foo") BeanProvider<FooService> fooServiceBeanProvider) {
        return new BarService(fooServiceProvider, fooServiceBeanProvider);
    }
    
    /**
     * Working well
     */
    @Bean
    @Context
    public BarService barService2(@Named("foo") Provider<FooService> fooServiceProvider,
                                  @Named("foo") BeanProvider<FooService> fooServiceBeanProvider) {
        return new BarService(fooServiceProvider, fooServiceBeanProvider);
    }

    /**
     * Working well
     */
    @Bean
    @Context
    public BarService barService3(@Foo Provider<FooService> fooServiceProvider,
                                  @Foo BeanProvider<FooService> fooServiceBeanProvider) {
        return new BarService(fooServiceProvider, fooServiceBeanProvider);
    }
}
