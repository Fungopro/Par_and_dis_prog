package net.thumbtack.school.colors;

import net.thumbtack.school.figures.v3.Rectangle;
import org.junit.Test;

import static org.junit.Assert.*;

public class ColorErrorCodeTest {

    @Test
    public void getErrorString() {
        try {
            Rectangle rect = new Rectangle(10, 20, "YELLOW");
            fail();
        } catch (ColorException ex) {
            assertEquals(ColorErrorCode.WRONG_COLOR_STRING.getErrorString(), ex.getErrorCode().getErrorString());
        }
    }
}