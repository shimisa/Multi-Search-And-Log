/**
 * The Controller class manages the threads and provides methods for interact the user
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-04-20
 *
 */
package SearchLog;
import org.apache.logging.log4j.Logger;

public class Controller {
    private final int maxThreads;
    private int treadsFinished;
    private boolean founded; // For specifying if the number has found
    private int indexOfTheNumber;
    private String strIndexOfTheNumber;
    private final Logger logger;

    /**
     * Constructs the controller.
     * initialize founded to false.
     * @param maxT The max num of threads allocating for the search task
     * @param log A pointer for the logger
     */
    public Controller(int maxT, Logger log) {
        maxThreads = maxT;
        treadsFinished = 0;
        indexOfTheNumber = -1;
        strIndexOfTheNumber = "";
        founded = false;
        logger = log;
    }

    /**
     * Method for updating once the number founded
     * Update founded to true
     * log out message
     * @param index The index of the required number in the array
     */
    public synchronized void setFounded(int index){
        if (index != -1){
            founded = true;
            indexOfTheNumber = index;
            strIndexOfTheNumber += String.valueOf(index) + ", ";
            logger.info("The number founded at index: " + index);
        }
    }

    /**
     * The search results
     * @return A string of the search results for the user
     */
    public synchronized String printResults(){
        if(founded)
            return "The number is in index: " + strIndexOfTheNumber.substring(0,strIndexOfTheNumber.length() - 2);
        else
            return "The number is not in the array";
    }

    /**
     * Method for synchronize the threads work, enters threads into waiting mode till all finish
     */
    public synchronized void waitForAll() {
        while (treadsFinished < maxThreads){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("An exception occurred when thread is waiting", e); // logging out as an error
            }
        }
    }

    /**
     * Notify and log out message when a thread finishes
     */
    public synchronized void treadFinished() {
        treadsFinished++;
        logger.info("Thread finished");
        notifyAll();
    }

    /**
     * Method for checking if the number has founded already
     * @return A boolean - true if founded
     */
    public synchronized boolean isFounded() {
        return founded;
    }

    /**
     * Method for logging out a message when a thread has started
     */
    public synchronized void treadStarted() {
        logger.info("Thread started");
    }
}
