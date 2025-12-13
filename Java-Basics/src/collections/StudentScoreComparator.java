package collections;

import java.util.Comparator;

public class StudentScoreComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        if (o1.score.equals(o2.score)) {
            return o1.id.compareTo(o2.id);
        }
        return o2.score.compareTo(o1.score);
    }

}
