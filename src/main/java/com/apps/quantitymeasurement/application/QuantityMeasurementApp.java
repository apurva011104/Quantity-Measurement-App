package com.apps.quantitymeasurement.application;

import java.util.Set;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.UnsupportedOperationsException;
import com.apps.quantitymeasurement.units.IMeasurable;
import com.apps.quantitymeasurement.units.TemperatureUnit;

public class QuantityMeasurementApp {

    static QuantityMeasurementController quantityMeasurementController = new QuantityMeasurementController();

    public static boolean demonstrateEquality(Quantity<?> quantity1 , Quantity<?> quantity2){
        try {
            return quantityMeasurementController.checkEquality(quantity1, quantity2);
        } 
        catch (UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(Quantity<U> quantity, U targetUnit){
        try {
            return quantityMeasurementController.convert(quantity, targetUnit);
        } 
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            return quantityMeasurementController.add(quantity1, quantity2);
        } 
        catch (UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;  
        
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        try {
            return quantityMeasurementController.add(quantity1, quantity2, targetUnit);
        } 
        catch (UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;    
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            return quantityMeasurementController.subtract(quantity1, quantity2);
        } 
        catch (UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static <U extends IMeasurable> Quantity<U> demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        try {
            return quantityMeasurementController.subtract(quantity1, quantity2, targetUnit);
        } 
        catch (UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static <U extends IMeasurable> double demonstrateDivision(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            return quantityMeasurementController.divide(quantity1, quantity2);

        } catch (UnsupportedOperationsException | IllegalArgumentException | ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        return 0.0;
    }

    public static void printHistory(){
        Set<Quantity<?>> quantities = quantityMeasurementController.getQuantityMeasurementHistory();
        for(Quantity<?> quantity: quantities){
            System.out.println(quantity);
        }
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

        printHistory();

    }
}
