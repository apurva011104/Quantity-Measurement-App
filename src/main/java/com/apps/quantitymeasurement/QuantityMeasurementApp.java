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

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2){
        return quantity1.subtract(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        return quantity1.subtract(quantity2, targetUnit);
    }

    public static <U extends IMeasurable> double demonstrateDivision(Quantity<U> quantity1, Quantity<U> quantity2){
        return quantity1.divide(quantity2);
    }


    public static void main(String[] args) {

        Quantity<LengthUnit> length1 = new Quantity<>(25.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(36.0, LengthUnit.YARDS);

        System.out.println(length1 + " - " + length2 + " = " + demonstrateSubtraction(length1, length2));
        System.out.println(length1 + " - " + length2 + " = " + demonstrateSubtraction(length1, length2, LengthUnit.INCHES));
        System.out.println(length1 + " / " + length2 + " = " + demonstrateDivision(length1, length2));

        Quantity<WeightUnit> weight1 = new Quantity<>(25.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(10000.0, WeightUnit.GRAMS);

        System.out.println(weight1 + " - " + weight2 + " = " + demonstrateSubtraction(weight1, weight2));
        System.out.println(weight1 + " - " + weight2 + " = " + demonstrateSubtraction(weight1, weight2, WeightUnit.POUNDS));
        System.out.println(weight1 + " / " + weight2 + " = " + demonstrateDivision(weight1, weight2));

        Quantity<VolumeUnit> volume1 = new Quantity<>(25.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(10000.0, VolumeUnit.MILLILITRE);

        System.out.println(volume1 + " - " + volume2 + " = " + demonstrateSubtraction(volume1, volume2));
        System.out.println(volume1 + " - " + volume2 + " = " + demonstrateSubtraction(volume1, volume2, VolumeUnit.GALLON));
        System.out.println(volume1 + " / " + volume2 + " = " + demonstrateDivision(volume1, volume2));

    }
}
