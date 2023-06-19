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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.Map;

public class Interface {

    HttpServer server;
    Logger logger = Logger.getLogger("beaser");

    String universe = "none";
    int dmx;
    int panel = 0;

    private static List<LogEntry> logs = new ArrayList<>();

    public Interface() throws Exception{
        server = HttpServer.create(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 5000), 1);
        server.createContext("/", new Handler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    // ja, loghandeler wird nicht benutzt. Muss aber drin sein! Kp, warum
    public void startlogging(LogHandler logHandler){
        logger.log(Level.INFO, "[API] Starting REST-API");
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

    public void addlog(String time, String level, String message){
        String[] parts = message.split("\\] ");
        String origin = parts[0].substring(parts[0].lastIndexOf('[') + 1);
        String text = parts[1];

        String color;
        if(level.equals("INFO")){
             color = "#FFF";
        } else {          
            color = "#ff0000";
        }
        logs.add(new LogEntry(time, level, origin, text, color));
    }

    // Helpers

    private String logsToJson() {
    StringBuilder sb = new StringBuilder("[");
    for (LogEntry log : logs) {
        if (sb.length() > 1) {
            sb.append(",");
        }
        sb.append("{");
        Map<String, String> logMap = log.toMap();
        sb.append(logMap.entrySet().stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(",")));
        sb.append("}");
    }
    sb.append("]");
    return sb.toString();
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

                if(path.equals("logs")){
                    response = logsToJson();
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
                    logger.log(Level.WARNING, "[API] Setting DMX Address to " + value);
                }

                if(path.equals("setpanel")){
                    InputStream is = t.getRequestBody();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String value = br.readLine();
                    response = "OK";
                    code = 200;
                    panel =  Integer.parseInt(value);
                    logger.log(Level.WARNING, "[API] Setting Panel-amount to " + value);
                }

                t.sendResponseHeaders(code, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                logger.log(Level.FINER, "Request to " + path + " (" + file.length() + ")");
                t.sendResponseHeaders(200, file.length());
                OutputStream os = t.getResponseBody();
                Files.copy(file.toPath(), os);
                os.close();
            }        }
    }
    
}
