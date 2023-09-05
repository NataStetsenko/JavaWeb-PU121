package step.learning.ioc;

import com.google.inject.AbstractModule;
import step.learning.servises.HashService;
import step.learning.servises.KupinaHashService;

public class ServiceConfig extends AbstractModule {
    @Override
    protected void configure() {
                bind(HashService.class)
                        .to(KupinaHashService.class);

    }}

