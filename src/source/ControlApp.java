package source;

import java.util.Scanner;

public class ControlApp {
    public static void main(String[] args) {
        StudentManagement studentManager = new StudentManagement(1000);  
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("Chosse an option below:");
            System.out.println("1. Add student");
            System.out.println("2. Print student information");
            System.out.println("3. Delete student information");
            System.out.println("4. Update student information");
            System.out.println("5. Sort student by mark");
            System.out.println("6. Search student by Id");
            System.out.println("7. Exit");
            System.out.print("Your options is: ");
            int option = scanner.nextInt();
            scanner.nextLine();  

            switch (option) {
                case 1:
                    studentManager.addStudent();
                    break;
                case 2:
                    studentManager.printAllStudents();
                    break;
                case 3:
                    studentManager.removeStudent();
                    break;
                case 4:
                    studentManager.updateStudent();
                    break;
                case 5:
                    studentManager.sortStudentsByMarks();
                    break;
                case 6:
                    studentManager.searchStudentById();
                    break;
                case 7:
                    System.out.println("Exit the program!");
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Option not valid!");
            }

            if (keepRunning) {
                System.out.print("Do you wanna do any options else ? (Y/N): ");
                String continueOption = scanner.nextLine();
                if (continueOption.equalsIgnoreCase("n")) {
                    keepRunning = false;
                    System.out.println("Exit the program!");
                }
            }
        }
        scanner.close();  
    }
}