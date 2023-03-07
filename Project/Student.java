public class Student extends User implements Comparable<Student>{
    private Parent mother;
    private Parent father;
    private String firstName;
    private String lastName;
    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        this.firstName=firstName;
        this.lastName=lastName;
    }
    public void setMother(Parent mother) {
        this.mother = mother;
    }
    public void setFather(Parent father) {
        this.father = father;
    }
    public Parent getMother(){
        return mother;
    }
    public Parent getFather(){
        return father;
    }
    public int compareTo(Student stud) {
        return stud.getName().compareTo(this.getName());
    }
    public boolean isParent(Observer observer) {
        if (observer.equals(father) || observer.equals(mother)) {
            return true;
        }
        return false;
    }
    public String getName() {
        return super.toString();
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

}
