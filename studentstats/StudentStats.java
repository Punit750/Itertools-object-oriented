// STUDENT NUMBER :23905993
// STUDENT NAME : PUNIT NITIN PATIL
package studentstats;

import java.util.Iterator;
import java.util.function.Predicate;
import itertools.DoubleEndedIterator;
import itertools.Reversing;
import itertools.Itertools;
import java.util.function.BiFunction;
import java.util.function.Function;
import studentapi.*;

/** A class for computing the average of a number of integer samples. */
class IntegerAverage {
    private int total = 0;
    private int count = 0;

    public void addSample(int sample) {
        total += sample;
        count++;
    }

    public int getAverage() {
        return count == 0 ? 0 : total / count;
    }
}

/** A {@link BiFunction} adding an integer sample to an {@link IntegerAverage}. */
class IntegerAverageReduction implements BiFunction<IntegerAverage, Integer, IntegerAverage> {
    public IntegerAverage apply(IntegerAverage lhs, Integer rhs) {
        if (rhs == null) return lhs;
        lhs.addSample(rhs);
        return lhs;
    }
}

/** A {@link Function} retrieving the mark for a particular unit from a {@link Student} record. */
class GetUnitMark implements Function<Student, Integer> {
    private String unit;

    public GetUnitMark(String unit) {
        this.unit = unit;
    }

    public Integer apply(Student student) {
        return student.getMark(unit);
    }
}

/** A class implementing an iterator over students who have taken a specific unit, from newest to oldest. */
class UnitFilteredReverseIterator implements Iterator<Student> {

    private Iterator<Student> Filtering; // Iterator for filtered students

    public UnitFilteredReverseIterator(StudentList list, String unit) {
        // Creating a DoubleEndedIterator over the StudentList
        DoubleEndedIterator<Student> studentListIterator = new StudentListIterator(list);

        // Reverse the iterator to iterate in reverse order
        DoubleEndedIterator<Student> reversedIterator = new Reversing<>(studentListIterator);

        // Defining a predicate to filter students by whether they have a mark for the specified unit
        Predicate<Student> ValidScore = new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student != null && student.getMark(unit) != null;
            }
        };
        // Filter the reversed iterator based on the defined predicate
        this.Filtering = Itertools.filter(reversedIterator, ValidScore);
    }

    @Override
    public boolean hasNext() {
        return Filtering.hasNext();//Checks if there are more filtered students to iterate over.
    }

    @Override
    public Student next() {
        return Filtering.next();//Returns the next filtered student.
    }
}



/** A collection of statistical and analytical methods for working with the student API. */
public class StudentStats {
    /**
     * Returns the average mark (integer division) across all students who have completed a given unit.
     *
     * @param list The student API interface.
     * @param unit The unit code.
     * @return The average mark for all students who have taken `unit`.
     * @throws QueryTimedOutException
     */
    public static int unitAverage(StudentList list, String unit) throws QueryTimedOutException {
        return Itertools.reduce(
                        Itertools.map(new StudentListIterator(list), new GetUnitMark(unit)),
                        new IntegerAverage(),
                        new IntegerAverageReduction())
                .getAverage();
    }

    /**
     * Returns an iterator over the students who have taken a given unit, from newest to oldest.
     *
     * @param list The student API interface.
     * @param unit The unit code.
     * @return An iterator over the students who have taken `unit`, from newest to oldest.
     */
    public static Iterator<Student> unitNewestStudents(StudentList list, String unit) {
        return  new UnitFilteredReverseIterator(list,  unit);
    }
}
