package net.thumbtack.school.colors;

import static net.thumbtack.school.colors.ColorErrorCode.WRONG_COLOR_STRING;

public enum Color { RED, GREEN, BLUE;

    public static Color colorFromString(String colorString) throws ColorException {
        checkColor(colorString);
        return Color.valueOf(colorString);
    }
    public static void checkColor (String colorString) throws ColorException {
        if(colorString ==null || colorString.length()==0)
            throw new ColorException(ColorErrorCode.NULL_COLOR);
        try {
            Color.valueOf(colorString);
        }catch (IllegalArgumentException ex){
            throw new ColorException(WRONG_COLOR_STRING);
        }

    }

    public static void checkColor (Color color) throws ColorException {
        if(color ==null)
            throw new ColorException(ColorErrorCode.NULL_COLOR);
    }
}
