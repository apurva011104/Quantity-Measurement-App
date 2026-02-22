package com.apps.quantitymeasurement;

import java.util.Scanner;

import com.apps.quantitymeasurement.Length.LengthUnit;

public class QuantityMeasurementApp {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static LengthUnit unitInput(String unit){ 
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

    private static LengthUnit takeUnitInput(){
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
    
    public static boolean demonstrateLengthEquality(Length length1 , Length length2){
        return length1.equals(length2);
    }

    public static void demonstrateLengthComparison(double value1, LengthUnit lengthUnit1, double value2, LengthUnit lengthUnit2){
        Length length1 = new Length(value1, lengthUnit1);
        Length length2 = new Length(value2, lengthUnit2);

        System.out.println("Are lengths equal?: "+ demonstrateLengthEquality(length1, length2));
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit sourceUnit, LengthUnit targetUnit){
        return demonstrateLengthConversion(new Length(value, sourceUnit), targetUnit);
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit targetUnit){
        return length.convertTo(targetUnit);
    }

    public static void main(String[] args) {
        System.out.println("Enter value: ");
        double value = SCANNER.nextDouble();
        SCANNER.nextLine();
        System.out.println("Enter source unit (feet, inches, yards or cms): ");
        LengthUnit sourceUnit = takeUnitInput();
        System.out.println("Enter target unit (feet, inches, yards or cms): ");
        LengthUnit targetUnit = takeUnitInput();

        Length convertedLength = demonstrateLengthConversion(value, sourceUnit, targetUnit);
        System.out.printf("%.2f %n", convertedLength.getValue());
        
    }
}
