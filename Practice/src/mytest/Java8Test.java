/**
 *  Copyright 2016 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package src.mytest;

/**
 * @version 1.0, 21-Apr-2016
 * @author ankit
 */
public class Java8Test {
    public static void main(String args[]) {
        Java8Test tester = new Java8Test();

        //with type declaration
        MathOperation addition = (int a, int b) -> a + b;

        //with out type declaration
        MathOperation subtraction = (a, b) -> a - b;

        //with return statement along with curly braces
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        //without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        //with parenthesis
        GreetingService greetService1 = message -> System.out.println("Hello " + message);
        greetService1.sayMessage("Mahesh");

        //without parenthesis
        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);
        greetService2.sayMessage("Suresh");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }  

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}