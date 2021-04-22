package SearchLog;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



public class Log4J {

    private static Logger logger = LogManager.getLogger(Log4J.class);

    public static void main(String [] args) {
        System.out.println("Hello world \n");

        logger.trace("This is a trace message");
        logger.info("This is an info message");
        logger.error("This is an error message");
        logger.warn("This is a warn message");
        logger.fatal("This is a fatal message");

        System.out.println("\n Completed");
    }
}
