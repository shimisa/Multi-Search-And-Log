/**
 * The Controller class manages the threads and provides methods for interact the user
 * @author  Shimi Sadaka
 */
package SearchLog;
import org.apache.logging.log4j.Logger;
import java.lang.Math;

public class Controller {
    private final int userArrayLength;
    private final int userNumOfThreads;
    private int maxThreads;
    private int singleThreadRange;
    private int treadsFinished;
    private boolean founded;
    private String strIndexOfTheNumber;
    private final Logger logger;

    /**
     * Constructs the controller.
     * initialize founded to false.
     * @param n The user array length
     * @param log A pointer for the logger
     */
    public Controller(int n, int numT, Logger log) {
        userArrayLength = n;
        userNumOfThreads = numT;
        treadsFinished = 0;
        strIndexOfTheNumber = "";
        founded = false;
        logger = log;
        defineRangeAndMaxTreads(numT, n);
    }

    /**
     * Method for updating once the number founded
     * Update founded to true
     * log out message
     * @param indexes The index of the required number in the array
     */
    public synchronized void setFounded(String indexes){
        if (!indexes.equals("")){
            founded = true;
            strIndexOfTheNumber += indexes;
            logger.info("The number founded at index: " + indexes.substring(0,indexes.length() - 2));
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
                logger.error("An exception occurred when thread is waiting", e);
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
     * Method for logging out a message when a thread has started
     */
    public synchronized void treadStarted() {
        logger.info("Thread started");
    }

    /**
     * Defines the work range of each thread
     */
    private synchronized void defineRangeAndMaxTreads(int userNumOfThreads, int userArrayLength) {
        if (userNumOfThreads < 1){
            logger.warn("Input number of threads is less than 1 -> Allocating one thread");
            maxThreads = 1;
            singleThreadRange = userArrayLength;
        }
        if (userNumOfThreads > userArrayLength){
            logger.warn("Input number of threads is too high -> Reducing number of threads to array length");
            maxThreads = userArrayLength;
            singleThreadRange = 1;
            return;
        }
        double tmp = (double)userArrayLength / userNumOfThreads;
        int tmp2 = (int) Math.floor(tmp);
        singleThreadRange = tmp2;
        maxThreads = userNumOfThreads;
    }

    public synchronized int getMaxThreads(){
        return maxThreads;
    }

    public synchronized int getSingleThreadRange(){
        return singleThreadRange;
    }

}
