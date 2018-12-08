package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

import java.util.Objects;

public class Triangle extends Figure{
    private Point2D first;
    private Point2D second;
    private Point2D third;

    public Triangle(Point2D point1, Point2D point2, Point2D point3, Color color) throws ColorException {
        super(color);
        setPoint1(point1);
        setPoint2(point2);
        setPoint3(point3);
    }

    Triangle(Point2D point1, Point2D point2, Point2D point3,String color) throws ColorException {
        super(color);
        setPoint1(point1);
        setPoint2(point2);
        setPoint3(point3);
    }

    public Point2D getPoint1(){
        return first;
    }

    public Point2D getPoint2(){
        return second;
    }

    public Point2D getPoint3(){
        return third;
    }

    public void setPoint1(Point2D point){
        this.first=point;
    }

    public void setPoint2(Point2D point){
        second=point;
    }

    public void setPoint3(Point2D point){
        third=point;
    }

    public double getSide12(){
        return Math.pow((Math.pow(this.getPoint1().getX()-second.getX(),2)+Math.pow(this.getPoint1().getY()-second.getY(),2)),0.5);
    }

    public double getSide13(){
        return Math.pow((Math.pow(this.getPoint1().getX()-third.getX(),2)+Math.pow(this.getPoint1().getY()-third.getY(),2)),0.5);
    }

    public double getSide23(){
        return Math.pow((Math.pow(second.getX()-third.getX(),2)+Math.pow(second.getY()-third.getY(),2)),0.5);
    }

    public void moveRel(int dx, int dy){
        this.getPoint1().moveRel(dx, dy);
        second.moveRel(dx, dy);
        third.moveRel(dx, dy);
    }



    public double getArea(){
        double p = (getSide12()+getSide23()+getSide13())/2;
        return Math.pow(p*(p-getSide13())*(p-getSide12())*(p-getSide23()) ,0.5);
    }

    public double getPerimeter(){
        return getSide12()+getSide23()+getSide13();
    }

    public boolean isInside(int x, int y){
        //(x1 - x0) * (y2 - y1) - (x2 - x1) * (y1 - y0)
        //(x2 - x0) * (y3 - y2) - (x3 - x2) * (y2 - y0)
        //(x3 - x0) * (y1 - y3) - (x1 - x3) * (y3 - y0)
        int a=(this.getPoint1().getX()-x)*(second.getY()-this.getPoint1().getY())-(second.getX()-this.getPoint1().getX())*(this.getPoint1().getY()-y);
        int b=(second.getX()-x)*(third.getY()-second.getY())-(third.getX()-second.getX())*(second.getY()-y);
        int c=(third.getX()-x)*(this.getPoint1().getY()-third.getY())-(this.getPoint1().getX()-third.getX())*(third.getY()-y);
        return a >= 0 && b >= 0 && c >= 0 || a <= 0 && b <= 0 && c <= 0;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(second, triangle.second) &&
                Objects.equals(third, triangle.third);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), second, third);
    }
}
