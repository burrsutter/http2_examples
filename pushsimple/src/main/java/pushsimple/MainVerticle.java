package pushsimple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.net.OpenSSLEngineOptions;
import io.vertx.core.net.PemKeyCertOptions;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() throws Exception {
    Router router = Router.router(vertx);
    router.get("/").handler(context -> {
/*
        HttpServerRequest request = context.request();
        HttpVersion httpversion = request.version();
        String method = request.rawMethod();
        context.response().end("Index " + method + " " + httpversion + " " + new java.util.Date());
*/
        context.response().push(HttpMethod.GET, "/script.js", ar -> {
            if (ar.succeeded()) {
              System.out.println("sending push");
              HttpServerResponse pushedResp = ar.result();
              pushedResp.sendFile("script.js");
            } else {
              System.out.println("Push failed " + ar.cause());
            }
        });

        context.response().sendFile("index.html");

    }); // get("/")
    
    router.get("/script.js").handler(context -> {
        System.out.println("Individual Request for script.js");
        context.response().sendFile("script.js");        
    }); // get("script.js")

    HttpServerOptions serverOptions = new HttpServerOptions();
    serverOptions
    .setSsl(true)
    .setKeyCertOptions(new PemKeyCertOptions().setCertPath("tls/server-cert.pem").setKeyPath("tls/server-key.pem"))
    .setUseAlpn(true);

    vertx.createHttpServer(serverOptions)           
        .requestHandler(router::accept)
        .listen(8443, "localhost");
  }
}
