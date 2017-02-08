package com.company.task4;

import java.io.Serializable;

public abstract class EmployeeBase implements Serializable {

    private long id;
    private String name;

    public EmployeeBase(){}

    public EmployeeBase(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract double calculateAvgSelary();

    @Override
    public String toString(){
        return "Id: " + this.id + "\n"+
                "name: " + this.name + "\n"+
                "Avg. Salary" + this.calculateAvgSelary();
    }

}


