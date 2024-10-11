import java.io.FileWriter;
import java.io.IOException;



class  Logger {
    Logger() {
    }

    private static Logger _logger = null;
    public static Loglevel _loglevel = Loglevel.INFO;
    private FileWriter writer;

    public static void setLogger(Loglevel loglevel) {
        _loglevel = loglevel;
    }

    public static Logger GetInstanse() {
        if (_logger == null) {
            _logger = new Logger();
        }

        return _logger;
    }


    public void Log(String message, Loglevel loglevel) {
        {
            try {
                writer = new FileWriter("LogLevel");
                writer.write(message + ": " + loglevel);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }


    enum Loglevel {
        INFO, WARNING, ERROR
    }
}



public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.Log("Error", Logger.Loglevel.INFO);
        Logger.GetInstanse();
    }
}

