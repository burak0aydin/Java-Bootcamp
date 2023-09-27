import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AcademicPersonel personel = new AcademicPersonel("Berkecan", "Ercükler");
        Course course = new Course("Televizyon Programlama");

        personel.addCourse(course);

        Student student = new Student("John", "Doe");
        GraduateStudent gradStudent = new GraduateStudent("Ali", "Veli");

        personel.addGraduateStudents(gradStudent);

        student.addCourse(course);
        gradStudent.addCourse(course);

        personel.grading(course);
        course.printGrades();
    }
}

class Person {
    String name;
    String surname;

    Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String toString() {
        return this.name + " " + this.surname;
    }
}

class AcademicPersonel extends Person {
    static ArrayList<AcademicPersonel> personels = new ArrayList<AcademicPersonel>();
    ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<GraduateStudent> supervisedStudents = new ArrayList<GraduateStudent>();
    private static final String[] titles = {"RA", "PhD", "PostDoc", "Assoc", "Prof"};
    private int title = 0;
    
    AcademicPersonel(String name, String surname) {
        super(name, surname);
        personels.add(this);
    }

    AcademicPersonel(String name, String surname, int title) {
        this(name, surname);

        if (title >= 0 && title <= 4) {
            this.title = title;
        }
    }

    public String getTitle() {
        return titles[title];
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.teacher = this;
    }

    public void addCourse(ArrayList<Course> courses) {
        for (Course course : courses) {
            this.addCourse(course);
        }
    }

    public void addGraduateStudents(GraduateStudent student) {
        supervisedStudents.add(student);
        student.advisor = this;
    }

    public void grading(Course course) {
        if (courses.contains(course)) {
            Random rand = new Random();

            for (Grade grade : course.grades) {
                int midtermGrade = rand.nextInt(0, 100);
                int finalGrade = rand.nextInt(0, 100);

                if (grade.student instanceof GraduateStudent) {
                    midtermGrade += 15;
                    finalGrade += 15;
                }
                grade.gradeMıdterm(midtermGrade);
                grade.gradeFinal(finalGrade);
            }
        }
    }

    @Override
    public String toString() {
        return this.getTitle() + " " + super.toString();
    }

}

class Student extends Person {
    static ArrayList<Student> students = new ArrayList<Student>();
    String studentNo;
    ArrayList<Course> courses = new ArrayList<Course>();

    Student(String name, String surname) {
        super(name, surname);
        int stdNo = 20220000 + new Random().nextInt(20230000);
        studentNo = String.valueOf(stdNo);

        students.add(this);
    }

    void addCourse(Course course) {
        this.courses.add(course);
        course.students.add(this);
        course.grades.add(new Grade(this));
    }

    void addCourse(ArrayList<Course> courses) {
        for (Course course : courses) {
            this.addCourse(course);
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " " + this.studentNo + " " + super.toString();
    }
}

class GraduateStudent extends Student {
    static ArrayList<GraduateStudent> graduateStudents = new ArrayList<GraduateStudent>();
    AcademicPersonel advisor;
 
    GraduateStudent(String name, String surname) {
        super(name, surname);

        graduateStudents.add(this);
    }

    @Override
    public String toString() {
        return super.toString() + " Advisor: " + this.advisor + " ";
    }

}

class Course {
    static ArrayList<Course> courses = new ArrayList<Course>();
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<Grade> grades = new ArrayList<Grade>();

    String courseName;
    AcademicPersonel teacher;

    Course(String courseName) {
        this.courseName = courseName;

        courses.add(this);
    }
    
    public void printGrades() {
        for (Grade grade : grades) {
            System.out.println(grade.toString());
        }
    }
}

class Grade {
    int midtermGrade;
    int finalGrade;
    Student student;

    Grade(Student student) {
        this.student = student;
    }

    void gradeMıdterm(int grade) {
        this.midtermGrade = grade;
    }

    void gradeFinal(int grade) {
        this.finalGrade = grade;
    }

    double getGrade() {
        return (this.midtermGrade * 0.4) + (this.finalGrade * 0.6);
    }

    public String toString() {
        return student.toString() + " Success: " + getGrade();
    }
}