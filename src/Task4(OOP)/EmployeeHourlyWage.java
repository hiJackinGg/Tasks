package com.company.task4;


public class EmployeeHourlyWage extends EmployeeBase{
    double hourlyWage;

    public EmployeeHourlyWage(){}

    public EmployeeHourlyWage(long id, String name, double hourlyWage){
        super(id, name);
        this.hourlyWage = hourlyWage;
    }

    @Override
    public double calculateAvgSelary() {

        return  20.8*8*hourlyWage;
    }
}


