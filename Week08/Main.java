import java.util.*;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Joe", 17);
        Student s2 = new Student("Batuhan", 22);
        Student s3 = new Student("Jessica", 21);
        Student s4 = new Student("Ayse", 22);

        Student[] students = {s1, s2, s3, s4};

        students = add(students);
        var studentsList = convert(students);
        var studentsArray = convertBack(studentsList);
        
        for (int i = 0; i < students.length; i++) {
            System.out.println(studentsArray[i]);
        }

        System.out.println("----------------------------------------------");

        /*---
        Print the student list with an iterator, as long as next list has next
        */
        Iterator<Student> iter = studentsList.iterator();
        iter.forEachRemaining(System.out::println);

        System.out.println("----------------------------------------------");

        Collections.sort(studentsList);

        iter = studentsList.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }

        System.out.println("----------------------------------------------");

        Student.compare(studentsList);

        for (Student student : studentsList) {
            System.out.println(student);
        }
        
    }

    /*----
    Write a method that adds 2 new students to an array of students
    and returns an array with both old and new students included
    */
    static Student[] add(Student[] array) {
        Student[] newArray = new Student[array.length + 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        int c = array.length;
        newArray[c++] = new Student("Doruk", 26);
        newArray[c] = new Student("Pinar", 25);

        return newArray;
    }

    /*---
    Write a method that converts the array of students to list of students
    and returns it
    */
    static List<Student> convert(Student[] array) {
        return Arrays.asList(array);
    }

    /*---
    Write a method that takes a list of students and converts the list of students back to array of students
    */
    static Student[] convertBack(List<Student> list) {
        return list.toArray(new Student[list.size()]);
    }
}

/* ---
    Write an immutable Student class with (String) name, (int) id, (int) age
    data fields
*/
class Student implements Comparable<Student> {
    private String name;
    private int id;
    private int age;
    private static int count = 0;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.id = ++count;
    }

    String getName() { return this.name; }
    int getId() { return this.id; }
    int getAge() { return this.age; }

    /*---
    Use collections framework's sort method to sort the students according to their age
    in decending order
    */
    @Override
    public int compareTo(Student s) {
        
        return s.getAge() - this.getAge();
    }

    static void compare(List<Student> students) {
        Collections.sort(students, new StudentComparator());
    }

    @Override
    public String toString() {
        return "Student " + name + " (" + getId() + ") " + getAge();
    }

}

/*---
    Write a custom comparator for student class according to 
    length of their name
*/
class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {

        if (o1.getName().length() < o2.getName().length()) {
            return -1;
        }
        else if (o1.getAge() < o2.getAge()) {
            return 1;
        }
        return 0;
    }
}