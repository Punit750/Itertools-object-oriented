//STUDENT NUMBER :23905993
//STUDENT NAME : PUNIT NITIN PATIL
package itertools;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Laziness<T> implements Iterator<T> {

    private Iterator<T> it; // The  iterator to  iterate over lazily
    private int count; // The maximum number of elements to iterate over
    private int counting; // The number of elements iterated so far

    // Constructor to initialize the iterator and the count limit
    public Laziness(Iterator<T> it, int count) {
        this.it = it;
        this.count = count;
        this.counting = 0;
    }

    @Override
    public boolean hasNext() {
        // Returns true if the counting is less than the count limit and the underlying iterator has more elements
        return counting < count && it.hasNext();
    }
    
    @Override
    public T next() {
        // Checks if there are more elements to iterate over
        if (hasNext()) {
            counting++; // Incrementing the count of elements iterated
            return it.next(); // Returns the next element from the underlying iterator
        } else {
            // Throwing an exception if there are no more elements to iterate
            throw new NoSuchElementException();
        }
    }
}
