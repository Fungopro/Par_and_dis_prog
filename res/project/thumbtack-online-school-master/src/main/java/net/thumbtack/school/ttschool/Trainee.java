package net.thumbtack.school.ttschool;

import java.util.Objects;

public class Trainee {
    private String firstName;
    private String lastName;
    private int rating;

    public Trainee(String firstName, String lastName, int rating) throws TrainingException {
            this.setFirstName(firstName);
            this.setLastName(lastName);
            this.setRating(rating);
    }

    private void checkFirstName(String firstName) throws TrainingException {
        if(firstName == null || firstName.length()==0){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_FIRSTNAME);
        }
    }


    private void checkLastName(String lastName) throws TrainingException {
        if(lastName == null || lastName.length()==0){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_LASTNAME);
        }
    }

    private void checkRating(int rating) throws TrainingException {
        if(rating<1 || rating>5){
            throw new TrainingException(TrainingErrorCode.TRAINEE_WRONG_RATING);
        }
    }


    public String getFullName() {
        return this.getFirstName()+" "+this.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws TrainingException {
        this.checkFirstName(firstName);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws TrainingException {
        this.checkLastName(lastName);
        this.lastName = lastName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) throws TrainingException {
        this.checkRating(rating);
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainee trainee = (Trainee) o;
        return rating == trainee.rating &&
                Objects.equals(firstName, trainee.firstName) &&
                Objects.equals(lastName, trainee.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName, rating);
    }

}
