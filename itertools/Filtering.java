// STUDENT NUMBER :23905993
// STUDENT NAME : PUNIT NITIN PATIL
package itertools;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.NoSuchElementException;

public class Filtering<T> implements Iterator<T> {
    private Iterator<T> it; // The iterator
    private Predicate<T> pred; // The predicate to filter elements
    private T NextElement; // The next element that satisfies the predicate
    private boolean NextCalled; // Flag to check if satisfyingElement has been called

    // Constructor that initialize the iterator and predicate
    public Filtering(Iterator<T> it, Predicate<T> pred) {
        this.it = it;
        this.pred = pred;
        this.NextElement = null;
        this.NextCalled = false;
    }

    // Method which will find the next element that satisfies the predicate
    public void satisfyingElement() {
        while (it.hasNext()) {
            T elem = it.next(); // Get the next element
            if (pred.test(elem)) { // Checking if it satisfies the predicate
                NextElement = elem; // Setting it as the next element
                break;
            } else {
                NextElement = null; // Here If not, continue searching
            }
        }
    }

    @Override
    public boolean hasNext() {
        if (!NextCalled) { // Check if satisfyingElement has not been called yet
            satisfyingElement(); // Find the next satisfying element
            NextCalled = true; // Mark that satisfyingElement has been called
        }
        return NextElement != null; // Returning true if there is a next element
    }

    @Override
    public T next() {
        if (hasNext()) { // Ensuring there is a next element(that is hasNext() is true or not)
            T currElem = NextElement; // Getting the current next element
            NextElement = null; // Here , Reset NextElement
            NextCalled = false; // Here , Reset the flag
            return currElem; // Return the current next element
        } else {
            throw new NoSuchElementException(); // Throwing an exception if no next element
        }
    }
}
