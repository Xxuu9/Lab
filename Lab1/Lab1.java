
package com.example.mylab;
import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        Scanner keyb = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = keyb.next();
        System.out.println("Enter your grade: ");
        double grade = keyb.nextDouble();
        if(grade >= 80 && grade <= 100){
            System.out.println("Hi "+name+", your letter grade is A.");
        }else if(grade >= 70 && grade <80){
            System.out.println("Hi "+name+", your letter grade is B.");
        }else if(grade >= 60 && grade <70){
            System.out.println("Hi "+name+", your letter grade is C.");
        }else if(grade >= 50 && grade <60){
            System.out.println("Hi "+name+", your letter grade is D.");
        }else if(grade >= 0 && grade <50){
            System.out.println("Hi "+name+", your letter grade is F.");
        }else
            System.out.println("The grade is outside the valid range.");
 }
}
