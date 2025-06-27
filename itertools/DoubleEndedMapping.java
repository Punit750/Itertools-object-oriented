// STUDENT NUMBER :23905993
// STUDENT NAME : PUNIT NITIN PATIL
package itertools;

import java.util.function.Function;
import java.util.NoSuchElementException;

public class DoubleEndedMapping<T, R> implements DoubleEndedIterator<R> {
    private DoubleEndedIterator<T> it; // The DoubleEnded iterator
    private Function<T, R> f; // The function which will be applied to each element

    // Constructor to initialize the double-ended iterator and function
    public DoubleEndedMapping(DoubleEndedIterator<T> it, Function<T, R> f) {
        this.it = it;
        this.f = f;
    }

    @Override
    public boolean hasNext() {
        // Returns true if the iterator has more elements
        return it.hasNext();
    }

    @Override
    public R next() {
        if (hasNext()) {
            // Getting the next element from the underlying iterator
            T element = it.next();
            // Applying the function to the element and returning the result
            return f.apply(element);
        } else {
            // Throwing an exception if there are no more elements to iterate
            throw new NoSuchElementException();
        }
    }

    @Override
    public R reverseNext() {
        if (hasNext()) {
            // Getting the next element from the underlying iterator in reverse direction
            T element = it.reverseNext();
            // Applying the function to the element and return the result
            return f.apply(element);
        } else {
            // Throwing an exception if there are no more elements to iterate
            throw new NoSuchElementException();
        }
    }
}
