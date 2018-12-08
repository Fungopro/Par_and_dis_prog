package net.thumbtack.school.ttschool;

import java.util.*;

public class School {
    private String schoolName;
    private Set<Group> group= new HashSet<Group>();
    private int year;

    public School(String schoolName, int year) throws TrainingException {
        checkSchool(schoolName);
        this.schoolName = schoolName;
        this.year = year;
    }




    public String getName() {
        return schoolName;
    }

    public void setName(String school_name) throws TrainingException {
        checkSchool(school_name);
        schoolName = school_name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void addGroup(Group group) throws TrainingException {
        checkGroup(group);
        this.group.add(group);
    }

    public Set<Group> getGroups() {
        return group;
    }


    public void  removeGroup(Group group) throws TrainingException {
        containsGroup(group);
        this.group.remove(group);
    }

    public void  removeGroup(String name) throws TrainingException {
    containsGroup(name);
    this.group.removeIf(element-> element.getName().equals(name));
    }


    private void checkSchool(String schoolName) throws TrainingException {
        if(schoolName==null || schoolName.length()==0)
            throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
    }

    private void checkGroup(Group group) throws TrainingException {
        for (Group item : this.group) {
            if (item.getName().equals(group.getName()))
                throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
        }
    }

    private boolean  containsGroup(String group) throws TrainingException {
        for(Group group1: this.group){
            if(group.equals(group1.getName()))
                return true;
        }
        throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);

    }
    public boolean  containsGroup(Group group) throws TrainingException {
        if(!this.group.contains(group))
            throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        else return true;
    }


}
