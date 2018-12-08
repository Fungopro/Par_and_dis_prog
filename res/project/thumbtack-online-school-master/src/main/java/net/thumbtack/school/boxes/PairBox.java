package net.thumbtack.school.boxes;

import net.thumbtack.school.area.HasArea;
import net.thumbtack.school.figures.v3.Figure;

public class PairBox <T extends Figure ,V extends Figure>  implements HasArea{
    private static final double EPS =1E-6;
    private T contentFirst;
    private V contentSecond;

    public PairBox(T contentFirst, V contentSecond) {
        this.setContentFirst(contentFirst);
        this.setContentSecond(contentSecond);
    }

    public T getContentFirst() {
        return contentFirst;
    }

    public void setContentFirst(T contentFirst) {
        this.contentFirst = contentFirst;
    }

    public V getContentSecond() {
        return contentSecond;
    }

    public void setContentSecond(V contentSecond) {
        this.contentSecond = contentSecond;
    }


    @Override
    public double getArea() {
        return contentFirst.getArea() + contentSecond.getArea();
    }

    public boolean isAreaEqual(PairBox box){
        return Math.abs(this.getArea()-box.getArea())<EPS;
    }

    public static boolean isAreaEqual(PairBox box1,PairBox box2){
        return Math.abs(box2.getArea()-box1.getArea())<EPS;
    }


}
