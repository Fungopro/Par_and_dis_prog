package net.thumbtack.school.figures.v3;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.Colored;

import java.util.Objects;

import static net.thumbtack.school.colors.Color.checkColor;

public abstract class Figure implements Colored,HasArea {

    private Color color;

    public Figure(Color color) throws ColorException {
        checkColor(color);
        this.setColor(color);
    }

    public Figure(String color) throws ColorException {
        this.setColor(color);
    }


    public void setColor(Color color) throws ColorException {
        checkColor(color);
        this.color = color;
    }

    public void setColor(String color) throws ColorException {
        this.color = Color.colorFromString(color);
    }

    public Color getColor() {
        return color;
    }

    public abstract void moveRel(int dx, int dy);

    public abstract double getArea();

    public abstract double getPerimeter();

    public abstract boolean isInside(int x,int y);

    public boolean isInside(Point2D point){ return isInside(point.getX(),point.getY());}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Figure figure = (Figure) o;
        return color == figure.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
