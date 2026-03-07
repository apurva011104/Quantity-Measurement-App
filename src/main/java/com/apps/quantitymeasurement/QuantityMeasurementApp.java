package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> quantity1 , Quantity<U> quantity2){
        return quantity1.equals(quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit){
        return quantity.convertTo(targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            return quantity1.add(quantity2);
        } 
        catch (UnsupportedOperationsException e) {
            System.out.println(e.getMessage());
        }
        return null;  
        
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        try {
            return quantity1.add(quantity2, targetUnit);
        } 
        catch (UnsupportedOperationsException e) {
            System.out.println(e.getMessage());
        }
        return null;    
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            return quantity1.subtract(quantity2);
        } 
        catch (UnsupportedOperationsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        try {
            return quantity1.subtract(quantity2, targetUnit);
        } 
        catch (UnsupportedOperationsException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static <U extends IMeasurable> double demonstrateDivision(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            return quantity1.divide(quantity2);

        } catch (UnsupportedOperationsException e) {
            System.out.println(e.getMessage());
        }
        return 0.0;
    }


    public static void main(String[] args) {

        Quantity<TemperatureUnit> temperature1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> temperature2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        System.out.println("Is "+temperature1+" equals to "+temperature2+"? "+demonstrateEquality(temperature1, temperature2));
        System.out.println(temperature1+" = "+demonstrateConversion(temperature1, TemperatureUnit.FAHRENHEIT));
        System.out.println(temperature2+" = "+demonstrateConversion(temperature2, TemperatureUnit.KELVIN));
        System.out.println(temperature1 + " + " + temperature2 + " = " + demonstrateAddition(temperature1, temperature2));
        System.out.println(temperature1 + " + " + temperature2 + " = " + demonstrateAddition(temperature1, temperature2, TemperatureUnit.CELSIUS));
        System.out.println(temperature1 + " - " + temperature2 + " = " + demonstrateSubtraction(temperature1, temperature2));
        System.out.println(temperature1 + " - " + temperature2 + " = " + demonstrateSubtraction(temperature1, temperature2, TemperatureUnit.FAHRENHEIT));
        System.out.println(temperature1 + " / " + temperature2 + " = " + demonstrateDivision(temperature1, temperature2));

    }
}
