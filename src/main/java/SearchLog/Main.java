/**
 * The Main class contains the user input:
 * 1. number of threads allocating for the search.
 * 2. Array of numbers.
 * 3. A number to look for.
 * The Main class run the threads according to the algorithm:
 * 1. Divides the Array to sections according to the max num of threads
 * 2. Each thread get a range in the array to search in.
 *
 * @author  Shimi Sadaka
 */
package SearchLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String [] args){

        /* The user input */
        final int[] userArray = {8,5,3,8,67,2,5,82,8,8};
        final int numOfTreads = 4;
        int number = 8;

        /* Defines The variables for the algo, and Initializing range work of the first thread  */
        final int userArrayLength = userArray.length;
        final Controller controller = new Controller(userArrayLength, numOfTreads, logger);;

        int indexFrom = 0;
        int indexTill = controller.getSingleThreadRange();

        /*
          Creates new threads and run them recursively.
          while (range work + till index) is bigger userArray length ->
          start a new thread with the specific  range
          Else, start the last thread till the end of the userArray
         */
        try {
            startThread(indexFrom, indexTill, controller.getSingleThreadRange(), userArrayLength, userArray, number, controller.getMaxThreads(), 0, controller);
        } catch (Exception e){
            logger.error(e);
            throw e;
        }

        controller.waitForAll(); // wait for all threads to finish their work
        System.out.println(controller.printResults()); // prints the results
    }

    /**
     * Private recursive method for creating and starting the threads
     */
    private static void startThread(int indexFrom , int indexTill, int singleThreadRange, int N, int[]array, int number, int maxTreads, int threadsStarted, Controller con){

        if ((threadsStarted + 1) == maxTreads){
            (new SearchThread(array, number, indexFrom, N, con)).start(); // create and start the last thread
            return;
        }
        (new SearchThread(array, number, indexFrom , indexTill, con)).start();  // create and start a new thread
        startThread(indexTill , (indexTill + singleThreadRange), singleThreadRange, N, array, number, maxTreads, threadsStarted + 1, con); // the recursive call
    }

}