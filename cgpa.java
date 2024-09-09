import java.util.Scanner;

public class CGPACalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] marks = new double[4];
        double[] gradePoints = new double[4];
        double sum = 0.0;

        // Input marks for four subjects
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextDouble();
            gradePoints[i] = marks[i] / 10; // Assuming grade points are marks divided by 10
            sum += gradePoints[i];
        }

        // Calculate CGPA
        double cgpa = sum / 4;
        System.out.println("CGPA: " + cgpa);
        System.out.println("Percentage: " + (cgpa * 9.5));

        scanner.close();
    }
}
