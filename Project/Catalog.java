import java.util.ArrayList;

public class Catalog {
    public static Catalog instance;
    public ArrayList<Course> courses;
    public ArrayList<Teacher>teachers;
    public ArrayList<Assistant>assistants;
    public ArrayList<Student> students;
    public ArrayList<Parent> parents;
    private ArrayList<Observer> observers;

    public Catalog() {

        courses = new ArrayList<>();
        observers = new ArrayList<>();
        teachers=new ArrayList<>();
        assistants=new ArrayList<>();
        students= new ArrayList<>();
        parents= new ArrayList<>();
    }
    public static Catalog getInstance() {
        if (instance == null) {
            synchronized (Catalog.class) {
                if (instance == null) {
                    instance = new Catalog();
                }
            }
        }
        return instance;
    }
    public void addCourse(Course course) {

        courses.add(course);
    }
    public void removeCourse(Course course) {

        courses.remove(course);
    }
    public ArrayList<Teacher> getTeachers(){
        for (Course course : courses) {
            int kteach=0;
            for(Teacher teacher : teachers){
                if(teacher.equals(course.getTeacher())){
                    kteach=1;
                }
            }
            if(kteach==0) {
                teachers.add(course.getTeacher());
            }
        }
        return teachers;
    }
    public ArrayList<Assistant> getAssistants(){
        for (Course course : courses) {
            ArrayList<Assistant> assistants1 = course.getAssistants();
            for(Assistant assistant : assistants1){
                int kasist=0;
                for(Assistant assistant2 : assistants ){
                    if(assistant2.equals(assistant)){
                        kasist=1;
                    }
                }
                if(kasist==0) {
                    assistants.add(assistant);
                }
            }
        }
        return assistants;
    }

    public ArrayList<Student> getStudents() {
        for(Course course : courses){
            ArrayList<Student> studenti = course.getAllStudents();
            for (Student student : studenti) {
                int kstud=0;
                for(Student student1 : students){
                    if(student1.equals(student)){
                        kstud=1;
                    }
                }
                if(kstud==0){
                    students.add(student);
                }
            }
        }
        return students;
    }

    public ArrayList<Parent> getParents() {
        for(Student student : students){
            if(student.getFather()!=null){
                parents.add(student.getFather());
            }
            if(student.getMother()!=null){
                parents.add(student.getMother());
            }
        }
        return parents;
    }
    public String toString() {
        return "Catalog{" + "courses=" + courses + '}';
    }
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Grade grade) {
        for (Observer observer : observers) {
            if (grade.getStudent().isParent(observer)) {
                observer.update(new Notification("Copilul dumneavoastra a luat nota " + grade.getTotal()));
            }
        }
    }
}
