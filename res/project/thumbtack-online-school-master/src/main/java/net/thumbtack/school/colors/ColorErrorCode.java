

package net.thumbtack.school.colors;

public enum ColorErrorCode {
    WRONG_COLOR_STRING("color is wrong"), NULL_COLOR("color is null");

    private String errorString;

    ColorErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
