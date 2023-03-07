import java.util.*;

public abstract class Course implements Comparable<Course>{
    private String name;
    private Teacher teacher;
    private Grade grade;
    private ArrayList<Assistant> assistants;
    private ArrayList<Grade> grades;
    private HashMap<String, Group> dict =new HashMap<>();
    private Strategy strategy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public ArrayList<Assistant> getAssistants() {
        return assistants;
    }

    public void setAssistants(ArrayList<Assistant> assistants) {
        this.assistants = assistants;
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Grade> grades) {
        this.grades = grades;
    }

    public HashMap<String, Group> getDict() {
        return dict;
    }

    public void setDict(HashMap<String, Group> dict) {
        this.dict = dict;
    }

    public Course(String name) {
        this.name = name;
        assistants=new ArrayList<>();
        grades= new ArrayList<>();
        dict= new HashMap<>();
    }

    public Course(Teacher teacher) {
        this.teacher = teacher;
    }

    public int compareTo(Course cour) {
        return cour.getName().compareTo(this.getName());
    }

    public Course(CourseBuilder builder){
        this.name=builder.Name;
        this.teacher=builder.Teacher;
        this.assistants=builder.Assistants;
        this.grades=builder.Grades;
        this.strategy = builder.strategy;
        this.dict=builder.Dict;

    }
    public void addAssistant(String ID, Assistant assistant) {
        if (!this.assistants.contains(assistant))
            this.assistants.add(assistant);
        this.dict.get(ID).setAssistant(assistant);
    }

    public void addStudent(String ID, Student student) {

        this.dict.get(ID).add(student);
    }

    public void addGroup(Group group) {
        String ID = group.getID();
        dict.put(ID, group);
    }

    public void addGroup(String ID, Assistant assistant) {
        Group group = new Group(ID, assistant);
        dict.put(ID, group);
        assistants.add(assistant);
    }

    public void addGroup(String ID, Assistant assistant, Comparator<Student> comp) {
        Group group = new Group(ID, assistant, comp);
        dict.put(ID, group);
    }

    public Grade getGrade(Student student) {
        for (Grade grade : this.grades) {
            if (grade.getStudent() == student)
                return grade;
        }
        return null;
    }

    public void addGrade(Grade grade) {

        this.grades.add(grade);
    }
    public Student getStudent(String name){
        ArrayList<Student> studenti = getAllStudents();
        for(Student student:studenti){
            if(name.equals(student.toString())){
                return student;
            }
        }
        return null;
    }
    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> list1 = new ArrayList<Student>();
        for (String key : dict.keySet()) {
            list1.addAll(dict.get(key).getStudents());
        }
        return list1;
    }

    public HashMap<Student, Grade> gettAllStudentGrades() {
        HashMap<Student, Grade> cat = new HashMap<Student, Grade>();
        for (Grade grade : this.grades) {
            cat.put(grade.getStudent(), grade);
        }
        return cat;
    }

    public abstract ArrayList<Student> getGraduatedStudents();
    public Student getBestStudent() {
        return this.strategy.getBestStudent(grades);
    }
    @Override
    public String toString() {
        return "Course{" +
                "Nume Curs='" + name + '\'' +
                ", Profesor de Curs=" + teacher +
                ", assistentii=" + assistants +
                ", Notele=" + grades +
                ", Grupele=" + dict +
                '}'+'\n';
    }

    public abstract static class CourseBuilder {
        private String Name;
        private Teacher Teacher;
        private Grade Grade;
        private final ArrayList<Assistant> Assistants;
        private ArrayList<Grade> Grades;
        private HashMap<String, Group> Dict;
        private Strategy strategy;
        public CourseBuilder(String Name){
             this.Name=Name;
             Assistants=new ArrayList<>();
             Grades=new ArrayList<>();
             Dict=new HashMap<>();
             strategy= new BestExamScore();
        }
        public CourseBuilder setName(String Name) {
            this.Name = Name;
            return this;
        }
        public CourseBuilder setTeacher(Teacher Teacher) {
            this.Teacher = Teacher;
            return this;
        }

        public CourseBuilder setAssistant(Assistant Assistant) {
            this.Assistants.add(Assistant);
            return this;
        }

        public CourseBuilder setGrades(ArrayList<Grade> Grade) {
            this.Grades=Grade;
            return this;
        }

        public CourseBuilder setGroup(String ID, Group group){
             this.Dict.put(ID, group);
             return this;
        }
        public CourseBuilder strategy(Strategy strategy) {
            this.strategy = strategy;
            return this;
        }
        public abstract Course build();
    }
}

