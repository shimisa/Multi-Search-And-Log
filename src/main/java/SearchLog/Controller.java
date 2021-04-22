package SearchLog;

import org.apache.logging.log4j.Logger;

public class Controller {

    private final int maxThreads;
    private int treadsFinished;
    private boolean founded = false;
    private int indexOfTheNumber;

    private Logger logger;


    public Controller(int maxT, Logger log) {
        maxThreads = maxT;
        treadsFinished = 0;
        indexOfTheNumber = -1;
        logger = log;

    }

    public synchronized void setFounded(int index){
        if (index != -1){
            founded = true;
            indexOfTheNumber = index;
            logger.info("The number founded at index: " + index);
        }
    }

    public synchronized String printResults(){

        if(founded)
            return "The number is in index: " + indexOfTheNumber;
        else
            return "The number is not in the array";
    }

    public synchronized void waitForAll() {
        while (treadsFinished < maxThreads){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error(e.getMessage()); // logging out as an error
            }
        }
    }

    public synchronized void treadFinished() {
        treadsFinished++;
        logger.info("Thread finished");
        notifyAll();
    }

    public synchronized boolean isFounded() {
        return founded;
    }

    public synchronized void treadStarted() {
        logger.info("Thread started");
    }
}
