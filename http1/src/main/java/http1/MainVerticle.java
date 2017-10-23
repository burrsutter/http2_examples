package http1;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpVersion;
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
        vertx.createHttpServer()
            .requestHandler(router::accept)
            .listen(8080);
    }

}
