package http;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.marshalling.Marshaller;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.RequestEntity;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import static akka.http.javadsl.server.Directives.*;
import static akka.http.javadsl.server.PathMatchers.segment;

@Singleton
public class HTTPListener {
    private static final Marshaller<Object, RequestEntity> MARSHALLER = Jackson.marshaller();

    @Inject
    public HTTPListener(ActorSystem system, ActorMaterializer materializer) {
        final String host = "localhost";
        final int port = 8080;
        final Flow<HttpRequest, HttpResponse, NotUsed> flow = createRoute().flow(system, materializer);

        Http.get(system).bindAndHandle(flow, ConnectHttp.toHost(host, port), materializer);
    }

    private Route createRoute() {
        
        return route(

            pathPrefix(segment("sample"), () ->
                get(() -> completeOK("hello world", MARSHALLER))
            )
        );
    }
}
