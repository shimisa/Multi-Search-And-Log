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
 * @version 1.0
 * @since   2020-04-20
 *
 */
package SearchLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static final Logger logger = LogManager.getLogger(Log4J.class);

    public static void main(String [] args){

        /*
           The user Input:
           N - the length of the array
           T - the Max active threads
           number - The num to look for in the array
         */
        final int[] array = {8,5,3,54,67,2,5,82,9,0,2,434,8};
        final int T = 5;
        int number = 8;

        /* The variables for the algo */
        final Controller controller;
        final int N = array.length;
        int workArea = N/T; // the range for each thread
        int maxTreads;
        int i; // index from
        int j; // index till

        /* define the range work of a thread and the maximum number of threads */
        if (T >= N) {
            workArea = 1;
            maxTreads = N;
        }
        else
            maxTreads = T;
        controller = new Controller(maxTreads, logger);
        /* defines the initial range work of the first thread */
        i = 0;
        j = workArea;

        /*
          Creates new threads and run them recursively.
          while (range work + till index) is bigger array length ->
          start a new thread with the specific  range
          Else, start the last thread till the end of the array
         */
        try {
            newTreadSearch(i, j, workArea, N, array, number, controller);
        }catch (Exception e){
            logger.error(e);
            throw e;
        }

        controller.waitForAll(); // wait for all threads to finish their work
        System.out.println(controller.printResults()); // prints the results
    }

    /**
     * The private recursive method for creating and starting the threads
     */
    private static void newTreadSearch(int i , int j, int workArea, int N, int[]array, int number, Controller c){

        if ((i + workArea) >= (N - 1)){
            (new SearchThread(array, number, i, (N - 1), c)).start();
            return;
        }

        (new SearchThread(array, number, i , j, c)).start();  // create and start a new thread
        newTreadSearch(i + workArea, j + workArea, workArea, N, array, number, c); // the recursive call

    }

}
