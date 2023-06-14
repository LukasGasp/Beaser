// HTTP API
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.logging.Handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Interface {

    HttpServer server;

    public Interface() throws Exception{
        server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new Handler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    public int getPort(){
        return server.getAddress().getPort();
    }

    public String getAdress(){
        return server.getAddress().getHostString();
    }
    
    class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String path = t.getRequestURI().getPath().substring(1);

            String response = "Error 404\nRequest: " + path;
            int code = 404;

            File file = new File("http/" + path);

            if (!file.exists()) {
                if(path.equals("ping")){
                    response = "pong";
                    code = 200;
                }

                if(path.equals("port")){
                    response = Integer.toString(getPort());
                    code = 200;
                }

                if(path.equals("ip")){
                    response = getAdress();
                    code = 200;
                }

                t.sendResponseHeaders(code, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                t.sendResponseHeaders(200, file.length());
                OutputStream os = t.getResponseBody();
                Files.copy(file.toPath(), os);
                os.close();
            }        }
    }
    
}
