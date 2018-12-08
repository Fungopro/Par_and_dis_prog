package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

import java.util.Objects;

public class Cylinder extends Circle {
    private int height;

    public Cylinder(Point2D center, int radius, int height, Color color) throws ColorException {
        super(center, radius,color);
        setHeight(height);
    }

    public Cylinder(Point2D center, int radius, int height, String color) throws ColorException {
        super(center, radius,Color.colorFromString(color));
        setHeight(height);
    }

    public Cylinder(int xCenter, int yCenter, int radius, int height, Color color) throws ColorException {
        super(xCenter, yCenter, radius, color);
        setHeight(height);
    }

    public Cylinder(int xCenter, int yCenter, int radius, int height,String color) throws ColorException {
        super(xCenter, yCenter, radius, Color.colorFromString(color));
        setHeight(height);
    }

    public Cylinder(int radius, int height, Color color) throws ColorException {
        super(radius,color);
        setHeight(height);
    }

    public Cylinder(int radius, int height, String color) throws ColorException {
        super(radius,Color.colorFromString(color));
        setHeight(height);
    }

    public Cylinder(Color color) throws ColorException {
        super(color);
        setHeight(1);
    }

    public Cylinder(String color) throws ColorException {
        super(Color.colorFromString(color));
        setHeight(1);
    }

    public int getHeight(){
        return height;
    }

    public void setHeight(int height){
        this.height=height;
    }

    public double getVolume(){
        return getArea()*getHeight();
    }

    public boolean isInside(int x, int y, int z){
        return isInside(x,y)&& z<=height;
    }

    public boolean isInside(Point3D point){
        return isInside(point.getX(),point.getY(), point.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cylinder cylinder = (Cylinder) o;
        return height == cylinder.height;
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), height);
    }
}
