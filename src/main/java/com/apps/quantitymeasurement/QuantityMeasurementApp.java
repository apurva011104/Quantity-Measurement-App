package com.apps.quantitymeasurement;

import java.util.Scanner;

import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {

    private static final Scanner SCANNER = new Scanner(System.in);
    
    public static boolean demonstrateLengthEquality(Length length1 , Length length2){
        return length1.equals(length2);
    }

    public static void demonstrateLengthComparison(double value1, LengthUnit lengthUnit1, double value2, LengthUnit lengthUnit2){
        Length length1 = new Length(value1, lengthUnit1);
        Length length2 = new Length(value2, lengthUnit2);

        System.out.println("Are lengths equal?: "+ demonstrateLengthEquality(length1, length2));
    }

    public static LengthUnit unitInput(String unit){ 
        switch(unit){
            case "feet":
                return LengthUnit.FEET;
            case "inches":
                return LengthUnit.INCHES;
            case "yards":
                return LengthUnit.YARDS;
            case "cms":
                return LengthUnit.CENTIMETERS;
            default:
                throw new IllegalArgumentException("Invalid unit.");
        }

    }

    public static LengthUnit takeUnitInput(){
        SCANNER.nextLine();
        while (true) { 
            try {
                String unit = SCANNER.nextLine();
                LengthUnit lengthUnit = unitInput(unit);
                return lengthUnit;
                
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()+" Enter valid unit: ");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter value1: ");
        double value1 = SCANNER.nextDouble();
        System.out.println("Enter value1 unit type (feet, inches, yards or cms): ");
        LengthUnit lengthUnit1 = takeUnitInput();
        System.out.println("Enter value2: ");
        double value2 = SCANNER.nextDouble();
        System.out.println("Enter value2 unit type (feet, inches, yards or cms): ");
        LengthUnit lengthUnit2 = takeUnitInput();

        demonstrateLengthComparison(value1, lengthUnit1, value2, lengthUnit2);
    }
}
