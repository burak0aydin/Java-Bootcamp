public class Lab03 {
    public static void main(String[] args) {
        /*MyPoint p1, p2;
        p1 = new MyPoint();
        p2 = new MyPoint(3, -4);
        System.out.println("Distance between p1 and p2: " + p1.distance(p2));
        System.out.println("Distance between p1 and (-3, -4): " + p1.distance(-3, -4));*/

        /*MyPoint p1 = new MyPoint(0, 4);
        MyPoint p2 = new MyPoint(4, 4);
        MyPoint p3 = new MyPoint(4, 0);

        Triangle2D t = new Triangle2D(p1, p2, p3);
        System.out.println(t.getVerticeCount() + ", " + t.getArea() + ", " + t.getPerimeter());

        MyPoint p4 = new MyPoint(0, 4);
        MyPoint p5 = new MyPoint(4, 4);
        MyPoint p6 = new MyPoint(4, 0);
        MyPoint p7 = new MyPoint(0, 0);
        Quadrilateral2D r = new Quadrilateral2D(p4, p5, p6, p7);
        System.out.println(r.getVerticeCount() + ", " + r.getArea() + ", " + r.getPerimeter());

        System.out.println(r.vertices[0].distance(t.vertices[2]));*/
        

        /*MyShape[] shapes = new MyShape[5];
        for (int i = 0; i < shapes.length; i++) {
            if (i % 2 == 0) {
                shapes[i] = new Triangle2D();
            }
            else {
                shapes[i] = new Quadrilateral2D();
            }
        }

        for (MyShape shape : shapes) {
            //System.out.println("Area: " + shape.getArea() + ", Perimeter: " + shape.getPerimeter() + ", Vertices: " + shape.getVerticeCount() + ", Color: " + shape.getColor() + ", ShapeID: " + shape.getShapeID());
            System.out.println(shape.toString());
        }*/

        /*Triangle2D t = new Triangle2D(new MyPoint(0, 0), new MyPoint(0, 5), new MyPoint(5, 0));
        Quadrilateral2D q = new Quadrilateral2D(new MyPoint(6, 0), new MyPoint(11, 0), new MyPoint(11, 5), new MyPoint(6, 5));

        MyPoint closestPoint = t.getClosestPoint(q);
        System.out.println("Closest point to Triangle: (" + closestPoint.getX() + ", " + closestPoint.getY() + ")");
        */
    }
}

class MyPoint {
    double x, y;

    MyPoint() {
        this(0, 0);
    }

    MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }

    double distance(MyPoint point) {
        // Automatically puts this keyword before the instance method
        double dist_x = Math.pow(getX() - point.getX(), 2);
        double dist_y = Math.pow(getY() - point.getY(), 2);

        return Math.sqrt(dist_x + dist_y);
    }

    double distance(double x, double y) {
        return distance(new MyPoint(x, y));
    }
}

class SimpleTriangle2D {
    MyPoint p1, p2, p3;

    SimpleTriangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    SimpleTriangle2D() {
        this(new MyPoint(0, 0), new MyPoint(4, 0), new MyPoint(0, 3));
    }

    MyPoint getPoint1() {
        return this.p1;
    }

    MyPoint getPoint2() {
        return this.p2;
    }

    MyPoint getPoint3() {
        return this.p3;
    }

    void setPoint1(MyPoint point) {
        this.p1 = point;
    }

    void setPoint1(double x, double y) {
        setPoint1(new MyPoint(x, y));
    }

    void setPoint2(MyPoint point) {
        this.p2 = point;
    }

    void setPoint2(double x, double y) {
        setPoint2(new MyPoint(x, y));
    }

    void setPoint3(MyPoint point) {
        this.p3 = point;
    }

    void setPoint3(double x, double y) {
        setPoint3(new MyPoint(x, y));
    }

    double getPerimeter() {
        return this.getPoint1().distance(this.getPoint2()) + this.getPoint2().distance(this.getPoint3()) + this.getPoint3().distance(this.getPoint1());
    }

    double getArea() {
        double s = getPerimeter() / 2;
        
        double side_1 = getPoint1().distance(getPoint2());
        double side_2 = getPoint2().distance(getPoint3());
        double side_3 = getPoint3().distance(getPoint1());

        return Math.sqrt(s * (s - side_1) * (s - side_2) * (s - side_3));
    }
}

class MyColor {
    static final String[] COLORS = {"RED", "GREEN", "YELLOW", "BLUE"};

    MyColor(int colorIndex) {
        colorName = COLORS[colorIndex];
    }

    String colorName;
}

abstract class MyShape {
    protected MyPoint[] vertices;
    private MyColor color;
    private int verticeCount = 0;
    private static int shapesCount = 0;
    private int shapeID;

    protected MyShape(MyPoint... vertices) {
        this.vertices = new MyPoint[vertices.length];

        for (MyPoint point : vertices) {
            this.vertices[verticeCount++] = point;
        }

        shapeID = shapesCount++;
        setColor(new MyColor(getShapeID() % MyColor.COLORS.length));
    }

    int getShapeID() {
        return shapeID;
    }

    static int getShapesCount() {
        return shapesCount;
    }

    int getVerticeCount() {
        return verticeCount;
    }

    double getPerimeter() {
        double perimeter = 0;
        for (int i = 1; i < verticeCount; i++) {
            perimeter += vertices[i-1].distance(vertices[i]);
        }
        perimeter += vertices[verticeCount - 1].distance(vertices[0]);
        return perimeter;
    }

    MyPoint getClosestPoint(MyShape shape) {
        double distance = Double.MAX_VALUE;
        int s = 0;
        for (int i = 0; i < this.vertices.length; i++) {
            for (int j = 0; j < shape.vertices.length; j++) {
                double distBetweenTwo = shape.vertices[j].distance(this.vertices[i]);
                if (distBetweenTwo < distance) {
                    s = j;
                    distance = distBetweenTwo;
                }
            }
        }
        return shape.vertices[s];
    }

    abstract double getArea();

    String getColor() {
        return color.colorName;
    }

    private void setColor(MyColor color) {
        this.color = color;
    }

    MyPoint getPoint(int index) {
        return this.vertices[index];
    }

    void setPoint(int index, MyPoint point) {
        vertices[index] = point;
    }

    void setPoint(int index, double x, double y) {
        setPoint(index, new MyPoint(x, y));
    }

    @Override
    public String toString() {
        return "Area: " + this.getArea() + ", Perimeter: " + this.getPerimeter() + ", Vertices: " + this.getVerticeCount() + ", Color: " + this.getColor() + ", ShapeID: " + this.getShapeID();
    }
}

class Triangle2D extends MyShape {
    Triangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        super(p1, p2, p3);
    }

    Triangle2D() {
        this(new MyPoint(0, 0), new MyPoint(4, 0), new MyPoint(0, 3));
    }

    @Override
    double getArea() {
        double s = getPerimeter() / 2;
        
        double side_1 = vertices[0].distance(vertices[1]);
        double side_2 = vertices[1].distance(vertices[2]);
        double side_3 = vertices[2].distance(vertices[0]);

        return Math.sqrt(s * (s - side_1) * (s - side_2) * (s - side_3));
    }

    @Override
    public String toString() {
        return "Triangle= " + super.toString();
    }
}

class Quadrilateral2D extends MyShape {
    Quadrilateral2D(MyPoint p1, MyPoint p2, MyPoint p3, MyPoint p4) {
        super(p1, p2, p3, p4);
    }

    Quadrilateral2D() {
        this(new MyPoint(0, 4), new MyPoint(4, 4), new MyPoint(4, 0), new MyPoint(0, 0));
    }

    @Override
    double getArea() {
        double first = new Triangle2D(vertices[0], vertices[1], vertices[2]).getArea();
        double second = new Triangle2D(vertices[2], vertices[3], vertices[0]).getArea();
        return first + second;
    }

    @Override
    public String toString() {
        return "Quadrilateral= " + super.toString();
    }
}