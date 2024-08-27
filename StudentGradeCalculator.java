import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String[] subjects = new String[numSubjects];
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        // Input subject names and marks
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter the name of subject " + (i + 1) + ": ");
            subjects[i] = scanner.nextLine();

            System.out.print("Enter marks for " + subjects[i] + " (out of 100): ");
            marks[i] = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Validate input
            while (marks[i] < 0 || marks[i] > 100) {
                System.out.print("Invalid marks. Enter marks for " + subjects[i] + " (out of 100): ");
                marks[i] = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            }

            totalMarks += marks[i];
        }

        // Calculate average percentage
        double averagePercentage = (double) totalMarks / numSubjects;

        // Determine grade based on average percentage
        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Display results in tabular format
        System.out.println("\nSubject-wise Marks:");
        System.out.println("---------------------------------------------------");
        System.out.printf("%-20s %-10s%n", "Subject", "Marks");
        System.out.println("---------------------------------------------------");
        for (int i = 0; i < numSubjects; i++) {
            System.out.printf("%-20s %-10d%n", subjects[i], marks[i]);
        }
        System.out.println("---------------------------------------------------");

        // Display total marks, average percentage, and grade
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
