// HTTP API
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Interface {

    HttpServer server;
    Logger logger;

    String universe = "none";
    int dmx;
    int panel = 0;

    public Interface() throws Exception{
        logger = Logger.getLogger("Logger for Interface");
        server = HttpServer.create(new InetSocketAddress(80), 1);
        server.createContext("/", new Handler());
        server.setExecutor(null); // creates a default executor
        logger.log(Level.INFO, "Starting REST-API");
        server.start();
    }

    public int getPort(){
        return server.getAddress().getPort();
    }

    public void setUniverse(int tempuniverse){
        universe = Integer.toString(tempuniverse);
    }

    public void setdmxaddress(int tempaddress){
        dmx = tempaddress;
    }

    public void setpanels(int temppanel){
        panel = temppanel;
    }
    
    class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String path = t.getRequestURI().getPath().substring(1);

            String response = "Error 404\nRequest: " + path;
            int code = 404;

            if(path.equals("")){
                path = "index.html";
            }

            File file = new File("http/" + path);

            if (!file.exists()) {
                if(path.equals("ping")){
                    response = "pong";
                    code = 200;
                }

                if (path.equals("status")) {
                    response = "{\"ip\":\"" + InetAddress.getLocalHost().getHostAddress() + "\", \"port\": \"" + Integer.toString(getPort()) + "\", \"host\":\"" + InetAddress.getLocalHost().getHostName() + "\"}";
                    t.getResponseHeaders().set("Content-Type", "application/json");
                    code = 200;
                }

                if(path.equals("dmx")){
                    response = "{\"universe\":\"" + universe + "\", \"address\": \"" + Integer.toString(dmx) + "\", \"range\":\"" + Integer.toString(dmx + panel*11 + 3) + "\", \"panel\":\"" + Integer.toString(panel) + "\"}";
                    t.getResponseHeaders().set("Content-Type", "application/json");
                    code = 200;
                }

                if(path.equals("setaddress")){
                    InputStream is = t.getRequestBody();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String value = br.readLine();
                    response = "OK";
                    code = 200;
                    dmx =  Integer.parseInt(value);
                }

                if(path.equals("setpanel")){
                    InputStream is = t.getRequestBody();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String value = br.readLine();
                    response = "OK";
                    code = 200;
                    panel =  Integer.parseInt(value);
                }

                t.sendResponseHeaders(code, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                logger.log(Level.INFO, "Request to " + path + " (" + file.length() + ")");
                t.sendResponseHeaders(200, file.length());
                OutputStream os = t.getResponseBody();
                Files.copy(file.toPath(), os);
                os.close();
            }        }
    }
    
}
