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
        
        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);

        System.out.println("Is " + volume1 +" equals to " + volume2 + "? " + demonstrateEquality(volume1, volume2));
        System.out.println(volume1 + " = " + demonstrateConversion(volume1, VolumeUnit.GALLON));
        System.out.println(volume1 + " + " + volume2 + " = " + demonstrateAddition(volume1, volume2));
        System.out.println(volume1 + " + " + volume2 + " = " + demonstrateAddition(volume1, volume2, VolumeUnit.GALLON));

    }
}
