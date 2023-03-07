import java.util.Collection;

public class BestExamScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        Student stud = null;
        Double bestex = 0.0;
        for (Grade grade : grades) {
            if (grade.getExamScore() > bestex) {
                bestex = grade.getExamScore();
                stud = grade.getStudent();
            }
        }

        return stud;
    }
}
