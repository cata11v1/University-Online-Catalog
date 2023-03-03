public abstract class User {
    private String firstName, lastName;
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String toString() {
        return firstName + " " + lastName;
    }
    public boolean equals(User user){
        return user.firstName.equals(this.firstName) && user.lastName.equals(this.lastName);

    }
}
