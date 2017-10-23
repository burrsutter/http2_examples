package http2;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.ext.web.handler.StaticHandler;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(StaticHandler.create());

        router.get("/what").handler(context -> {
            HttpServerRequest request = context.request();
            HttpVersion httpversion = request.version();
            String method = request.rawMethod();
            context.response().end("What " + method + " " + httpversion + " " + new java.util.Date());
        });
        
        HttpServerOptions serverOptions = new HttpServerOptions();
        serverOptions
        .setSsl(true)
        .setKeyCertOptions(new PemKeyCertOptions().setCertPath("tls/server-cert.pem").setKeyPath("tls/server-key.pem"))
        .setUseAlpn(true);

        vertx.createHttpServer(serverOptions)           
            .requestHandler(router::accept)
            .listen(8443, "localhost");
/*
        vertx.createHttpServer(serverOptions)           
            .requestHandler(router::accept)
            .listen(8443,"localhost", ar -> {
                if(ar.succeeded()) {
                    System.out.println("Server Started");
                } else {
                    System.out.println(ar.cause());
                }
            });
            
*/
    }

}
