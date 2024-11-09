package source;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentManagement {
    private Student[] students;
    private int studentCount;

    Scanner scanner = new Scanner(System.in);

    public StudentManagement(int maxStudents) {
        students = new Student[maxStudents];
        studentCount = 0;
    }

    public void addStudent() {
        while (true) {
            try {
                System.out.println("Enter student information below!");
                System.out.print("Enter student Id (or enter 0 to stop) : ");
                int Id = scanner.nextInt();
                if (Id == 0) {
                    break;
                }

                // Kiểm tra ID phải bằng ID cuối cùng + 1
                if (studentCount > 0) {
                    int expectedId = students[studentCount - 1].getId() + 1;
                    if (Id != expectedId) {
                        System.out.println("ID must be" + expectedId + " - Please enter again!");
                        continue;
                    }
                }
                // tao bien scanner tai day de ngan cach int va String
                scanner.nextLine(); 
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();

                // Kiểm tra tên không chứa số
                if (!name.matches("[a-zA-Z\\s]+")) {
                    System.out.println("Student name must not be a number, pls enter again!");
                    continue;
                }

                System.out.print("Enter student mark: ");
                double score = scanner.nextDouble();
                scanner.nextLine();  

                if (score < 0 || score > 10) {
                    System.out.println("Student mark is not valid, it must be from 1 to 10");
                    continue;
                }

                if (studentCount < students.length) {
                    students[studentCount++] = new Student(Id, name, score);
                    System.out.println("Student added successfully!");
                } else {
                    System.out.println("Can't add student, the arr is full!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Err, pls enter valid value!");
                scanner.nextLine();  
        }  }
    }





    public void printAllStudents() {
        if (studentCount == 0) {
            System.out.println("There is not student!");
            return;
        }
        for (int i = 0; i < studentCount; i++) {
            students[i].printInfo();
        }
    }

    public void removeStudent() {
        try {
            System.out.println("Student list before remove:");
            for (int i = 0; i < studentCount; i++) {
                students[i].printInfo();
            }

            System.out.print("Enter student Id need to remove: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            boolean found = false;
            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId() == id) {
                    // Dịch chuyển các phần tử
                    for (int j = i; j < studentCount - 1; j++) {
                        students[j] = students[j + 1];
                    }
                    students[--studentCount] = null; 
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Student deleted successfully with Id: " + id);
            } else {
                System.out.println("There is not student with Id: " + id);
            }

            System.out.println("\nStudent list after remove: ");
            for (int i = 0; i < studentCount; i++) {
                students[i].printInfo();
            }
        } catch (InputMismatchException e) {
            System.out.println("Err, pls enter a valid number! ");
            scanner.nextLine();  
        }
    }

    public void updateStudent() {
        try {
            System.out.print("Enter student Id need to edit: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId() == id) {
                    System.out.print("New student name: ");
                    String newName = scanner.nextLine();
                    System.out.print("New student mark: ");
                    double newMarks = scanner.nextDouble();
                    scanner.nextLine();

                    if (newMarks < 0 || newMarks > 10) {
                        System.out.println("Student mark is not valid, pls enter from 1 to 10!");
                        return;
                    }
                    students[i].setName(newName);
                    students[i].setMarks(newMarks);
                    System.out.println("Student mark update successfully.");
                    return;
                }
            }
            System.out.println("There is not student with Id: " + id);
        } catch (InputMismatchException e) {
            System.out.println("Err, pls enter a valid number!");
            scanner.nextLine();  
        }

    }

    public void sortStudentsByMarks() {
        System.out.println("The student list before sort: ");
        for (int i = 0; i < studentCount; i++) {
            students[i].printInfo();
        }
        // Sắp xếp sinh viên theo điểm
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (students[j].getMarks() > students[j + 1].getMarks()) {
                    // Hoán đổi vị trí sinh viên
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        // Cập nhật lại ID  
        for (int i = 0; i < studentCount; i++) {
            students[i].setId(i + 1);
        }
        System.out.println("\nThe student list after sort and update student Id:");
        for (int i = 0; i < studentCount; i++) {
            students[i].printInfo();
        }

        System.out.println("Da xap sep sinh vien theo diem tang dan va cap nhap lai Id");
    }

    public void searchStudentById() {
        try {
            System.out.print("Enter student Id need to search: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < studentCount; i++) {
                if (students[i].getId() == id) {
                    students[i].printInfo();
                    return;
                }
            }
            System.out.println("There is not student with Id: " + id);
        } catch (InputMismatchException e) {
            System.out.println("Err, pls enter a valid number!");
            scanner.nextLine();  
        }
    }

   
    public void closeScanner() {
        scanner.close();
    }
}
