public class Lab02 {
    public static void main(String[] args) {
        MyPoint p1 = new MyPoint();
        MyPoint p2 = new MyPoint(0, 4);
        MyPoint p3 = new MyPoint(-3 , 0);
        //System.out.println("Distance between p1 and p2: " + p1.distance(p2));
        //System.out.println("Distance between p1 and p2: " + p1.distance(3, 4));

        SimpleTriangle2D t = new SimpleTriangle2D(p1, p2, p3);
        System.out.println("Area: " + t.getArea() + ", Perimeter: " + t.getPerimeter());
    }
}

class MyPoint {
    double x, y;

    double getX() {
        return x;
    }
    double getY() {
        return y;
    }

    MyPoint() {
        this(0, 0);
    }

    MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double distance(MyPoint point) {
        double dist_x = Math.pow(this.getX() - point.getX(), 2);
        double dist_y = Math.pow(getY() - point.getY(), 2);

        return Math.sqrt(dist_x + dist_y);
    }

    double distance(double x, double y) {
        return distance(new MyPoint(x, y));
    }
}

class SimpleTriangle2D {
    MyPoint p1, p2, p3;

    SimpleTriangle2D() {
        this(new MyPoint(0, 0), new MyPoint(0, -3), new MyPoint(4, 0));
    }

    SimpleTriangle2D(MyPoint p1, MyPoint p2, MyPoint p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
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

    void setPoint1(MyPoint p1) {
        this.p1 = p1;
    }

    void setPoint2(MyPoint p2) {
        this.p2 = p2;
    }

    void setPoint3(MyPoint p3) {
        this.p3 = p3;
    }

    void setPoint1(double x, double y) {
        setPoint1(new MyPoint(x, y));
    }

    void setPoint2(double x, double y) {
        setPoint2(new MyPoint(x, y));
    }

    void setPoint3(double x, double y) {
        setPoint3(new MyPoint(x, y));
    }

    double getPerimeter() {
        return this.getPoint1().distance(this.getPoint2()) + this.getPoint2().distance(this.getPoint3()) + this.getPoint3().distance(this.getPoint1());
    }

    double getArea() {
        double s = getPerimeter() / 2;

        double side_1 = getPoint1().distance(this.getPoint2());
        double side_2 = getPoint2().distance(this.getPoint3());
        double side_3 = getPoint3().distance(this.getPoint1());

        return Math.sqrt(s * (s - side_1) * (s - side_2) * (s - side_3));
    }
}
