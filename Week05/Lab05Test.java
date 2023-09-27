import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Before;

public class Lab05Test {

    Triangle triangle;

    @Before
    public void setup() {
        try {
            triangle = new Triangle(3.0, 4.0, 5.0);
        }
        catch (IllegalTriangleException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shouldGetPerimeter() {

        assertEquals(12.0, triangle.getPerimeter(), 0.001);
    }

    @Test
    public void shouldGetArea() {
        assertTrue(triangle.getArea() < 10);
    }
}