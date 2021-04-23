/**
 * The Controller class manages the threads and provides methods for interact the user
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-04-20
 *
 */
package SearchLog;

public class SearchThread extends Thread{
    private final Controller controller;
    private final int indexFrom;
    private final int indexTill;
    private final int number;
    private final int[] array;

    /**
     * Constructs the search thread
     * @param arr The array given from the user
     * @param num The num to look for in the array
     * @param i The left border of the range
     * @param j The right border of the range
     * @param con A pointer to the controller
     */
    public SearchThread(int[] arr, int num, int i, int j, Controller con){
        controller = con;
        array = arr;
        number = num;
        indexFrom = i;
        indexTill = j;
    }

    /**
     * The run method executes the search for every thread, and updates the controller
     */
    @Override
    public void run() {
        controller.treadStarted();
        /* recursive version */
        controller.setFounded(recSearch(indexFrom,indexTill));
        controller.treadFinished();
    }

    /**
     * Recursive search method
     * @param i the index from
     * @param j the index till
     * @return if founded - the index of the num. else - -1
     */
    private int recSearch(int i, int j){
        if ( j < i /*|| controller.isFounded()*/)
            return -1;
        if (array[i] == number)
            return i;
        if (array[j] == number)
            return j;
        return recSearch(i + 1, j - 1);
    }

}
