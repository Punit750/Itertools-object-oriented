// STUDENT NUMBER :23905993
// STUDENT NAME : PUNIT NITIN PATIL
package itertools;

import java.util.NoSuchElementException;

public class Reversing<T> implements DoubleEndedIterator<T> {
    private DoubleEndedIterator<T> it; // The underlying double-ended iterator

    // Constructor to initialize the double-ended iterator
    public Reversing(DoubleEndedIterator<T> it) {
        this.it = it;
    }

    @Override
    public boolean hasNext() {
        // Returns true if there are more elements in the forward direction
        return it.hasNext();
    }

    @Override
    public T next() {
        // Checking if there are more elements to iterate over in the forward direction
        if (hasNext()) {
            // Returning the next element in the reverse direction
            return it.reverseNext();
        } else {
            // Throwing an exception if there are no more elements to iterate
            throw new NoSuchElementException();
        }
    }

    @Override
    public T reverseNext() {
        // Checking if there are more elements to iterate over in the forward direction
        if (hasNext()) {
            // Returning the next element in the forward direction
            return it.next();
        } else {
            // Throwing an exception if there are no more elements to iterate
            throw new NoSuchElementException();
        }
    }
}
