package SearchLog;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class Log4jBackstop implements Thread.UncaughtExceptionHandler{

    private static Logger log = LogManager.getLogger(Log4jBackstop.class);

    public void uncaughtException(Thread t, Throwable ex) {
        log.error("Uncaught exception in thread: " + t.getName(), ex);
    }


}
