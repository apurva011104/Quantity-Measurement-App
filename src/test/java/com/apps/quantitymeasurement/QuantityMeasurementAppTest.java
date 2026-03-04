package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testIMeasurableInterface_VolumeUnitImplementation_GetConversionFactor(){
        IMeasurable unit = VolumeUnit.LITRE;
        double actual = unit.getConversionFactor();
        double expected = 1.0;

        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void testIMeasurableInterface_VolumeUnitImplementation_ConvertToBaseUnit(){
        IMeasurable unit =  VolumeUnit.MILLILITRE;
        double actual = unit.convertToBaseUnit(1000.0);
        double expected = 1.0;

        assertEquals(expected, actual , 0.0001);
    }

    @Test
    public void testIMeasurableInterface_VolumeUnitImplementation_ConvertFromBaseUnit(){
        IMeasurable unit =  VolumeUnit.GALLON;
        double actual = unit.convertFromBaseUnit(15.0);
        double expected = 3.96;

        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testGenericQuantity_VolumeOperations_Equality(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);

        assertTrue(volume1.equals(volume2));
    } 

    @Test
    public void testGenericQuantity_VolumeOperations_EqualityWithNull(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = null;

        assertFalse(volume1.equals(volume2));
    } 

    @Test
    public void testCrossCategoryPrevention_VolumeVsLength_Equality(){
        Quantity<VolumeUnit> volume = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.5, LengthUnit.CENTIMETERS);

        assertFalse(volume.equals(length));
    }

    @Test
    public void testCrossCategoryPrevention_VolumeVsWeight_Equality(){
        Quantity<VolumeUnit> volume = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> weight = new Quantity<>(1.5, WeightUnit.KILOGRAMS);

        assertFalse(volume.equals(weight));
    }

    @Test
    public void testGenericQuantity_VolumeOperations_Conversion(){
        Quantity<VolumeUnit> volume = new Quantity<>(6.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> converted = volume.convertTo(VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> expected = new Quantity<>(6000.0, VolumeUnit.MILLILITRE);

        assertEquals(expected, converted);
    }

    @Test
    public void testGenericQuantity_VolumeOperations_Conversion_TargetNullUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, VolumeUnit.LITRE).convertTo(null);
        });
    }

    @Test
    public void testGenericQuantity_VolumeOperations_Addition(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> sum = volume1.add(volume2);

        Quantity<VolumeUnit> expected = new Quantity<>(30.0, VolumeUnit.LITRE);

        assertEquals(expected, sum);
    }

    @Test 
    public  void testGenericQuantity_VolumeOperations_AdditionWithNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, VolumeUnit.LITRE).add(null);
        });
    }

    @Test
    public void testGenericQuantity_VolumeOperations_AdditionTargetUnit(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> sum = volume1.add(volume2, VolumeUnit.GALLON);

        Quantity<VolumeUnit> expected = new Quantity<>(7.92516, VolumeUnit.GALLON);

        assertEquals(expected, sum);
    }

    @Test 
    public  void testGenericQuantity_VolumeOperations_AdditionWithNullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, VolumeUnit.LITRE).add( new Quantity<>(15000.0, VolumeUnit.MILLILITRE),null);
        });
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_VolumeEquality(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);
        boolean isEqual = QuantityMeasurementApp.demonstrateEquality(volume1, volume2);

        assertTrue(isEqual);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_VolumeEquality_NullQuantity(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = null;
        boolean isEqual = QuantityMeasurementApp.demonstrateEquality(volume1, volume2);

        assertFalse(isEqual);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_VolumeConversion(){
        Quantity<VolumeUnit> length = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> converted = QuantityMeasurementApp.demonstrateConversion(length, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> expected = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);

        assertEquals(expected, converted);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_VolumeConversion_NullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.demonstrateConversion(new Quantity<>(15000.0, VolumeUnit.MILLILITRE), null);
        });
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_VolumeAddition(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = QuantityMeasurementApp.demonstrateAddition(volume1, volume2);

        Quantity<VolumeUnit> expected = new Quantity<>(30.0, VolumeUnit.LITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_VolumeAdditionWithNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.demonstrateAddition(new Quantity<>(15.0, VolumeUnit.LITRE), null);
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
    public void testQuantityMeasurementApp_SimplifiedDemonstration_VolumeAdditionTargetUnit(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = QuantityMeasurementApp.demonstrateAddition(volume1, volume2, VolumeUnit.GALLON);

        Quantity<VolumeUnit> expected = new Quantity<>(7.92516, VolumeUnit.GALLON);

        assertEquals(expected, sum);
    }

    @Test
    public void testQuantityMeasurementApp_SimplifiedDemonstration_VolumeAdditionWithNullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            QuantityMeasurementApp.demonstrateAddition(new Quantity<>(15.0, VolumeUnit.LITRE), new Quantity<>(15000.0, VolumeUnit.MILLILITRE), null);
        });
    }
    
}