package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testIMeasurableInterface_LengthUnitImplementation_GetConversionFactor(){
        IMeasurable unit = LengthUnit.INCHES;
        double actual = unit.getConversionFactor();
        double expected = 1.0;

        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testIMeasurableInterface_LengthUnitImplementation_ConvertToBaseUnit(){
        IMeasurable unit =  LengthUnit.FEET;
        double actual = unit.convertToBaseUnit(1.0);
        double expected = 12.0;

        assertEquals(expected, actual , 0.0001);
    }

    @Test
    public void testIMeasurableInterface_LengthUnitImplementation_ConvertFromBaseUnit(){
        IMeasurable unit =  LengthUnit.YARDS;
        double actual = unit.convertFromBaseUnit(72.0);
        double expected = 2.0;

        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testIMeasurableInterface_WeightUnitImplementation_GetConversionFactor(){
        IMeasurable unit = WeightUnit.GRAMS;
        double actual = unit.getConversionFactor();
        double expected = 0.001;

        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testIMeasurableInterface_WeightUnitImplementation_ConvertToBaseUnit(){
        IMeasurable unit = WeightUnit.GRAMS;
        double actual = unit.convertToBaseUnit(15000.0);
        double expected = 15.0;

        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testIMeasurableInterface_WeightUnitImplementation_ConvertFromBaseUnit(){
        IMeasurable unit = WeightUnit.GRAMS;
        double actual = unit.convertFromBaseUnit(6.0);
        double expected = 6000.0;

        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testGenericQuantity_LengthOperations_Equality(){
        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0, LengthUnit.YARDS);

        assertTrue(length1.equals(length2));
    }

    @Test
    public void testGenericQuantity_WeightOperations_Equality(){
        Quantity<WeightUnit> weight1 = new Quantity<>(1500.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(1.5, WeightUnit.KILOGRAMS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testCrossCategoryPrevention_LengthVsWeight_Equality(){
        Quantity<LengthUnit> length = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(1.5, WeightUnit.KILOGRAMS);

        assertFalse(length.equals(weight));
    }

    @Test
    public void testGenericQuantity_LengthOperations_Conversion(){
        Quantity<LengthUnit> length = new Quantity<>(6.0, LengthUnit.FEET);
        Quantity<LengthUnit> converted = length.convertTo(LengthUnit.INCHES);

        Quantity<LengthUnit> expected = new Quantity<>(72.0, LengthUnit.INCHES);

        assertEquals(expected, converted);
    }

    @Test
    public void testGenericQuantity_WeightOperations_Conversion(){
        Quantity<WeightUnit> weight = new Quantity<>(15.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> converted = weight.convertTo(WeightUnit.GRAMS);

        Quantity<WeightUnit> expected = new Quantity<>(15000.0, WeightUnit.GRAMS);

        assertEquals(expected, converted);
    }

    @Test
    public void testGenericQuantity_LengthOperations_Addition(){
        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0, LengthUnit.YARDS);
        Quantity<LengthUnit> sum = length1.add(length2);

        Quantity<LengthUnit> expected = new Quantity<>(30.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test 
    public  void testGenericQuantity_WeightOperations_Addition(){
        Quantity<WeightUnit> weight1 = new Quantity<>(1500.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(1.5, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> sum = weight1.add(weight2);

        Quantity<WeightUnit> expected = new Quantity<>(3000.0 , WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testGenericQuantity_LengthOperations_AdditionTargetUnit(){
        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0, LengthUnit.YARDS);
        Quantity<LengthUnit> sum = length1.add(length2, LengthUnit.INCHES);

        Quantity<LengthUnit> expected = new Quantity<>(360.0, LengthUnit.INCHES);

        assertEquals(expected, sum);
    }

    @Test 
    public  void testGenericQuantity_WeightOperations_AdditionTargetUnit(){
        Quantity<WeightUnit> weight1 = new Quantity<>(1500.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(1.5, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> sum = weight1.add(weight2);

        Quantity<WeightUnit> expected = new Quantity<>(3.0 , WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    /*
    Was preventing code from compiling
    @Test
    public void testCrossCategoryPrevention_LengthVsWeight_CompilerTypeSafety(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(15.0, WeightUnit.GRAMS).convertTo(LengthUnit.FEET);
        });
    }
    */

    @Test
    public void testGenericQuantity_ConstructorValidation_NullUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(15.0, null);
        });
    }

    @Test
    public void testGenericQuantity_ConstructorValidation_InvalidValue(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(Double.NEGATIVE_INFINITY, WeightUnit.GRAMS);
        });
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_LengthEquality(){
        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0, LengthUnit.YARDS);
        boolean isEqual = QuantityMeasurementApp.demonstrateEquality(length1, length2);

        assertTrue(isEqual);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_LengthEquality_NullQuantity(){
        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = null;
        boolean isEqual = QuantityMeasurementApp.demonstrateEquality(length1, length2);

        assertFalse(isEqual);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_WeightEquality(){
        Quantity<WeightUnit> weight1 = new Quantity<>(1500.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(1.5, WeightUnit.KILOGRAMS);
        boolean isEqual = QuantityMeasurementApp.demonstrateEquality(weight1, weight2);

        assertTrue(isEqual);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_NullQuantity(){
        Quantity<WeightUnit> weight1 = new Quantity<>(1500.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = null;
        boolean isEqual = QuantityMeasurementApp.demonstrateEquality(weight1, weight2);

        assertFalse(isEqual);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_LengthConversion(){
        Quantity<LengthUnit> length = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> converted = QuantityMeasurementApp.demonstrateConversion(length, LengthUnit.INCHES);

        Quantity<LengthUnit> expected = new Quantity<>(180.0, LengthUnit.INCHES);

        assertEquals(expected, converted);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_WeightConversion(){
        Quantity<WeightUnit> weight = new Quantity<>(15.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> converted = QuantityMeasurementApp.demonstrateConversion(weight, WeightUnit.GRAMS);

        Quantity<WeightUnit> expected = new Quantity<>(15000.0, WeightUnit.GRAMS);

        assertEquals(expected, converted);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_LengthConversion_NullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.demonstrateConversion(new Quantity<>(15.0, LengthUnit.CENTIMETERS), null);
        });
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_WeightConversion_NullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.demonstrateConversion(new Quantity<>(15.0, WeightUnit.GRAMS), null);
        });
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_LengthAddition(){
        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0, LengthUnit.YARDS);
        Quantity<LengthUnit> sum = QuantityMeasurementApp.demonstrateAddition(length1, length2);

        Quantity<LengthUnit> expected = new Quantity<>(30.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_LengthAdditionWithNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.demonstrateAddition(new Quantity<>(15.0, LengthUnit.CENTIMETERS), null);
        });
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_WeightAdddition(){
        Quantity<WeightUnit> weight1 = new Quantity<>(1500.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(1.5, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> sum = QuantityMeasurementApp.demonstrateAddition(weight1, weight2);

        Quantity<WeightUnit> expected = new Quantity<>(3000.0 , WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_WeightAdditionWithNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.demonstrateAddition(new Quantity<>(15.0, WeightUnit.GRAMS), null);
        });
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_LengthAdditionTargetUnit(){
        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(5.0, LengthUnit.YARDS);
        Quantity<LengthUnit> sum = QuantityMeasurementApp.demonstrateAddition(length1, length2, LengthUnit.INCHES);

        Quantity<LengthUnit> expected = new Quantity<>(360.0, LengthUnit.INCHES);

        assertEquals(expected, sum);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_LengthAdditionWithNullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.demonstrateAddition(new Quantity<>(15.0, LengthUnit.CENTIMETERS), new Quantity<>(5.0, LengthUnit.YARDS), null);
        });
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_WeightAdditionTargetUnit(){
        Quantity<WeightUnit> weight1 = new Quantity<>(1500.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(1.5, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> sum = QuantityMeasurementApp.demonstrateAddition(weight1, weight2, WeightUnit.KILOGRAMS);

        Quantity<WeightUnit> expected = new Quantity<>(3.0 , WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_WeightAdditionWithNullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.demonstrateAddition(new Quantity<>(15.0, WeightUnit.GRAMS), new Quantity<>(1.5, WeightUnit.KILOGRAMS), null);
        });
    }
    
}