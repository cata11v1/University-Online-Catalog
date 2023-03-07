import java.util.Collection;

public class BestPartialScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        Student stud = null;
        Double bestpar = 0.0;

        for (Grade grade : grades) {
            if (grade.getPartialScore() > bestpar) {
                bestpar = grade.getPartialScore();
                stud = grade.getStudent();
            }
        }

        return stud;
    }
}
