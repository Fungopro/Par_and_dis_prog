package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.ColorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class Point3DTest {

    @Test
    public void getZ() {
        Point3D point = new Point3D(30);
        assertEquals(30, point.getZ());
    }

    @Test
    public void move() {
        Point3D point = new Point3D(30);
        point.move(10,20,10 );
        assertEquals(40, point.getZ());
        assertEquals(10, point.getX());
        assertEquals(20, point.getY());

    }

    @Test
    public void setZ() {
    Point3D point = new Point3D();
    point.setZ(10);
    assertEquals(10,point.getZ());
    }


    @Test
    public void equals() throws ColorException {
        Point3D point1= new Point3D();
        Point3D point2= new Point3D();
        Point2D point4= new Point2D();
        Point3D point3= new Point3D(1,1,1);
        Point3D point5= new Point3D(0,0,1);
        assertTrue(point1.equals(point2));
        assertTrue(point1.equals(point1));
        assertFalse(point1.equals(null));
        assertFalse(point1.equals(point4));
        assertFalse(point1.equals(point3));
        assertFalse(point1.equals(point5));
    }
}