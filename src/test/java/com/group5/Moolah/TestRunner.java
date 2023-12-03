package com.group5.Moolah;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

    public static void main(String[] args){

        System.out.println("Running test cases for ExpenseData Class: ...\n");
        Result result = JUnitCore.runClasses(ExpenseDataTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println();
        System.out.printf("Tests ran: %s, Failed: %s%n",
                result.getRunCount(), result.getFailureCount());
        System.out.println();


        System.out.println("Running test cases for UserData Class: ...\n");
        result = JUnitCore.runClasses(UserDataTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println();
        System.out.printf("Tests ran: %s, Failed: %s%n",
                result.getRunCount(), result.getFailureCount());
        System.out.println();


        System.out.println("Running test cases for ExpenseCalculation Class: ...\n");
        result = JUnitCore.runClasses(ExpenseCalculationTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println();
        System.out.printf("Tests ran: %s, Failed: %s%n",
                result.getRunCount(), result.getFailureCount());

    }
}
