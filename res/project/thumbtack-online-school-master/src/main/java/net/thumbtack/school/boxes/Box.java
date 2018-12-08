package net.thumbtack.school.boxes;


import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.figures.v3.Figure;


public class Box <T extends Figure> implements HasArea{

    private static final double EPS =1E-6;
    private T Content;

    public Box(T obj) {
        this.Content = obj;
    }

    public T getContent() {
        return Content;
    }

    public void setContent(T obj) {
        this.Content = obj;
    }


    @Override
    public double getArea() {
        return Content.getArea();
    }

    public boolean isAreaEqual(Box V) {
        return Math.abs(Content.getArea() - V.getArea())<EPS;
    }

    public static boolean isAreaEqual(Box V,Box C) {
        return Math.abs(C.getArea() - V.getArea())<EPS;
    }


}
