// STUDENT NUMBER :23905993
// STUDENT NAME : PUNIT NITIN PATIL
package itertools;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.NoSuchElementException;

public class Reduce<T, R> implements Iterator<R> {
    private Iterator<T> it; // The iterator
    private R result; // The Required result
    private BiFunction<R, T, R> f; // The function which will combine the result with the next element

    // Constructor to initialize the iterator, initial result, and combining function
    public Reduce(Iterator<T> it, R init, BiFunction<R, T, R> f) {
        this.it = it;
        this.result = init;
        this.f = f;
    }
    
    @Override
    public boolean hasNext() {
        // Returns true if the underlying iterator has more elements
        return it.hasNext();
    }

    @Override
    public R next() {
        if (!hasNext()) {
            // Throwing an exception if there are no more elements to iterate
            throw new NoSuchElementException();
        }
        while (hasNext()) {
            // Getting the next element from the underlying iterator
            T element = it.next();
            // Applying the function to combine the accumulated result with the next element
            result = f.apply(result, element);
        }
        // Returning the Final which combines result and next element
        return result;
    } 
}
