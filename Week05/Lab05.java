import java.util.Random;
import java.util.Scanner;

public class Lab05 {
    public static void main(String[] args) {
        /*
        int[] arrayOfInts = new int[100];
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < arrayOfInts.length; i++) {
            arrayOfInts[i] = rand.nextInt(100);
        }

        try {
            while (true) {
                int index = scanner.nextInt();
                if (index < 0 || index >= arrayOfInts.length) {
                    throw new ArrayIndexOutOfBoundsException("Out of Bounds");
                }

                System.out.println(arrayOfInts[index]);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        */
        Triangle[] arrayOfTriangles = new Triangle[3];

        try {
            int counter = 0;
            Triangle triangle = new Triangle(2.0, 2.5, 4.0);
            arrayOfTriangles[counter++] = triangle;

            triangle = new Triangle(2.0, 3.0, 4.0);
            arrayOfTriangles[counter++] = triangle;

            triangle = new Triangle(3.0, 4.0, 5.0);
            arrayOfTriangles[counter++] = triangle;

            triangle = new Triangle(3.0, 3.0, 6.0); // change one of the 3.0 to 3.5
            arrayOfTriangles[counter++] = triangle;
        }
        catch (IllegalTriangleException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            printTriangles(arrayOfTriangles);
        }
        /*catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error handler for IndexOutOfBoundsException");
        }*/
        finally {
            System.out.println("Number of triangles instantiated: " + Triangle.getTriangleCount());
        }

        System.out.println("Exception handled, continue to execute the program");
        
    }

    static void printTriangles(Triangle[] triangles) {
        for (int i = 0; i < triangles.length; i++) {
            System.out.println(triangles[i].toString());
        }
    }
}

class Triangle {
    private double sideFirst, sideSecond, sideThird;
    private static int TRIANGLE_COUNT = 0;

    Triangle(double sideFirst, double sideSecond, double sideThird) throws IllegalTriangleException {
        this.sideFirst = sideFirst;
        this.sideSecond = sideSecond;
        this.sideThird = sideThird;

        isLegalTriangle();
        TRIANGLE_COUNT++;
    }

    Triangle() throws IllegalTriangleException {
        this(1.0, 1.0, 1.0);
    }

    private void isLegalTriangle() throws IllegalTriangleException {
        
        if (getSideFirst() + getSideSecond() <= getSideThird()) {
            throw new IllegalTriangleException(getSideThird(), getSideFirst(), getSideSecond());
        }

        if (getSideFirst() + getSideThird() <= getSideSecond()) {
            throw new IllegalTriangleException(getSideSecond(), getSideFirst(), getSideThird());
        }

        if (getSideSecond() + getSideThird() <= getSideFirst()) {
            throw new IllegalTriangleException(getSideFirst(), getSideSecond(), getSideThird());
        }
    }

    public static int getTriangleCount() {
        return TRIANGLE_COUNT;
    }

    double getSideFirst() {
        return this.sideFirst;
    }

    double getSideSecond() {
        return this.sideSecond;
    }

    double getSideThird() {
        return this.sideThird;
    }

    double getPerimeter() {
        return this.getSideFirst() + this.getSideSecond() + this.getSideThird();
    }

    double getArea() {
        double s = getPerimeter() / 2;

        return Math.sqrt(s * (s - this.getSideFirst()) * (s - this.getSideSecond()) * (s - this.getSideThird()));
    }

    @Override
    public String toString() {
        return "Triangle: First side = " + this.getSideFirst() + ", second side = " + this.getSideSecond() + ", third side = " + this.getSideThird();
    }
}

class IllegalTriangleException extends Exception {
    IllegalTriangleException(double side1, double side2, double side3) {
        super("Triangle with one of the side length " + side1 + " cannot be bigger or equal than sum of other two sides with lengths " + side2 + " and " + side3);
    }
}

