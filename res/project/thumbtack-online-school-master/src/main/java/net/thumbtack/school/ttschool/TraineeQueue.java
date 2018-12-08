package net.thumbtack.school.ttschool;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {
    private Queue<Trainee> traineeQueue;
    public TraineeQueue(){
        traineeQueue=new LinkedList<Trainee>();
    }
    public void addTrainee(Trainee trainee){
        traineeQueue.add(trainee);
    }
    public Trainee removeTrainee() throws TrainingException {
        checkQueueRemove();
        return traineeQueue.remove();
    }
    private void checkQueueRemove() throws TrainingException {
        if(traineeQueue.isEmpty())
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }
}
