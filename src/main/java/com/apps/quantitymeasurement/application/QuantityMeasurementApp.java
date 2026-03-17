package com.apps.quantitymeasurement.application;

import java.util.List;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.exception.UnsupportedOperationsException;
import com.apps.quantitymeasurement.units.IMeasurable;
import com.apps.quantitymeasurement.units.LengthUnit;
import com.apps.quantitymeasurement.units.TemperatureUnit;
import com.apps.quantitymeasurement.units.VolumeUnit;
import com.apps.quantitymeasurement.units.WeightUnit;

public class QuantityMeasurementApp{

    static QuantityMeasurementController quantityMeasurementController = new QuantityMeasurementController();

    public static boolean compare(Quantity<?> quantity1 , Quantity<?> quantity2) throws DatabaseException , UnsupportedOperationsException , IllegalArgumentException{
        return quantityMeasurementController.checkEquality(quantity1, quantity2);    
    }

    public static <U extends IMeasurable> Quantity<U> convert(Quantity<U> quantity, U targetUnit) throws DatabaseException , IllegalArgumentException{
        return quantityMeasurementController.convert(quantity, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2) throws DatabaseException , UnsupportedOperationsException , IllegalArgumentException{
        return quantityMeasurementController.add(quantity1, quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) throws DatabaseException , UnsupportedOperationsException , IllegalArgumentException {
        return quantityMeasurementController.add(quantity1, quantity2, targetUnit);
           
    }

    public static <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2) throws DatabaseException , UnsupportedOperationsException , IllegalArgumentException{
        return quantityMeasurementController.subtract(quantity1, quantity2);
    }

    public static <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) throws DatabaseException , UnsupportedOperationsException , IllegalArgumentException{
        return quantityMeasurementController.subtract(quantity1, quantity2, targetUnit);
    }

    public static <U extends IMeasurable> double divide(Quantity<U> quantity1, Quantity<U> quantity2) throws DatabaseException , UnsupportedOperationsException , IllegalArgumentException, ArithmeticException{
        return quantityMeasurementController.divide(quantity1, quantity2);
    }

    public static void printQuantityMeasurementHistory(){
        try {
            List<Quantity<?>> quantities = quantityMeasurementController.getQuantityMeasurementHistory();
            if(quantities.isEmpty()){
                System.out.println("No history found");
                return;
            }
            System.out.println("History: ");
            for(Quantity<?> quantity: quantities){
                System.out.println(quantity);
            }
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void  printQuantityMeasurementHistoryByMeasurementType(String measurementType){
        try {
            List<Quantity<?>> quantities = quantityMeasurementController.getQuantityMeasurementHistoryByMeasurementType(measurementType);
            if(quantities.isEmpty()){
                System.out.println("No history found");
                return;
            }
            System.out.println("History: ");
            for(Quantity<?> quantity: quantities){
                System.out.println(quantity);
            }
        } catch (DatabaseException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAll(){
        try {
            quantityMeasurementController.deleteAll();
            System.out.println("All measurements deleted successfully");
        } catch (DatabaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static <U extends IMeasurable> void demonstrateComparison(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            System.out.println("Is "+quantity1+" equals to "+quantity2+"? " + compare(quantity1, quantity2));
        } catch (DatabaseException | UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static <U extends IMeasurable> void demonstrateConversion(Quantity<U> quantity, U targetUnit){
        try {
            System.out.println(quantity + " = " + convert(quantity, targetUnit));
        } catch (DatabaseException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            System.out.println(quantity1 + " + " + quantity2 + " = " + add(quantity1, quantity2));
        } catch (DatabaseException | UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static <U extends IMeasurable> void demonstrateAddition(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        try {
            System.out.println(quantity1 + " + " + quantity2 + " = " + add(quantity1, quantity2,targetUnit));
        } catch (DatabaseException | UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            System.out.println(quantity1 + " - " + quantity2 + " = " + subtract(quantity1, quantity2));
        } catch (DatabaseException | UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static <U extends IMeasurable> void demonstrateSubtraction(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit){
        try {
            System.out.println(quantity1 + " - " + quantity2 + " = " + subtract(quantity1, quantity2,targetUnit));
        } catch (DatabaseException | UnsupportedOperationsException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static <U extends IMeasurable> void demonstrateDivision(Quantity<U> quantity1, Quantity<U> quantity2){
        try {
            System.out.printf(quantity1 + " / " + quantity2 + " = %.2f" , divide(quantity1, quantity2));
        } catch (DatabaseException | UnsupportedOperationsException | IllegalArgumentException | ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {

        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0, LengthUnit.YARDS);

        demonstrateComparison(length1, length2);
        demonstrateConversion(length1, LengthUnit.CENTIMETERS);
        demonstrateAddition(length1, length2);
        demonstrateAddition(length1, length2, LengthUnit.INCHES);
        demonstrateSubtraction(length1, length2);
        demonstrateSubtraction(length1, length2, LengthUnit.INCHES);
        demonstrateDivision(length1, length2);

        Quantity<WeightUnit> weight1 = new Quantity<>(15000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(5.0, WeightUnit.POUNDS);

        demonstrateComparison(weight1, weight2);
        demonstrateConversion(weight1, WeightUnit.KILOGRAMS);
        demonstrateAddition(weight1, weight2);
        demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAMS);
        demonstrateSubtraction(weight1, weight2);
        demonstrateSubtraction(weight1, weight2, WeightUnit.KILOGRAMS);
        demonstrateDivision(weight1, weight2);

        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> volume2 = new Quantity<>(25.0, VolumeUnit.LITRE);

        demonstrateComparison(volume1, volume2);
        demonstrateConversion(volume1, VolumeUnit.LITRE);
        demonstrateAddition(volume1, volume2);
        demonstrateAddition(volume1, volume2, VolumeUnit.MILLILITRE);
        demonstrateSubtraction(volume1, volume2);
        demonstrateSubtraction(volume1, volume2, VolumeUnit.MILLILITRE);
        demonstrateDivision(volume1, volume2);

        Quantity<TemperatureUnit> temp1 = new Quantity<>(15.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> temp2 = new Quantity<>(25.0, TemperatureUnit.FAHRENHEIT);

        demonstrateComparison(temp1, temp2);
        demonstrateConversion(temp1, TemperatureUnit.KELVIN);
        demonstrateAddition(temp1, temp2);
        demonstrateAddition(temp1, temp2, TemperatureUnit.KELVIN);
        demonstrateSubtraction(temp1, temp2);
        demonstrateSubtraction(temp1, temp2, TemperatureUnit.KELVIN);
        demonstrateDivision(temp1, temp2);

        printQuantityMeasurementHistory();

        printQuantityMeasurementHistoryByMeasurementType("WeightUnit");

        deleteAll();

        printQuantityMeasurementHistory();
    }
}
