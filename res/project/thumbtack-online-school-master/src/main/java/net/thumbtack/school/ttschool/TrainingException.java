package net.thumbtack.school.ttschool;

public class TrainingException extends Exception {
    private TrainingErrorCode msg;

    public TrainingException(TrainingErrorCode msg) {
        this.msg = msg;
    }

    public TrainingErrorCode getErrorCode() {
        return msg;
    }
    public void setMsg(TrainingErrorCode msg) {
        this.msg = msg;
    }
}
