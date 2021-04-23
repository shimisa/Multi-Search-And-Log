package SearchLog;


public class SearchThread extends Thread{
    private final Controller controller;
    private int indexFrom;
    private int indexTill;
    private int number;
    private int array[];

    public SearchThread(int[] arr, int num, int i, int j, Controller con){
        controller = con;
        array = arr;
        number = num;
        indexFrom = i;
        indexTill = j;

    }

    /**
     * The run method executes the search for every thread
     */
    @Override
    public void run() {

        controller.treadStarted();
        // iterative version:
        // while index in range and the number did not found yet - keep searching
        /*
        for (int i = indexFrom; i <= indexTill && !controller.isFounded() ; i++) {
            if (array[i] == number)
                controller.setFounded(i);
        }
        */

        // recursive version:
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
        if ( j < i || controller.isFounded())
            return -1;
        if (array[i] == number)
            return i;
        if (array[j] == number)
            return j;
        return recSearch(i + 1, j - 1);
    }



}
