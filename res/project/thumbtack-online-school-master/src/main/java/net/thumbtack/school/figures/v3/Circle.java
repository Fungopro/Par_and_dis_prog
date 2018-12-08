package net.thumbtack.school.figures.v3;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

import java.util.Objects;


public class Circle extends Figure implements HasArea{
    private int radius;
    private Point2D center;

    public Circle(Point2D center,int radius, Color color ) throws ColorException {
        super(color);
        this.setRadius(radius);
        this.setCenter(center);
    }

    public Circle(Point2D center,int radius, String color) throws ColorException {
        super(Color.colorFromString(color));
        this.setRadius(radius);
        this.setCenter(center);
    }

    public Circle(int x, int y, int radius, String color) throws ColorException {
        this( new Point2D(x,y),radius,Color.colorFromString(color));
    }

    public Circle(int x, int y, int radius, Color color) throws ColorException {
        this( new Point2D(x,y),radius,color);
    }


    public Circle(int radius,String color) throws ColorException {
        this(new Point2D(0,0),radius, Color.colorFromString(color));
    }

    public Circle(int radius,Color color) throws ColorException {
        this(new Point2D(0,0),radius, color);
    }

    public Circle(String color) throws ColorException {
        this(new Point2D(0,0),1,Color.colorFromString(color));
    }

    public Circle(Color color) throws ColorException {
        this(new Point2D(0,0),1,color);
    }
    public Circle() throws ColorException {
        this(new Point2D(0,0),1,Color.BLUE);
    }


    public Point2D getCenter(){
        return center;
    }

    public int getRadius(){
        return radius;
    }

    public void setCenter(Point2D center){ this.center=center;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    @Override
    public void moveRel(int dx, int dy){
        center.setX(center.getX()+dx);
        center.setY(center.getY()+dy);
    }

    public void enlarge(int n){
        radius *=n;
    }

    @Override
    public double getArea(){
        return Math.PI* radius * radius;
    }

    @Override
    public double getPerimeter(){
        return 2*Math.PI* radius;
    }

    public boolean isInside(int x, int y) {
        return radius >=Math.pow(Math.pow(x-center.getX(),2)+Math.pow(y-center.getY(),2),0.5);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return radius == circle.radius &&
                Objects.equals(center, circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius, center);
    }
}
