package com.company.task4;

import java.io.*;
import java.util.*;


//Class which implements all needed tasks (a,b,c,d,e).
class Tasks {

    //test collection of employees
    static List<EmployeeBase> employeeList = new ArrayList<EmployeeBase>(10){
        {
            add(new EmployeeFixedPayment(1, "John", 1000.0));
            add(new EmployeeHourlyWage(2, "Mike", 10.5));
            add(new EmployeeFixedPayment(3, "Shawn", 3000.0));
            add(new EmployeeFixedPayment(4, "Bred", 2050.10));
            add(new EmployeeHourlyWage(5, "Matt", 15.0));
            add(new EmployeeHourlyWage(6, "Sam", 20.0));
            add(new EmployeeFixedPayment(7, "Fred", 1000.0));
            add(new EmployeeFixedPayment(8, "Joy", 4000.0));
            add(new EmployeeHourlyWage(9, "Jeff", 20.0));
            add(new EmployeeFixedPayment(10, "Bob", 3500.0));
        }
    };

    public static <T extends EmployeeBase> void setCollection(Collection<T> col){
        for(EmployeeBase e : col){
            System.out.println(e);
        }
    }

    public static void sort(){
        Collections.sort(employeeList, new Comparator<EmployeeBase>() {

                    public int compare(EmployeeBase e1, EmployeeBase e2) {

                        if (e1.calculateAvgSelary() - e2.calculateAvgSelary() != 0) {
                            return (int) (e2.calculateAvgSelary() - e1.calculateAvgSelary());
                        } else {
                            return e2.getName().compareTo(e1.getName());
                        }
                    }
                }
        );

        for(EmployeeBase e : employeeList){
            System.out.println(e);
        }

    }



    public static void problemB(){
        for(int i = 0; i<5; i++){
            EmployeeBase e = employeeList.get(i);
            System.out.println(e);
//            System.out.println("id:" + e.getId());
//            System.out.println("name:" + e.getName());
//            System.out.println("salary:" + e.calculateAvgSelary());
        }
    }

    public static void problemC(){
        for(int i = employeeList.size()-3; i<employeeList.size(); i++){
            EmployeeBase e = employeeList.get(i);
            System.out.println(e);
        }
    }

    public static void writeToFile(String filename){

        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))){
            os.writeObject(employeeList);
        }
        catch(Exception e){
            System.out.println("Failed to save");
            e.printStackTrace();
        }

    }

    public static void readFromFile(String filename){
        try(ObjectInputStream os = new ObjectInputStream(new FileInputStream(filename))){
            List<EmployeeBase> list = (List)os.readObject();
            System.out.println(list);


        }
        catch(Exception e){
            System.out.println("Failed to read");
            e.printStackTrace();
        }

    }

}


