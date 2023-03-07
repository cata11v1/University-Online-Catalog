import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class Test {
    public static void main(String[] args) {
        Catalog catalog = Catalog.getInstance();
        User Cristina = UserFactory.createUser("Student", "Cristina", "Antonescu");
        User Andrada = UserFactory.createUser("Student", "Andrada", "Dunare");
        User Alin = UserFactory.createUser("Student", "Popescu", "Alin");
        User Madalin = UserFactory.createUser("Student", "Georgescu", "Madalin");
        User mother1 = UserFactory.createUser("Parent", "Popescu", "Madalina");
        User father1 = UserFactory.createUser("Parent", "Georgescu", "Madalin");
        User Adrianprof = UserFactory.createUser("Teacher", "Adrian", "Marian");
        User Calinprof = UserFactory.createUser("Teacher", "Calin", "Pars");
        User Ionutprof = UserFactory.createUser("Teacher", "Ionut", "Barbu");
        User assist1 = UserFactory.createUser("Assistant", "Popa", "Mihai");
        User assist2 = UserFactory.createUser("Assistant", "Dan", "Chirista");
        FullCourse Course1 = (FullCourse) new FullCourse.FullCourseBuilder("Poo").setName("Poo").setTeacher((Teacher) Adrianprof).strategy(new BestTotalScore()).build();
        FullCourse Course2 = (FullCourse) new FullCourse.FullCourseBuilder("So").setName("So").setTeacher((Teacher) Calinprof).strategy(new BestExamScore()).build();
        FullCourse Course3 = (FullCourse) new FullCourse.FullCourseBuilder("Deea").setName("Deea").setTeacher((Teacher) Ionutprof).strategy(new BestPartialScore()).build();
        Group group1 = new Group("0001", (Assistant) assist1, new Comparator<Student>() {
            public int compare(Student unu, Student doi) {
                return unu.getFirstName().compareTo(doi.getFirstName());
            }
        });
        Group group2 = new Group("0002", (Assistant) assist2, new Comparator<Student>() {
            public int compare(Student unu, Student doi) {
                return unu.getFirstName().compareTo(doi.getFirstName());
            }
        });
        Course1.addGroup(group1);
        Course1.addGroup(group2);
        Course1.addStudent("0001", (Student) Alin);
        Course1.addStudent("0001", (Student) Cristina);
        Course1.addStudent("0002", (Student) Madalin);
        Course1.addStudent("0002", (Student) Andrada);
        Course2.addGroup(group1);
        Course2.addGroup(group2);

        Course2.addStudent("0001", (Student) Alin);
        Course2.addStudent("0001", (Student) Cristina);
        Course2.addStudent("0002", (Student) Madalin);
        Course2.addStudent("0002", (Student) Andrada);
        Course3.addGroup(group1);
        Course3.addGroup(group2);
        Course3.addStudent("0001", (Student) Alin);
        Course3.addStudent("0001", (Student) Cristina);
        Course3.addStudent("0002", (Student) Madalin);
        Course3.addStudent("0002", (Student) Andrada);
        ((Student) Alin).setMother((Parent) mother1);
        ((Student) Madalin).setFather((Parent) father1);
        Course1.addAssistant("0001", (Assistant) assist1);
        Course1.addAssistant("0002", (Assistant) assist2);
        Course2.addAssistant("0001", (Assistant) assist1);
        Course2.addAssistant("0002", (Assistant) assist2);
        Course3.addAssistant("0001", (Assistant) assist1);
        Course3.addAssistant("0002", (Assistant) assist2);
        Grade notaAlinPoo = new Grade("Poo", (Student) Alin, 3.0, 4.5);
        Grade notaMadalinPoo = new Grade("Poo", (Student) Madalin, 4.0, 4.0);
        Grade notaCristinaPoo = new Grade("Poo", (Student) Cristina, 5.0, 4.5);
        Grade notaAndradaPoo = new Grade("Poo", (Student) Andrada, 4.9, 4.5);
        Grade notaAlinSo = new Grade("So", (Student) Alin, 5.0, 4.5);
        Grade notaMadalinSo = new Grade("So", (Student) Madalin, 2.0, 5.0);
        Grade notaCristinaSo = new Grade("So", (Student) Cristina, 3.0, 4.5);
        Grade notaAndradaSo = new Grade("So", (Student) Andrada, 4.0, 4.5);
        Grade notaAlinDeea = new Grade("Deea", (Student) Alin, 5.0, 4.5);
        Grade notaMadalinDeea = new Grade("Deea", (Student) Madalin, 4.5, 5.0);
        Grade notaCristinaDeea = new Grade("Deea", (Student) Cristina, 3.0, 4.5);
        Grade notaAndradaDeea = new Grade("Deea", (Student) Andrada, 4.5, 4.5);

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
        System.out.println(catalog.getTeachers());
        System.out.println(catalog.getAssistants());
        System.out.println(catalog.getStudents());
        System.out.println(catalog.getParents());
        JFrame file1 = new JFrame("Welcome to University Online Catalog");
        JButton teach = new JButton("Teacher");
        teach.setBounds(100, 120, 120, 30);
        JButton assis = new JButton("Assistant");
        assis.setBounds(220, 120, 120, 30);
        JButton stud = new JButton("Student");
        stud.setBounds(340, 120, 120, 30);
        JButton pari = new JButton("Parent");
        pari.setBounds(460, 120, 120, 30);
        file1.add(teach);
        file1.add(assis);
        file1.add(stud);
        file1.add(pari);
        file1.setSize(680, 400);
        file1.setLayout(null);
        file1.setVisible(true);
        teach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Teacher Login");
                final JLabel label = new JLabel();
                label.setBounds(20, 150, 200, 50);
                final JPasswordField value = new JPasswordField();
                value.setBounds(100, 75, 200, 30);
                JLabel l1 = new JLabel("Name:");
                l1.setBounds(20, 20, 80, 30);
                JLabel l2 = new JLabel("Password:");
                l2.setBounds(20, 75, 80, 30);
                JButton b = new JButton("Login");
                b.setBounds(100, 120, 80, 30);
                final JTextField text = new JTextField();
                text.setBounds(100, 20, 200, 30);
                f.add(value);
                f.add(l1);
                f.add(label);
                f.add(l2);
                f.add(b);
                f.add(text);
                f.setSize(400, 300);
                f.setLayout(null);
                f.setVisible(true);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<Course> coursesprof = new ArrayList<>();
                        String name = text.getText();
                        int k = 0;
                        for (Teacher teacher : catalog.getTeachers()) {
                            if (name.equals(teacher.toString())) {
                                k = 1;
                            }
                        }
                        if (k == 1) {

                            for(Course course : catalog.courses){
                                if(course.getTeacher().toString().equals(name)){
                                    coursesprof.add(course);
                                }
                            }
                            String data = "Username " + text.getText();
                            data += ", Password: "
                                    + new String(value.getPassword());
                            label.setText(data);
                            DefaultTableModel tableModel = new DefaultTableModel();
                            JTable table = new JTable(tableModel);
                            tableModel.addColumn("Course Name");
                            tableModel.addColumn("Student Name");
                            tableModel.addColumn("Partial Grade");
                            tableModel.addColumn("Exam Grade");
                            tableModel.addColumn("Final Grade");
                            Collections.sort(coursesprof,Collections.reverseOrder());
                            for(Course course : coursesprof){
                                ArrayList<Student> studenti= course.getAllStudents();
                                Collections.sort(studenti, Collections.reverseOrder());
                                for(Student student : studenti){
                                    tableModel.insertRow(tableModel.getRowCount(), new Object[] { course.getName(), student.getName(),
                                            course.getGrade(student).getPartialScore(), course.getGrade(student).getExamScore(), course.getGrade(student).getTotal() });
                                }
                            }
                            JFrame f = new JFrame();
                            f.setLayout(new BorderLayout());
                            f.setSize(800, 350);
                            JPanel best = new JPanel(new BorderLayout());
                            JPanel topBtnPnl = new JPanel(new FlowLayout(FlowLayout.TRAILING));
                            JButton bes=new JButton("Best Students");
                            topBtnPnl.add(bes);
                            best.add(topBtnPnl, BorderLayout.NORTH);
                            f.add(table.getTableHeader(), BorderLayout.NORTH);
                            f.add(table, BorderLayout.CENTER);
                            f.add(best, BorderLayout.SOUTH);
                            f.add(new JScrollPane(table));
                            f.setVisible(true);
                            f.setLayout(null);
                            f.setTitle(name);
                            bes.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JFrame beststu=new JFrame("Best Student");
                                    beststu.setSize(300, 300);
                                    int i=1;
                                    for(Course course : coursesprof){
                                        JLabel label=new JLabel("Best "+course.getName()+" Student is: "+course.getBestStudent().getName());
                                        label.setBounds(0,50*i, 400, 40);
                                        beststu.add(label);
                                        i++;
                                    }
                                    beststu.setVisible(true);
                                }
                            });
                        } else {
                            String data = "Wrong username or password";
                            label.setText(data);
                        }
                    }
                });
            }
        });
        assis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Assistant Login");
                final JLabel label = new JLabel();
                label.setBounds(20, 150, 200, 50);
                final JPasswordField value = new JPasswordField();
                value.setBounds(100, 75, 200, 30);
                JLabel l1 = new JLabel("Name:");
                l1.setBounds(20, 20, 80, 30);
                JLabel l2 = new JLabel("Password:");
                l2.setBounds(20, 75, 80, 30);
                JButton b = new JButton("Login");
                b.setBounds(100, 120, 80, 30);
                final JTextField text = new JTextField();
                text.setBounds(100, 20, 200, 30);
                f.add(value);
                f.add(l1);
                f.add(label);
                f.add(l2);
                f.add(b);
                f.add(text);
                f.setSize(400, 300);
                f.setLayout(null);
                f.setVisible(true);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<Course> coursesassist= new ArrayList<>();
                        String name = text.getText();
                        int k = 0;
                        for (Assistant assistant : catalog.getAssistants()) {
                            if (name.equals(assistant.toString())) {
                                k = 1;
                            }
                        }
                        if (k == 1) {
                            for(Course course : catalog.courses){
                                for(Assistant assistant: course.getAssistants()) {
                                    if (assistant.toString().equals(name)) {
                                        coursesassist.add(course);
                                    }
                                }
                            }
                            String data = "Username " + text.getText();
                            data += ", Password: "
                                    + new String(value.getPassword());
                            label.setText(data);
                            DefaultTableModel tableModel = new DefaultTableModel();
                            JTable table = new JTable(tableModel);
                            tableModel.addColumn("Course Name");
                            tableModel.addColumn("Student Name");
                            tableModel.addColumn("Partial Grade");
                            //tableModel.addColumn("Exam Grade");
                            //tableModel.addColumn("Final Grade");
                            Collections.sort(coursesassist, Collections.reverseOrder());
                            for(Course course : coursesassist){
                                ArrayList<Student> studenti= course.getAllStudents();
                                Collections.sort(studenti, Collections.reverseOrder());
                                for(Student student : studenti){
                                    tableModel.insertRow(tableModel.getRowCount(), new Object[] { course.getName(), student.getName(),
                                            course.getGrade(student).getPartialScore()/*, course.getGrade(student).getExamScore(), course.getGrade(student).getTotal()*/ });
                                }
                            }
                            JFrame f = new JFrame();
                            f.setSize(800, 350);
                            f.add(new JScrollPane(table));
                            f.setTitle(name);
                            f.setVisible(true);
                        } else {
                            String data = "Wrong username or password";
                            label.setText(data);
                        }
                    }
                });
            }
        });
        stud.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Course> coursestud= new ArrayList<>();
                JFrame f = new JFrame("Student Login");
                final JLabel label = new JLabel();
                label.setBounds(20, 150, 200, 50);
                final JPasswordField value = new JPasswordField();
                value.setBounds(100, 75, 200, 30);
                JLabel l1 = new JLabel("Name:");
                l1.setBounds(20, 20, 80, 30);
                JLabel l2 = new JLabel("Password:");
                l2.setBounds(20, 75, 80, 30);
                JButton b = new JButton("Login");
                b.setBounds(100, 120, 80, 30);
                final JTextField text = new JTextField();
                text.setBounds(100, 20, 200, 30);
                f.add(value);
                f.add(l1);
                f.add(label);
                f.add(l2);
                f.add(b);
                f.add(text);
                f.setSize(400, 300);
                f.setLayout(null);
                f.setVisible(true);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = text.getText();
                        int k = 0;
                        for (Student student : catalog.getStudents()) {
                            if (name.equals(student.toString())) {
                                k = 1;
                            }
                        }
                        if (k == 1) {
                            for(Course course : catalog.courses){
                                if(course.getStudent(name)!=null) {
                                    coursestud.add(course);
                                }
                            }
                            String data = "Username " + text.getText();
                            data += ", Password: "
                                    + new String(value.getPassword());
                            label.setText(data);
                            DefaultTableModel tableModel = new DefaultTableModel();
                            JTable table = new JTable(tableModel);
                            tableModel.addColumn("Course");
                            tableModel.addColumn("Partial Grade");
                            tableModel.addColumn("Exam Grade");
                            tableModel.addColumn("Total Grade");
                            Collections.sort(coursestud, Collections.reverseOrder());
                            for(Course course : coursestud){
                                tableModel.insertRow(tableModel.getRowCount(), new Object[] { course.getName(), course.getGrade(course.getStudent(name)).getPartialScore()
                                        , course.getGrade(course.getStudent(name)).getExamScore(), course.getGrade(course.getStudent(name)).getTotal() });
                            }
                            JFrame f = new JFrame();
                            f.setSize(550, 350);
                            f.add(new JScrollPane(table));
                            f.setTitle(name);
                            f.setVisible(true);
                        } else {
                            String data = "Wrong username or password";
                            label.setText(data);
                        }
                    }
                });
            }
        });
        pari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Course> coursestud= new ArrayList<>();
                JFrame f = new JFrame("Parent Login");
                final JLabel label = new JLabel();
                label.setBounds(20, 150, 200, 50);
                final JPasswordField value = new JPasswordField();
                value.setBounds(100, 75, 200, 30);
                JLabel l1 = new JLabel("Name:");
                l1.setBounds(20, 20, 80, 30);
                JLabel l2 = new JLabel("Password:");
                l2.setBounds(20, 75, 80, 30);
                JButton b = new JButton("Login");
                b.setBounds(100, 120, 80, 30);
                final JTextField text = new JTextField();
                text.setBounds(100, 20, 200, 30);
                f.add(value);
                f.add(l1);
                f.add(label);
                f.add(l2);
                f.add(b);
                f.add(text);
                f.setSize(400, 300);
                f.setLayout(null);
                f.setVisible(true);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String name = text.getText();
                        int k = 0;
                        for (Parent parent : catalog.getParents()) {
                            if (name.equals(parent.toString())) {
                                k = 1;
                            }
                        }
                        if (k == 1) {
                            String namest=new String();
                            for(Course course : catalog.courses){
                                ArrayList<Student> studenti =course.getAllStudents();
                                for(Student student: studenti){
                                    if(student.getFather()!=null){
                                        if(name.equals(student.getFather().toString())) {
                                            namest=student.getName();
                                            coursestud.add(course);
                                        }
                                    } else if (student.getMother()!=null) {
                                        if(name.equals(student.getMother().toString())) {
                                            namest=student.getName();
                                            coursestud.add(course);
                                        }
                                    }
                                }
                            }
                            String data = "Username " + text.getText();
                            data += ", Password: "
                                    + new String(value.getPassword());
                            label.setText(data);
                            DefaultTableModel tableModel = new DefaultTableModel();
                            JTable table = new JTable(tableModel);
                            tableModel.addColumn("Course");
                            tableModel.addColumn("Partial Grade");
                            tableModel.addColumn("Exam Grade");
                            tableModel.addColumn("Total Grade");
                            Collections.sort(coursestud, Collections.reverseOrder());
                            for(Course course : coursestud){
                                tableModel.insertRow(tableModel.getRowCount(), new Object[] { course.getName(), course.getGrade(course.getStudent(namest)).getPartialScore()
                                        , course.getGrade(course.getStudent(namest)).getExamScore(), course.getGrade(course.getStudent(namest)).getTotal() });
                            }
                            JFrame f = new JFrame();
                            f.setSize(550, 350);
                            f.add(new JScrollPane(table));
                            f.setTitle(name+" parent of "+namest+" with grades:");
                            f.setVisible(true);
                        } else {
                            String data = "Wrong username or password";
                            label.setText(data);
                        }
                    }
                });
            }
        });
    }
}