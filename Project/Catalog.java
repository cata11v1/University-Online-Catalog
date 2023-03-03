import java.util.ArrayList;

public class Catalog {
    public static Catalog instance;
    public ArrayList<Course> courses;
    private ArrayList<Observer> observers;

    public Catalog() {

        courses = new ArrayList<>();
        observers = new ArrayList<>();
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
