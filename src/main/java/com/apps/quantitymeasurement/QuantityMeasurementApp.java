package com.apps.quantitymeasurement;

import java.util.Scanner;

public class QuantityMeasurementApp {

    private static final Scanner SCANNER = new Scanner(System.in);
    public static boolean demonstrateLengthEquality(Length length1 , Length length2){
        return length1.equals(length2);
    }

    public static void demonstrateFeetEquality(){
        System.out.print("Enter length value1 in feets: ");
        double value1 = SCANNER.nextDouble();
        System.out.print("Enter length value2 in feets: ");
        double value2 = SCANNER.nextDouble();

        Length length1 = new Length(value1, Length.LengthUnit.FEET);
        Length length2 = new Length(value2, Length.LengthUnit.FEET);

        boolean isEqual = demonstrateLengthEquality(length1, length2);
        System.out.println("Are lengths equal? " + isEqual);
    }

    public static void demonstrateInchesEquality(){
        System.out.print("Enter length value1 in inches: ");
        double value1 = SCANNER.nextDouble();
        System.out.print("Enter length value2 in inches: ");
        double value2 = SCANNER.nextDouble();

        Length length1 = new Length(value1, Length.LengthUnit.INCHES);
        Length length2 = new Length(value2, Length.LengthUnit.INCHES);

        boolean isEqual = demonstrateLengthEquality(length1, length2);
        System.out.println("Are lengths equal? " + isEqual);  
    }

    public static void demonstrateFeetInchesComparison(){
        System.out.print("Enter length value1 in feets: ");
        double value1 = SCANNER.nextDouble();
        System.out.print("Enter length value2 in inches: ");
        double value2 = SCANNER.nextDouble();

        Length length1 = new Length(value1, Length.LengthUnit.FEET);
        Length length2 = new Length(value2, Length.LengthUnit.INCHES);

        boolean isEqual = demonstrateLengthEquality(length1, length2);
        System.out.println("Are lengths equal? " + isEqual);
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
        demonstrateFeetInchesComparison();
    }
}
