package step.learning.ioc;

import com.google.inject.AbstractModule;
import step.learning.servises.HashService;
import step.learning.servises.KupinaHashService;
import step.learning.servises.db.DbProvider;
import step.learning.servises.db.PlanetDbProvider;

public class ServiceConfig extends AbstractModule {
    @Override
    protected void configure() {
                bind(HashService.class).to(KupinaHashService.class);
                bind(DbProvider.class).to(PlanetDbProvider.class);


    }}

