import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter dimensions of matrix to create: ");
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();
        int n = scanner.nextInt();

        double[][] matrix = makeMatrix(m, n);

        System.out.println();
        printMatrix(matrix);
        System.out.println();

        Locate location1 = locateMax(matrix);
        System.out.println("maxValue: " + location1.maxValue + " is at (" + location1.row + ", " + location1.column + ")");

        System.out.println("-------------------------------------------------------");
        matrix = getMatrix(m, n);

        System.out.println();
        printMatrix(matrix);
        System.out.println();

        Locate location2 = locateMax(matrix);
        System.out.println("maxValue: " + location2.maxValue + " is at (" + location2.row + ", " + location2.column + ")");

    }

    public static Locate locateMax(double[][] matrix) {
        Locate location = new Locate();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (location.maxValue < matrix[i][j]) {
                    location.setFields(i, j, matrix[i][j]);
                }
            }
        }

        return location;
    }

    public static double[][] makeMatrix(int rows, int columns) {
        Random rand = new Random();
        double[][] matrix = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = rand.nextDouble(-5, 5);
            }
        }

        return matrix;
    }

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(String.format("%8.2f", matrix[i][j]));
            }
            System.out.println();
        }
    }

    public static double[][] getMatrix(int rows, int columns) {
        Scanner scanner = new Scanner(System.in);
        double[][] matrix = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            String s = String.valueOf(i + 1);

            int betweenCheck = (i + 1) % 100;
            if (10 < betweenCheck && betweenCheck < 14) {
                s += "th";
            }
            else {
                switch ((i + 1) % 10) {
                    case 1:
                        s += "st";
                        break;
                    
                    case 2:
                        s += "nd";
                        break;
                    
                    case 3:
                        s += "rd";
                        break;

                    default:
                        s += "th";
                        break;
                }
            }

            s += " row: ";
            System.out.print(s);
            
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        return matrix;
    }

}

class Locate {
    int row, column;
    double maxValue = Double.MIN_VALUE;

    void setFields(int row, int column, double maxValue) {
        this.row = row;
        this.column = column;
        this.maxValue = maxValue;
    }
}