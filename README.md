# Multi-Search-And-Log
Multi-threaded searching an array, and printing logs with Log4J

Divides an Array into sections according to the maximun number of threads have been allocated for the task.
All the threads search for a givven number in parallel, each thread in his own section.
If founded - Returns the index of the number in the array.

Along the way, the system uses the log4J framework for printing out logs and document them into a file - MyLogs.log
