import java.util.ArrayList;

public class FullCourse extends Course{

        private FullCourse(FullCourseBuilder builder){
            super(builder);
        }
        public static class FullCourseBuilder extends Course.CourseBuilder{
            public FullCourseBuilder(String name){
                super(name);
            }
            @Override
            public FullCourse build(){
                return new FullCourse(this);
            }
        }

        @Override
        public ArrayList<Student> getGraduatedStudents()
        {

            ArrayList<Student> list1 = new ArrayList<Student>();
            for (Grade grade : this.getGrades()) {
                if (grade.getPartialScore() >= 3 && grade.getExamScore() >= 2) {
                    list1.add(grade.getStudent());
                }
            }
            return list1;
        }
}
