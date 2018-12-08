package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class Rectangle3DTest {

    @Test
    public void setHeight() throws ColorException {
        Rectangle3D rect= new Rectangle3D(10,20,30,40,10, Color.BLUE);
        rect.setHeight(20);
        assertEquals(20, rect.getHeight());
    }

    @Test
    public void isInside() throws ColorException {
        Rectangle3D rect3D = new Rectangle3D(10, 20, 30, 40, 10, Color.BLUE);
        assertTrue(rect3D.isInside(new Point3D(10, 30,0)));
        assertTrue(rect3D.isInside(new Point3D(30, 30,10)));
        assertFalse(rect3D.isInside(new Point3D(10, 40,40)));
        assertFalse(rect3D.isInside(new Point3D(0, 0,-10)));
        rect3D.setHeight(-10);
        assertTrue(rect3D.isInside(new Point3D(30, 30,-5)));
        assertFalse(rect3D.isInside(new Point3D(30, 30,5)));
    }
    @Test
    public void isInside2() throws ColorException {
        Rectangle3D rect3D = new Rectangle3D(10, 20, 30, 40, 10, Color.BLUE);
        assertTrue(rect3D.isInside(10, 30,0));
        assertTrue(rect3D.isInside(30, 30,10));
        assertFalse(rect3D.isInside(10, 40,40));
        assertFalse(rect3D.isInside(0, 0,-10));
        rect3D.setHeight(-10);
        assertTrue(rect3D.isInside(30, 30,-5));
        assertFalse(rect3D.isInside(30, 30,5));
    }

    @Test
    public void getVolume() throws ColorException {
        Rectangle3D rect3D = new Rectangle3D(new Point2D(0,0),new Point2D(10,10), 10, Color.BLUE);
        assertEquals( (double)1000,rect3D.getVolume(),0.0);
    }
}