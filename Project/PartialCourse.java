import java.util.ArrayList;

public class PartialCourse extends Course{

    private PartialCourse(PartialCourseBuilder builder){
            super(builder);
    }
    public static class PartialCourseBuilder extends Course.CourseBuilder{
        public PartialCourseBuilder(String name){
            super(name);
        }
        @Override
        public PartialCourse build(){
            return new PartialCourse(this);
        }
    }

    @Override
    public ArrayList<Student> getGraduatedStudents()
    {

        ArrayList<Student> list1 = new ArrayList<Student>();
        for (Grade grade : this.getGrades()) {
            if (grade.getTotal() >= 5) {
                list1.add(grade.getStudent());
            }
        }
        return list1;
    }

}
