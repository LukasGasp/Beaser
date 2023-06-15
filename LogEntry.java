import java.util.HashMap;
import java.util.Map;

public class LogEntry {
    private String time;
    private String message;
    private String level;
    private String origin;
    private String color;

    public LogEntry(String time, String level, String origin, String message, String color) {
        this.time = time;
        this.level = level;
        this.message = message;
        this.origin = origin;
        this.color = color;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("time", time);
        map.put("message", message);
        map.put("origin", origin);
        map.put("level", level);
        map.put("color", color);
        return map;
    }
}
