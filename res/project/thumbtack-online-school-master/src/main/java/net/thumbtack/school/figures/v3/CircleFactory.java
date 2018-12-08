package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;

abstract class CircleFactory {
    private static int num;

    public static Circle createCircle(Point2D center, int radius, Color color) throws ColorException {
        num++;
    return new Circle(center,radius,color);
    }

    public static Circle createCircle(Point2D center, int radius, String color) throws ColorException {
        num++;
        return new Circle(center,radius,color);
    }
    public static int getCircleCount(){
        return num;
    }
    public static void reset(){
        num=0;
    }

}
