import java.util.Collection;

public class BestTotalScore implements Strategy{
    @Override
    public Student getBestStudent(Collection<Grade> grades) {
        Student stud = null;
        Double besttot = 0.0;
        for (Grade grade : grades) {
            if (grade.getTotal() > besttot) {
                besttot = grade.getTotal();
                stud = grade.getStudent();
            }
        }

        return stud;
    }
}
