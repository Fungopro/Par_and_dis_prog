package net.thumbtack.school.figures.v3;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.Colored;

import java.util.Objects;

public class Rectangle extends Figure implements Colored{
    private Point2D bottomRight;
    private Point2D topLeft;

    public Rectangle(Point2D leftTop, Point2D rightBottom, Color color) throws ColorException {
        super(color);
        this.setTopLeft(leftTop);
        this.setBottomRight(rightBottom);
    }

    public Rectangle(Point2D leftTop, Point2D rightBottom, String color) throws ColorException {
        super(color);
        this.setTopLeft(leftTop);
        this.setBottomRight(rightBottom);
    }

    public Rectangle(Point2D leftTop, Point2D rightBottom) throws ColorException {
        this(leftTop,rightBottom,Color.BLUE);
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom, Color color) throws ColorException {
        this(new Point2D(xLeft,yTop), new Point2D(xRight,yBottom),color);
    }

    public Rectangle(int xLeft, int yTop, int xRight, int yBottom,String color) throws ColorException {
        this(new Point2D(xLeft,yTop), new Point2D(xRight,yBottom),color);
    }

    public Rectangle(int length, int width, Color color) throws ColorException {
        this(new Point2D(0,-width), new Point2D(length,0),color);
    }

    public Rectangle(int length, int width, String color) throws ColorException {
        this(new Point2D(0,-width), new Point2D(length,0),color);
    }

    public Rectangle(Color color) throws ColorException {
        this(new Point2D(0,-1), new Point2D(1,0),color);
    }

    public Rectangle(String color) throws ColorException {
        this(new Point2D(0,-1), new Point2D(1,0),color);
    }

    public Rectangle() throws ColorException {
        this(new Point2D(0,-1), new Point2D(1,0),Color.BLUE);
    }

    public Point2D getTopLeft() {
        return topLeft;
    }

    public Point2D getBottomRight(){
        return bottomRight;
    }

    public void setBottomRight(Point2D bottomRight){
        this.bottomRight = bottomRight;
    }

    public void setTopLeft(Point2D topLeft){
        this.topLeft=topLeft;
    }

    public int getLength(){
        return Math.abs( bottomRight.getX()-topLeft.getX());
    }

    public int getWidth(){
        return Math.abs( -bottomRight.getY()+topLeft.getY());
    }

    public void moveRel(int dx, int dy){
        bottomRight.moveRel(dx, dy);
        topLeft.moveRel(dx,dy);
    }

    public void enlarge(int nx, int ny){
        bottomRight.moveTo(topLeft.getX() + (bottomRight.getX()-topLeft.getX())*nx, topLeft.getY()+((-topLeft.getY()+bottomRight.getY())*ny));
    }

    public double getArea(){
        return (double)Math.abs((bottomRight.getX()-topLeft.getX())*(topLeft.getY()-bottomRight.getY()));
    }

    public double getPerimeter(){
        return (double)(Math.abs(bottomRight.getX()-topLeft.getX())*2+Math.abs(topLeft.getY()-bottomRight.getY())*2);
    }

    public boolean isInside(int x, int y){
        return (x<=bottomRight.getX()&&x>=topLeft.getX()&&y>=topLeft.getY()&&y<=bottomRight.getY());
    }

    //var intersects = function ( a, b ) {
    //  return ( a.y < b.y1 || a.y1 > b.y || a.x1 < b.x || a.x > b.x1 );
    //}
    public boolean isIntersects(Rectangle rectangle){

        return topLeft.getY() < rectangle.bottomRight.getY() && bottomRight.getY() > rectangle.getTopLeft().getY() && bottomRight.getX() > rectangle.getTopLeft().getX() && topLeft.getX() < rectangle.bottomRight.getX();
    }

    public boolean isInside(Rectangle rectangle){
        return isInside(rectangle.getTopLeft().getX(),rectangle.getTopLeft().getY())&&isInside(rectangle.bottomRight.getX(),rectangle.getTopLeft().getY())&&isInside(rectangle.getTopLeft().getX(),rectangle.bottomRight.getY())&&isInside(rectangle.bottomRight.getX(),rectangle.bottomRight.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(bottomRight, rectangle.bottomRight);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), bottomRight);
    }
}
