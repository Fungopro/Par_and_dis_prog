/*Теперь создайте класс ColorException (наследник от класса Exception) - класс исключения, экземпляр которого должен выбрасываться
при невозможности преобразовать текстовую строку в enum Color или если в качестве Color или String передается null.
В этом классе должно быть поле типа enum ColorErrorCode, конструктор, принимающий enum ColorErrorCode и геттер (getErrorCode) для него.
 Разместите этот класс также в пакете net.thumbtack.school.colors.*/

package net.thumbtack.school.colors;

public class ColorException extends Exception {
    private ColorErrorCode msg;

    public ColorException(ColorErrorCode msg) {
        this.msg=msg;
    }

    public ColorErrorCode getErrorCode() {
        return this.msg;
    }
}
