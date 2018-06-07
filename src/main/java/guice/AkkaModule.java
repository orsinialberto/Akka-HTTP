package guice;

import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provider;

import javax.inject.Singleton;

public class AkkaModule implements Module {
	
	private static final String APP_NAME = "sample-akka-http";

    @Override
    public void configure(Binder binder) {
    	
    	binder
    		.bind(ActorSystem.class)
            .toInstance(ActorSystem.create(AkkaModule.APP_NAME));  // ConfigFactory.load()

    	binder
    		.bind(ActorMaterializer.class)
            .toProvider(new Provider<ActorMaterializer>() {
                @Inject
                private ActorSystem system;

                @Override
                public ActorMaterializer get() {
                    return ActorMaterializer.create(system);
                }
            })
            .in(Singleton.class);
    }   	
}