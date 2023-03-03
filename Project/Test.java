import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        Catalog catalog = Catalog.getInstance();
        User Cristina= UserFactory.createUser("Student", "Cristina", "Antonescu");
        User Andrada= UserFactory.createUser("Student", "Andrada", "Dunare");
        User Alin =UserFactory.createUser("Student", "Popescu", "Alin");
        User Madalin = UserFactory.createUser("Student", "Georgescu", "Madalin");
        User mother1= UserFactory.createUser("Parent", "Popescu", "Madalina");
        User father1= UserFactory.createUser("Parent", "Georgescu", "Madalin");
        User Adrianprof=UserFactory.createUser("Teacher", "Adrian", "Marian");
        User Calinprof=UserFactory.createUser("Teacher", "Calin", "Pars");
        User Ionutprof=UserFactory.createUser("Teacher", "Ionut", "Barbu");
        User assist1=UserFactory.createUser("Assistant", "Popa", "Mihai");
        User assist2=UserFactory.createUser("Assistant", "Dan", "Chirista");
        FullCourse Course1=(FullCourse) new FullCourse.FullCourseBuilder("Poo").setName("Poo").setTeacher((Teacher)Adrianprof).strategy(new BestTotalScore()).build();
        FullCourse Course2=(FullCourse) new FullCourse.FullCourseBuilder("So").setName("So").setTeacher((Teacher)Calinprof).strategy(new BestExamScore()).build();
        FullCourse Course3=(FullCourse) new FullCourse.FullCourseBuilder("Deea").setName("Deea").setTeacher((Teacher)Ionutprof).strategy(new BestPartialScore()).build();
        Group group1 =new Group("0001", (Assistant) assist1, new Comparator<Student>()
        {
            public int compare(Student unu, Student doi){
                return unu.getFirstName().compareTo(doi.getFirstName());
            }
        });
        Group group2 =new Group("0002", (Assistant) assist2, new Comparator<Student>()
        {
            public int compare(Student unu, Student doi){
                return unu.getFirstName().compareTo(doi.getFirstName());
            }
        });
        Course1.addGroup(group1);
        Course1.addGroup(group2);
        Course1.addStudent("0001", (Student)Alin);
        Course1.addStudent("0001", (Student)Cristina);
        Course1.addStudent("0002", (Student)Madalin);
        Course1.addStudent("0002", (Student)Andrada);
        Course2.addGroup(group1);
        Course2.addGroup(group2);

        Course2.addStudent("0001", (Student)Alin);
        Course2.addStudent("0001", (Student)Cristina);
        Course2.addStudent("0002", (Student)Madalin);
        Course2.addStudent("0002", (Student)Andrada);
        Course3.addGroup(group1);
        Course3.addGroup(group2);
        Course3.addStudent("0001", (Student)Alin);
        Course3.addStudent("0001", (Student)Cristina);
        Course3.addStudent("0002", (Student)Madalin);
        Course3.addStudent("0002", (Student)Andrada);
        ((Student) Alin).setMother((Parent)mother1);
        ((Student) Madalin).setFather((Parent)father1);
        Course1.addAssistant("0001", (Assistant)assist1);
        Course1.addAssistant("0002", (Assistant)assist2);
        Course2.addAssistant("0001", (Assistant)assist1);
        Course2.addAssistant("0002", (Assistant)assist2);
        Course3.addAssistant("0001", (Assistant)assist1);
        Course3.addAssistant("0002", (Assistant)assist2);
        Grade notaAlinPoo= new Grade("Poo", (Student) Alin, 3.0, 4.5 );
        Grade notaMadalinPoo= new Grade("Poo", (Student) Madalin, 4.0, 4.0 );
        Grade notaCristinaPoo= new Grade("Poo", (Student) Cristina, 5.0, 4.5 );
        Grade notaAndradaPoo= new Grade("Poo", (Student) Andrada, 4.9, 4.5 );
        Grade notaAlinSo= new Grade("So", (Student) Alin, 5.0, 4.5 );
        Grade notaMadalinSo= new Grade("So", (Student) Madalin, 2.0, 5.0 );
        Grade notaCristinaSo= new Grade("So", (Student) Cristina, 3.0, 4.5 );
        Grade notaAndradaSo= new Grade("So", (Student) Andrada, 4.0, 4.5 );
        Grade notaAlinDeea= new Grade("Deea", (Student) Alin, 5.0, 4.5 );
        Grade notaMadalinDeea= new Grade("Deea", (Student) Madalin, 4.5, 5.0 );
        Grade notaCristinaDeea= new Grade("Deea", (Student) Cristina, 3.0, 4.5 );
        Grade notaAndradaDeea= new Grade("Deea", (Student) Andrada, 4.5, 4.5 );
        Course1.addGrade(notaAlinPoo);
        Course1.addGrade(notaMadalinPoo);
        Course1.addGrade(notaCristinaPoo);
        Course1.addGrade(notaAndradaPoo);
        Course2.addGrade(notaAlinSo);
        Course2.addGrade(notaMadalinSo);
        Course2.addGrade(notaAndradaSo);
        Course2.addGrade(notaCristinaSo);
        Course3.addGrade(notaAlinDeea);
        Course3.addGrade(notaMadalinDeea);
        Course3.addGrade(notaAndradaDeea);
        Course3.addGrade(notaCristinaDeea);
        catalog.addCourse(Course1);
        catalog.addCourse(Course2);
        catalog.addCourse(Course3);
        catalog.addObserver((Parent) mother1);
        catalog.addObserver((Parent) father1);
        System.out.println("Pentru mama lui Alin");
        catalog.notifyObservers(notaAlinPoo);
        System.out.println("Pentru tatal lui Madalin");
        catalog.notifyObservers(notaMadalinDeea);
        System.out.println("Best Student(Curs1 besttotal):" + Course1.getBestStudent());
        System.out.println("Best Student(Curs2 bestexam):" + Course2.getBestStudent());
        System.out.println("Best Student(Curs3 bestpartial):" + Course3.getBestStudent());
        System.out.println(catalog);
    }
}
