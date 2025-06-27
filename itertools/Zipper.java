// STUDENT NUMBER :23905993
// STUDENT NAME : PUNIT NITIN PATIL
package itertools;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.NoSuchElementException;

public class Zipper<T, U, R> implements Iterator<R> {
    Iterator<T> lit; // The first  iterator
    Iterator<U> rit; // The second  iterator
    BiFunction<T, U, R> f; // The function which will combine elements from the two iterators

    // Constructor to initialize the two iterators and the combining function
    public Zipper(Iterator<T> lit, Iterator<U> rit, BiFunction<T, U, R> f) {
        this.lit = lit;
        this.rit = rit;
        this.f = f;
    }

    @Override
    public boolean hasNext() {
        // Returns true if both iterators have more elements
        return lit.hasNext() && rit.hasNext();
    }

    @Override
    public R next() {
        if (hasNext()) {
            // Getting the next element from each iterator
            T element1 = lit.next();
            U element2 = rit.next();
            // Applying the function to combine the elements and returning the result
            return f.apply(element1, element2);
        } else {
            // Throwing an exception if there are no more elements to iterate
            throw new NoSuchElementException();
        }
    }
}
