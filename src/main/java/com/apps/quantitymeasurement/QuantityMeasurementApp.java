package com.apps.quantitymeasurement;

import java.util.Scanner;

public class QuantityMeasurementApp {

    private static final Scanner SCANNER = new Scanner(System.in);

    //Weight related methods
    public static boolean demonstrateWeightEquality(Weight weight1 , Weight weight2){
        return weight1.equals(weight2);
    }

    public static void demonstrateWeightComparison(double value1, WeightUnit weightUnit1, double value2, WeightUnit weightUnit2){
        Weight weight1 = new Weight(value1, weightUnit1);
        Weight weight2 = new Weight(value2, weightUnit2);

        System.out.println("Are "+ weight1 +" and " + weight2 + " equal?: "+ demonstrateWeightEquality(weight1, weight2));
    }

    public static Weight demonstrateWeightConversion(double value, WeightUnit sourceUnit, WeightUnit targetUnit){
        return demonstrateWeightConversion(new Weight(value, sourceUnit), targetUnit);
    }

    public static Weight demonstrateWeightConversion(Weight weight, WeightUnit targetUnit){
        return weight.convertTo(targetUnit);
    }

    public static Weight demonstrateWeightAddition(double value1, WeightUnit weightUnit1, double value2, WeightUnit weightUnit2){
        Weight weight1 = new Weight(value1, weightUnit1);
        Weight weight2 = new Weight(value2, weightUnit2);

        return weight1.add(weight2);
    }

    public static Weight demonstrateWeightAddition(double value1, WeightUnit weightUnit1, double value2, WeightUnit weightUnit2, WeightUnit targetUnit){
        Weight weight1 = new Weight(value1, weightUnit1);
        Weight weight2 = new Weight(value2, weightUnit2);

        return weight1.add(weight2, targetUnit);
    }
    
    //Length related methods
    public static boolean demonstrateLengthEquality(Length length1 , Length length2){
        return length1.equals(length2);
    }

    public static void demonstrateLengthComparison(double value1, LengthUnit lengthUnit1, double value2, LengthUnit lengthUnit2){
        Length length1 = new Length(value1, lengthUnit1);
        Length length2 = new Length(value2, lengthUnit2);

        System.out.println("Are "+ length1 +" and " + length2 + " equal?: "+ demonstrateLengthEquality(length1, length2));
    }

    public static Length demonstrateLengthConversion(double value, LengthUnit sourceUnit, LengthUnit targetUnit){
        return demonstrateLengthConversion(new Length(value, sourceUnit), targetUnit);
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit targetUnit){
        return length.convertTo(targetUnit);
    }

    public static Length demonstrateLengthAddition(double value1, LengthUnit lengthUnit1 , double value2, LengthUnit lengthUnit2){
        Length length1 = new Length(value1, lengthUnit1);
        Length length2 = new Length(value2, lengthUnit2);

        return length1.add(length2);
    }

    public static Length demonstrateLengthAddition(double value1, LengthUnit lengthUnit1 , double value2, LengthUnit lengthUnit2, LengthUnit targetUnit){
        Length length1 = new Length(value1, lengthUnit1);
        Length length2 = new Length(value2, lengthUnit2);

        return length1.add(length2,targetUnit);
    }

    public static void main(String[] args) {

        demonstrateWeightComparison(5000.0, WeightUnit.GRAMS, 5.0, WeightUnit.KILOGRAMS);

        System.out.println("5000.0 grams = "+demonstrateWeightConversion(5000.0, WeightUnit.GRAMS, WeightUnit.POUNDS));

        System.out.println("12.0 kgs + 1000.0 grams = " + demonstrateWeightAddition(12.0, WeightUnit.KILOGRAMS, 1000.0, WeightUnit.GRAMS));

        System.out.println("12.0 kgs + 1000.0 grams = " + demonstrateWeightAddition(12.0, WeightUnit.KILOGRAMS, 1000.0, WeightUnit.GRAMS, WeightUnit.POUNDS));
        
    }
}
