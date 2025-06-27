// STUDENT NUMBER :23905993
// STUDENT NAME : PUNIT NITIN PATIL
package itertools;

import java.util.Iterator;
import java.util.function.Function;
import java.util.NoSuchElementException;

public class SingleEndedMaps<T, R> implements Iterator<R> {
    private Iterator<T> it; // The  iterator
    private Function<T, R> f; // The function which applies to each element

    // Constructor to initialize the iterator and function
    public SingleEndedMaps(Iterator<T> it, Function<T, R> f) {
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
            // Getting the next element from the  iterator
            T element = it.next();
            // Applying the function to the element and returning the result
            return f.apply(element);
        } else {
            // Throwing an exception if there are no more elements to iterate
            throw new NoSuchElementException();
        }
    }
}
