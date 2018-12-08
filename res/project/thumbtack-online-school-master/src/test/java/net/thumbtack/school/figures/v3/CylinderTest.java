package net.thumbtack.school.figures.v3;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;
import org.junit.Test;

import static org.junit.Assert.*;

public class CylinderTest {

    @Test
    public void getHeightAndsetHeight() throws ColorException {
        Cylinder cilynder = new Cylinder(1,5, Color.BLUE);
        assertEquals(5, cilynder.getHeight());
        cilynder.setHeight(10);
        assertEquals(10, cilynder.getHeight() );
    }

}