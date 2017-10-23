package push;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.PemKeyCertOptions;


public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        Router router = Router.router(vertx);
        
        router.get("/").handler(context -> {
            HttpServerRequest request = context.request();
            HttpVersion httpversion = request.version();
            String method = request.rawMethod();
            HttpServerResponse response = context.response();
            response.push(HttpMethod.GET, "/img/tiger-2791980_640 [www.imagesplitter.net]-0-0.jpeg", ar -> {
                if (ar.succeeded()) {
                    System.out.println("sending push 1");
                    HttpServerResponse pushedResp = ar.result();
                    pushedResp.sendFile("tiger-2791980_640 [www.imagesplitter.net]-0-0.jpeg");
                } else {
                    System.out.println("No Push");
                  }
            });  

            response.push(HttpMethod.GET, "/img/tiger-2791980_640 [www.imagesplitter.net]-0-1.jpeg", ar -> {
                if (ar.succeeded()) {
                    System.out.println("sending push 2");
                    HttpServerResponse pushedResp = ar.result();
                    pushedResp.sendFile("tiger-2791980_640 [www.imagesplitter.net]-0-1.jpeg");
                } else {
                    System.out.println("No Push");
                  }
            });  

            response.push(HttpMethod.GET, "/img/tiger-2791980_640 [www.imagesplitter.net]-0-2.jpeg", ar -> {
                if (ar.succeeded()) {
                    System.out.println("sending push 3");
                    HttpServerResponse pushedResp = ar.result();
                    pushedResp.sendFile("tiger-2791980_640 [www.imagesplitter.net]-0-2.jpeg");
                } else {
                    System.out.println("No Push");
                  }
            });  

            response.push(HttpMethod.GET, "/img/tiger-2791980_640 [www.imagesplitter.net]-1-0.jpeg", ar -> {
                if (ar.succeeded()) {
                    System.out.println("sending push 4");
                    HttpServerResponse pushedResp = ar.result();
                    pushedResp.sendFile("tiger-2791980_640 [www.imagesplitter.net]-1-0.jpeg");
                } else {
                    System.out.println("No Push");
                  }
            });  

            response.push(HttpMethod.GET, "/img/tiger-2791980_640 [www.imagesplitter.net]-1-1.jpeg", ar -> {
                if (ar.succeeded()) {
                    System.out.println("sending push 5");
                    HttpServerResponse pushedResp = ar.result();
                    pushedResp.sendFile("tiger-2791980_640 [www.imagesplitter.net]-1-1.jpeg");
                } else {
                    System.out.println("No Push");
                  }
            });  

            response.push(HttpMethod.GET, "/img/tiger-2791980_640 [www.imagesplitter.net]-1-2.jpeg", ar -> {
                if (ar.succeeded()) {
                    System.out.println("sending push 6");
                    HttpServerResponse pushedResp = ar.result();
                    pushedResp.sendFile("tiger-2791980_640 [www.imagesplitter.net]-1-2.jpeg");
                } else {
                    System.out.println("No Push");
                  }
            });  

            response.push(HttpMethod.GET, "/img/tiger-2791980_640 [www.imagesplitter.net]-2-0.jpeg", ar -> {
                if (ar.succeeded()) {
                    System.out.println("sending push 7");
                    HttpServerResponse pushedResp = ar.result();
                    pushedResp.sendFile("tiger-2791980_640 [www.imagesplitter.net]-2-0.jpeg");
                } else {
                    System.out.println("No Push");
                  }
            });  

            response.push(HttpMethod.GET, "/img/tiger-2791980_640 [www.imagesplitter.net]-2-1.jpeg", ar -> {
                if (ar.succeeded()) {
                    System.out.println("sending push 8");
                    HttpServerResponse pushedResp = ar.result();
                    pushedResp.sendFile("tiger-2791980_640 [www.imagesplitter.net]-2-1.jpeg");
                } else {
                    System.out.println("No Push");
                  }
            });  

            response.push(HttpMethod.GET, "/img/tiger-2791980_640 [www.imagesplitter.net]-2-2.jpeg", ar -> {
                if (ar.succeeded()) {
                    System.out.println("sending push 9");
                    HttpServerResponse pushedResp = ar.result();
                    pushedResp.sendFile("tiger-2791980_640 [www.imagesplitter.net]-2-2.jpeg");
                } else {
                    System.out.println("No Push");
                  }
            });  

            response.sendFile("index.html");
            
        });
        /*
        router.get("/img/*").handler(context -> {
            HttpServerRequest request = context.request();
            String file = request.path().substring(5);
            System.out.println("Image request " + file);
            HttpServerResponse response = context.response();
            response.sendFile(file);
        });
        */
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
