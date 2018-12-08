package net.thumbtack.school.figures.v3;

import org.junit.Test;

import static org.junit.Assert.*;

public class Point2DTest {
    @Test
    public void Point() {
        Point2D point=new Point2D();
        assertEquals(point.getX(),0);
        assertEquals(point.getY(),0);
    }

    @Test
    public void PointEquals(){
        Point2D point1= new Point2D();
        Point2D point2= new Point2D();
        Point3D point4= new Point3D();
        Point2D point3= new Point2D(1,1);
        Point2D point5= new Point2D(0,1);
        assertTrue(point1.equals(point2));
        assertTrue(point1.equals(point1));
        assertFalse(point1.equals(null));
        assertFalse(point1.equals(point4));
        assertFalse(point1.equals(point3));
        assertFalse(point1.equals(point5));

    }

}