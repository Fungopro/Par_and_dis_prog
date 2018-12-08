package net.thumbtack.school.ttschool;

public enum TrainingErrorCode {
    TRAINEE_WRONG_FIRSTNAME("invalid firstname"),TRAINEE_WRONG_LASTNAME("invalid lastname"),
    TRAINEE_WRONG_RATING("invalid rating"),GROUP_WRONG_NAME("invalid group name"),
    GROUP_WRONG_ROOM("invalid group auditory"), TRAINEE_NOT_FOUND("Trainee not found"),
    GROUP_NOT_FOUND("Group not found"),DUPLICATE_GROUP_NAME("Duplicate group name"),
    SCHOOL_WRONG_NAME("School wrong name"),DUPLICATE_TRAINEE("Duplicate trainee"),EMPTY_TRAINEE_QUEUE("Queue is empty");

    private String errorString;

    TrainingErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }

}
