package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1 , Quantity<U> quantity2){
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit){
        return quantity.convertTo(targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){
        return quantity1.add(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        return quantity1.add(quantity2, targetUnit);
    }

    public static void main(String[] args) {
        
        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.YARDS);
        Quantity<LengthUnit> length2 = new Quantity<>(45.0, LengthUnit.FEET);

        System.out.println("Is " + length1 +" equals to " + length2 + "? " + demonstrateEquality(length1, length2));
        System.out.println(length1 + " = " + demonstrateConversion(length1, LengthUnit.FEET));
        System.out.println(length1 + " + " + length2 + " = " + demonstrateAddition(length1, length2));
        System.out.println(length1 + " + " + length2 + " = " + demonstrateAddition(length1, length2, LengthUnit.INCHES));

        Quantity<WeightUnit> weight1 = new Quantity<>(1500.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(1.5, WeightUnit.KILOGRAMS);

        System.out.println("Is " + weight1 +" equals to " + weight2 + "? " + demonstrateEquality(weight1, weight2));
        System.out.println(weight1 + " = " + demonstrateConversion(weight1, WeightUnit.POUNDS));
        System.out.println(weight1 + " + " + weight2 + " = " + demonstrateAddition(weight1, weight2));
        System.out.println(weight1 + " + " + weight2 + " = " + demonstrateAddition(weight1, weight2, WeightUnit.POUNDS));



    }
}
