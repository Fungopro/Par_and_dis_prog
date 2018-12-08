package net.thumbtack.school.boxes;


import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.figures.v3.Figure;


public class ArrayBox <T extends Figure>{

    private static final double EPS =1E-6;
    private T[] Content;

    public ArrayBox(T[] content) {
        Content = content;
    }

    public T[] getContent() {
        return Content;
    }

    public void setContent(T[] content) {
        Content = content;
    }

    public T getElement(int index) {
        return Content[index];
    }

    public void setElement(T figure,int index) {
        this.Content[index]=figure;
    }

    public boolean isSameSize(ArrayBox array){
        return Content.length==array.Content.length;
    }



}
