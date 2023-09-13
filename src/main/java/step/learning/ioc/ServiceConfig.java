package step.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.binder.LinkedBindingBuilder;
import com.google.inject.name.Names;
import step.learning.servises.HashService;
import step.learning.servises.KupinaHashService;
import step.learning.servises.db.DbProvider;
import step.learning.servises.db.PlanetDbProvider;
import step.learning.servises.kdf.HashKdfService;
import step.learning.servises.kdf.KdfService;

public class ServiceConfig extends AbstractModule {
    @Override
    protected void configure() {
                bind(HashService.class).to(KupinaHashService.class);
                bind(DbProvider.class).to(PlanetDbProvider.class);
                bind(KdfService.class).to(HashKdfService.class);
                bind( String.class )
                .annotatedWith( Names.named( "DbPrefix" ) )
                .toInstance( "pu121_" ) ;


    }}

