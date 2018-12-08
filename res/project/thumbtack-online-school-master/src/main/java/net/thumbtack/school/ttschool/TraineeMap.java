package net.thumbtack.school.ttschool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TraineeMap {
    private Map<Trainee, String> traineeMap;

    public Map<Trainee, String> getTraineeMap() {
        return traineeMap;
    }

    public void setTraineeMap(Map<Trainee,String> traineeMap) {
        this.traineeMap = traineeMap;
    }

    public TraineeMap() {
        traineeMap=new HashMap<Trainee, String>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
    checkTrainee(trainee);
    traineeMap.put(trainee,institute);
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
    if (null==traineeMap.replace(trainee, institute))
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
   if(null==traineeMap.remove(trainee))
       throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }


    public int getTraineesCount(){
        return traineeMap.values().size();
    }


    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        if(traineeMap.get(trainee)==null)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        else
            return traineeMap.get(trainee);
    }


    public Set<Trainee> getAllTrainees(){
        return traineeMap.keySet();
    }



    public Set<String> getAllInstitutes(){
        Set<String> temp= new HashSet<>();
        for(Map.Entry entry: traineeMap.entrySet()){
        temp.add((String) entry.getValue());
        }
        return temp;
    }


    public boolean isAnyFromInstitute(String institute){
        return traineeMap.values().contains(institute);
    }

    private void checkTrainee(Trainee trainee_1) throws TrainingException {
        for (Map.Entry<Trainee, String> entry: traineeMap.entrySet()) {
            if(entry.getKey().equals(trainee_1))
                throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
        }
    }
}
