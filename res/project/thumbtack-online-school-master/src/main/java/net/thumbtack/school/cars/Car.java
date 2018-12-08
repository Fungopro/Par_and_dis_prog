package net.thumbtack.school.cars;

import net.thumbtack.school.colors.Color;
import net.thumbtack.school.colors.ColorException;
import net.thumbtack.school.colors.Colored;

import static net.thumbtack.school.colors.Color.checkColor;

public class Car implements Colored{
    private String model;
    private int weight;
    private int maxSpeed;
    private Color color;

    public Car(String model, int weight, int maxSpeed, Color color) throws ColorException {
        this.setModel(model);
        this.setWeight(weight);
        this.setMaxSpeed(maxSpeed);
        this.setColor(color);
    }

    public Car(String model, int weight, int maxSpeed, String color) throws ColorException {
        this.setModel(model);
        this.setWeight(weight);
        this.setMaxSpeed(maxSpeed);
        this.setColor(color);
    }




    @Override
    public void setColor(Color color) throws ColorException {
        checkColor(color);
        this.color=color;
    }

    @Override
    public void setColor(String colorString) throws ColorException {
        this.setColor(Color.colorFromString(colorString));
    }

    @Override
    public Color getColor() {
        return color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model){
        this.model=model;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }


}
