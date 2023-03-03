public class Student extends User{
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
    public boolean isParent(Observer observer) {
        if (observer.equals(father) || observer.equals(mother)) {
            return true;
        }
        return false;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }

}
