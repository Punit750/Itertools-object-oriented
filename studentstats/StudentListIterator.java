// NAME: PUNIT NITIN PATIL
// STUDENT: 23905993
package studentstats;

import itertools.DoubleEndedIterator;
import studentapi.*;
import java.util.NoSuchElementException;

/**
 * A (double ended) iterator over student records pulled from the student API.
 *
 * <p>
 * This does not load the whole student list immediately, but rather queries the
 * API ({@link StudentList#getPage}) only as needed.
 */

public class StudentListIterator implements DoubleEndedIterator<Student> {
    private StudentList list; // Stores the StudentList object
    private int retries; // Maximum number of retries for timed-out queries
    private int currentPage; // Index of the current page being iterated
    private int studentIndexInPage; // Index of the current student within the current page
    private Student[] currentPageStudents; // List containing students from the current page
    private int lastPage; // Index of the last page using for the Reverse Iteration
    private int currentPageLast; // Index of the last student accessed in the current page Using for the Reverse Iteration
    private int tracker; // Tracks the total number of students iterated over

    /**
     * Construct an iterator over the given {@link StudentList} with the specified
     * retry quota.
     *
     * @param list    The API interface.
     * @param retries The number of times to retry a query after getting {@link
     *                QueryTimedOutException} before declaring the API unreachable
     *                and throwing an {@link ApiUnreachableException}.
     */
    public StudentListIterator(StudentList list, int retries) {
        this.list = list;
        this.retries = retries; 
        this.currentPage = 0;
        this.studentIndexInPage = 0; 
        this.currentPageStudents = null;
        this.lastPage = list.getNumPages() - 1; // Initialize last page index
        this.currentPageLast = -1; // Initialize last student index for reverse iteration
        this.tracker = 0; // Initialize tracker for total students iterated
    }

    /**
     * Construct an iterator over the given {@link StudentList} with a default retry
     * quota of 3.
     *
     * @param list The API interface.
     */
    public StudentListIterator(StudentList list) {
        this(list, 3); // Calling the primary constructor with default retries set to 3
    }

    @Override
    public boolean hasNext() {
        // Checking if there are more students to iterate over
        return tracker < list.getNumStudents();
    }

    @Override
    public Student next() {
        if (!hasNext()) {
            throw new NoSuchElementException(); // No more students to iterate 
        }
        // Checking if students from the current page are exhausted or not loaded yet
        if (currentPageStudents == null || studentIndexInPage >= currentPageStudents.length) {
            if (!FlipToNextPage()) {
                throw new NoSuchElementException(); // No more pages to Get
            }
            studentIndexInPage = 0; // Resetting index for the new page
        }

        tracker++; // Incrementing the tracker for each student iterated
        return currentPageStudents[studentIndexInPage++]; // Returning the next student and update index
    }

    @Override
    public Student reverseNext() {
        if (!hasNext()) {
            throw new NoSuchElementException(); // No more students to iterate in reverse order
        }
        // Checking if students from the previous page are loaded or need to be fetched
        if (currentPageStudents == null || currentPageLast < 0) {
            if (!FlipToPreviousPage()) {
                throw new NoSuchElementException(); // No more pages to Get
            }
            currentPageLast = currentPageStudents.length - 1; // Setting the index to the last student of the new page
        }

        tracker++; // Incrementing the tracker for each student iterated
        return currentPageStudents[currentPageLast--]; // Returning the previous student and update index
    }

    private boolean FlipToNextPage() {
        // Variable which will track retry attempts
        int Tries = 0;
        while (Tries < retries) {
            try {
                // Checking if there are more pages to get
                if (currentPage < list.getNumPages()) {
                    currentPageStudents = list.getPage(currentPage); // Getting the next page
                    if (currentPageStudents != null && currentPageStudents.length > 0) {
                        currentPage++; // Moving to the next page
                        return true;
                    }
                }
                return false; // No more pages to available 
            } catch (QueryTimedOutException e) {
                Tries++; // Incrementing retry attempts
            }
        }
        throw new ApiUnreachableException(); // Throwing an exception if retries are exhausted
    }

    private boolean FlipToPreviousPage() {
        // Variable to track retry attempts
        int Tries = 0;
        while (Tries < retries) {
            try {
                // Checking if there are previous pages to retrieve
                if (lastPage >= 0) {
                    currentPageStudents = list.getPage(lastPage); // Getting the previous page
                    if (currentPageStudents != null && currentPageStudents.length > 0) {
                        lastPage--; // Moving to the previous page
                        return true;
                    }
                }
                return false; // No more pages to available
            } catch (QueryTimedOutException e) {
                Tries++; // Incrementing retry attempts
            }
        }
        throw new ApiUnreachableException(); // Throwing an exception if retries are exhausted
    }
}
