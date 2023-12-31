package step.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import step.learning.email.EmailService;
import step.learning.email.GmailService;
import step.learning.services.HashService;
import step.learning.services.KupinaHashService;
import step.learning.services.db.DbProvider;
import step.learning.services.db.PlanetDbProvider;
import step.learning.services.formparse.FormParseService;
import step.learning.services.formparse.MixedFormParseService;

import step.learning.services.kdf.HashKdfService;
import step.learning.services.kdf.KdfService;

public class ServiceConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind( HashService.class ).to( KupinaHashService.class ) ;
        bind( DbProvider.class ).to( PlanetDbProvider.class ) ;
        bind( KdfService.class ).to( HashKdfService.class ) ;
        bind( FormParseService.class ).to( MixedFormParseService.class ) ;
        bind( EmailService.class).to( GmailService.class  ) ;

        bind( String.class )
                .annotatedWith( Names.named( "DbPrefix" ) )
                .toInstance( "pu121_" ) ;
        bind( String.class )
                .annotatedWith( Names.named( "UploadDir" ) )
                .toInstance( "upload" ) ;
    }
}
