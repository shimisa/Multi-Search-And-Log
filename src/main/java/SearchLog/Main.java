package SearchLog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Log4J.class);

    public static void main(String [] args){

        /*
           The user Input:
           N the length of the array
           T the Max active threads
         */
        final int[] array = {34,5,3,54,67,2,5,8,9,0,2,434,45};
        final int T = 5;

        final Controller c;
        final int N = array.length;
        int workArea = N/T; // the range for each thread
        int maxTreads;
        int i; // index from
        int j; // index till
        int number = 8; // the num to find in the array

        // define the range work of a thread and the maximum number of threads
        if (T >= N) {
            workArea = 1;
            maxTreads = N;
        }
        else
            maxTreads = T;


        c = new Controller(maxTreads, logger);

        i = 0;
        j = workArea;


        /**
         * While the range work plus the till index is bigger than the array length -
         * start a new thread with the specific  range
         * Else, start the last thread till the end of the array
         */
        /*
        while((i + workArea) < (N - 1)){

            (new SearchLog.SearchThread(array, number, i , j, c)).start();

            i += workArea;
            j += workArea;

        }
        // the last thread invoked
        (new SearchLog.SearchThread(array, number, i , (N - 1), c)).start();
        */


        // invoked the recursive version
        newTreadSearch(i + workArea, j + workArea, workArea, N, array, number, c);

        c.waitForAll(); // wait for all threads to finish their work
        System.out.println(c.printResults()); // prints the results
    }

    /**
     * The recursive method for creating and starting the threads
     *
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
