import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class LogHandler extends Handler {
  Interface webInterface;
  DateFormat df;

  public LogHandler(Interface tempInterface) {
    df = new SimpleDateFormat("dd.MM / HH:mm:ss");
    setFormatter(new SimpleFormatter());
    setLevel(Level.ALL);
    webInterface = tempInterface;
  }

  @Override
  public void publish(LogRecord record) {
    if (isLoggable(record)) {
      webInterface.addlog(df.format(new Date(record.getMillis())), record.getLevel().toString(), record.getMessage());
    }
  }

  @Override
  public void flush() {}

  @Override
  public void close() throws SecurityException {}
}
