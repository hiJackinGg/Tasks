package com.company.task4;

public class EmployeeFixedPayment extends EmployeeBase{
    double fixedSalary;

    public EmployeeFixedPayment(long id, String name, double fixedSalary){
        super(id, name);
        this.fixedSalary = fixedSalary;
    }

    @Override
    public double calculateAvgSelary(){
        return fixedSalary;
    }

}


