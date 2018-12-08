package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.ColorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RectangleTest {

    @Test
    public void getTopLeftAndBottomRight() throws ColorException {
        Rectangle rect = new Rectangle();
        rect.setTopLeft(new Point2D(10,10));
        rect.setBottomRight(new Point2D(0,0));
        assertEquals(new Point2D(10,10), rect.getTopLeft());
        assertEquals(new Point2D(0,0), rect.getBottomRight());
    }

    @Test
    public void getBottomRight() {
    }
}