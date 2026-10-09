package Day_5;

class StudentProfile {

    String name;
    int studentId;
    double score;

    StudentProfile(String name, int studentId, double score) {
        this.name = name;
        this.studentId = studentId;
        this.score = score;
    }

    StudentProfile(String name, int studentId) {
        this.name = name;
        this.studentId = studentId;
        this.score = 0.0;
    }

    char getGrade() {

        if (score >= 90) {
            return 'A';
        } else if (score >= 75) {
            return 'B';
        } else if (score >= 50) {
            return 'C';
        } else {
            return 'F';
        }
    }

    void displayReport() {
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentId);
        System.out.println("Score: " + score);
        System.out.println("Grade: " + getGrade());
    }
}

public class Problem2 {

    public static void main(String[] args) {
        StudentProfile student1 =  new StudentProfile("Janhavi", 101, 82.5);
        StudentProfile student2 =  new StudentProfile("Rahul", 102);

        System.out.println("Student 1 Report");
        student1.displayReport();

        System.out.println();

        System.out.println("Student 2 Report");
        student2.displayReport();
    }
}