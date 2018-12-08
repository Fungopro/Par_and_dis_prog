package net.thumbtack.school.ttschool;

import java.util.*;
import java.util.concurrent.CyclicBarrier;

public class Group {

    private String groupName;
    private String room;
    private List<Trainee> group=new ArrayList<Trainee>();

    public Group(String groupName, String room) throws TrainingException {
        checkGroup(groupName);
        checkAuditory(room);
        this.groupName = groupName;
        this.room = room;
    }


    public String getName() {
        return groupName;
    }

    public void setName(String groupName) throws TrainingException {
        checkGroup(groupName);
        this.groupName = groupName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        checkAuditory(room);
        this.room = room;
    }


    public List<Trainee> getTrainees() {
        return group;
    }

    public void setTrainees(List<Trainee> group) {
        this.group = group;
    }

    public void addTrainee(Trainee trainee) throws TrainingException {
        group.add(trainee);
    }

    public void  removeTrainee(Trainee trainee) throws TrainingException {
        if(!group.remove(trainee))
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void  removeTrainee(int index) throws TrainingException {
    checkTraineeRemove(index);
    group.remove(index);
    }


    public Trainee  getTraineeByFirstName(String firstName) throws TrainingException {
        return checkTraineeGetFirst(firstName);
    }
    public Trainee  getTraineeByFullName(String fullName) throws TrainingException {
        return checkTraineeGetFull(fullName);
    }

    public void  sortTraineeListByFirstNameAscendant(){
        group.sort(new Comparator<Trainee>() {
            @Override
            public int compare(Trainee o1, Trainee o2) {

                return o1.getFullName().compareTo(o2.getFullName());
            }
        });
    }

    public void  sortTraineeListByRatingDescendant(){
        group.sort(new Comparator<Trainee>() {
            @Override
            public int compare(Trainee o1, Trainee o2) {

                return Integer.compare(o2.getRating(),o1.getRating());
            }
        });
    }

    public void  reverseTraineeList(){
        Collections.reverse(group);
    }
    public void  rotateTraineeList(int positions){
        if(positions>0)
            while (positions!=0){
            rightRotate();
            positions--;
            }
        else if (positions<0){
            while (positions!=0){
                leftRotate();
                positions++;
            }
        }
    }

    public List<Trainee>  getTraineesWithMaxRating() throws TrainingException {


        boolean[] rating= new boolean [5];
        for (Trainee trainee : group ) {
            for(int i=4; i>=0;i--){
                if(trainee.getRating()==i+1)
                    rating[i]=true;
            }
        }

        for(int i=5;i>0;i--){
            if(rating[i-1]) {
                List<Trainee> maxRating= new ArrayList<Trainee>();
                for (Trainee trainee:group)
                    if(trainee.getRating()==i)
                        maxRating.add(trainee);
                return maxRating;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public boolean  hasDuplicates(){

        for (int i=0;i<group.size();i++){
            for(int j=i+1;j<group.size();j++){
                if(group.get(i).equals(group.get(j)))
                    return true;
            }
        }
        return false;
    }



// дополнительные методы -----------------------------------------------------------------------------------------------
    private void rightRotate(){
    Trainee temp = group.get(group.size()-1);
        for (int i =group.size()-1; i>0;i--){
        group.set(i,group.get(i-1));
    }
    group.set(0,temp);
    }

    private void leftRotate(){
        Trainee temp = group.get(0);
        for (int i =0; i<group.size();i++){
            group.set(i,group.get(i+1));
        }
        group.set(group.size()-1,temp);
    }


    private static void checkGroup(String groupName) throws TrainingException {
        if(groupName ==null || groupName.length()==0)
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
    }
    private static void checkAuditory(String auditory) throws TrainingException {
        if(auditory ==null || auditory.length()==0)
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
    }
    private void checkTraineeRemove(int index) throws TrainingException {
        if(group.size()<=index)
            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }
    private Trainee checkTraineeGetFirst(String name) throws TrainingException {
        for (Trainee trainee: group) {
           if(trainee.getFirstName().equals(name))
                   return trainee;
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }
    private Trainee checkTraineeGetFull(String name) throws TrainingException {
        for (Trainee trainee: group) {
            if(trainee.getFullName().equals(name))
                return trainee;
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }


}
