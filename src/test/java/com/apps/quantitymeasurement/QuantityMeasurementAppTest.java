package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testGenericQuantity_LengthOperations_Subtraction(){
        Quantity<LengthUnit> length1 = new Quantity<>(25.0, LengthUnit.FEET);
        Quantity<LengthUnit> weight2 = new Quantity<>(3.0, LengthUnit.YARDS);

        Quantity<LengthUnit> difference = length1.subtract(weight2);

        Quantity<LengthUnit> expected = new Quantity<>(16.0, LengthUnit.FEET);

        assertEquals(expected, difference);
    }

    @Test 
    public void testGenericQuantity_LengthOperations_SubtractionWithNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, LengthUnit.CENTIMETERS).subtract(null);
        });
    }

    @Test
    public void testGenericQuantity_LengthOperations_SubtractionTargetUnit(){
        Quantity<LengthUnit> length1 = new Quantity<>(25.0, LengthUnit.FEET);
        Quantity<LengthUnit> weight2 = new Quantity<>(6.0, LengthUnit.YARDS);

        Quantity<LengthUnit> difference = length1.subtract(weight2, LengthUnit.INCHES);

        Quantity<LengthUnit> expected = new Quantity<>(84.0, LengthUnit.INCHES);

        assertEquals(expected, difference);
    }

    @Test 
    public void testGenericQuantity_LengthOperations_SubtractionWithNullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, LengthUnit.CENTIMETERS).subtract(new Quantity<>(6.0, LengthUnit.YARDS),null);
        });
    }

    @Test
    public void testGenericQuantity_LengthOperations_Division(){
        Quantity<LengthUnit> length1 = new Quantity<>(27.0, LengthUnit.FEET);
        Quantity<LengthUnit> weight2 = new Quantity<>(3.0, LengthUnit.YARDS);

        double divide = length1.divide(weight2);

        double expected = 3.0;

        assertEquals(expected, divide);
        
    }

    @Test 
    public void testGenericQuantity_LengthOperations_DivisionWithNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, LengthUnit.CENTIMETERS).divide(null);
        });
    }

    @Test
    public void testGenericQuantity_WeightOperations_Subtraction(){
        Quantity<WeightUnit> weight1 = new Quantity<>(25.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(15000.0, WeightUnit.GRAMS);

        Quantity<WeightUnit> difference = weight1.subtract(weight2);

        Quantity<WeightUnit> expected = new Quantity<>(10.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, difference);
    }

    @Test
    public void testGenericQuantity_WeightOperations_SubtractionTargetUnit(){
        Quantity<WeightUnit> weight1 = new Quantity<>(25.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(15000.0, WeightUnit.GRAMS);

        Quantity<WeightUnit> difference = weight1.subtract(weight2, WeightUnit.POUNDS);

        Quantity<WeightUnit> expected = new Quantity<>(22.0462, WeightUnit.POUNDS);

        assertEquals(expected, difference);
    }

    @Test 
    public  void testGenericQuantity_WeightOperations_SubtractionWithNullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, WeightUnit.GRAMS).subtract(new Quantity<>(10.0, WeightUnit.GRAMS),null);
        });
    }

    @Test
    public void testGenericQuantity_WeightOperations_Division(){
        Quantity<WeightUnit> weight1 = new Quantity<>(9000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(3.0, WeightUnit.KILOGRAMS);

        double divide = weight1.divide(weight2);

        double expected = 3.0;

        assertEquals(expected, divide);
        
    }

    @Test 
    public void testGenericQuantity_WeightOperations_DivisionWithNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0,WeightUnit.GRAMS).divide(null);
        });
    }

   
    @Test
    public void testGenericQuantity_WeightOperations_DivisionWithZero(){
        assertThrows(ArithmeticException.class, ()->{
            new Quantity<>(6.0, WeightUnit.GRAMS).divide(new Quantity<>(0, WeightUnit.GRAMS));
        });
    }

    @Test
    public void testGenericQuantity_VolumeOperations_Subtraction(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(25.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> difference = volume1.subtract(volume2);

        Quantity<VolumeUnit> expected = new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(expected, difference);
    }

    @Test 
    public  void testGenericQuantity_VolumeOperations_SubtractionWithNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, VolumeUnit.LITRE).subtract(null);
        });
    }

    @Test
    public void testGenericQuantity_VolumeOperations_SubtractionTargetUnit(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(25.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(15000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> difference = volume1.subtract(volume2, VolumeUnit.GALLON);

        Quantity<VolumeUnit> expected = new Quantity<>(2.64172, VolumeUnit.GALLON);

        assertEquals(expected, difference);
    }

    @Test 
    public  void testGenericQuantity_VolumeOperations_SubtractionWithNullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, VolumeUnit.LITRE).subtract( new Quantity<>(1500.0, VolumeUnit.MILLILITRE),null);
        });
    }

    @Test
    public void testGenericQuantity_VolumeOperations_Division(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(9000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(3.0, VolumeUnit.LITRE);

        double divide = volume1.divide(volume2);

        double expected = 3.0;

        assertEquals(expected, divide);
        
    }

    @Test 
    public void testGenericQuantity_VolumeOperations_DivisionWithNull(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(6.0, VolumeUnit.GALLON).divide(null);
        });
    }

   
    @Test
    public void testGenericQuantity_DivisionWithZero(){
        assertThrows(ArithmeticException.class, ()->{
            new Quantity<>(6.0, VolumeUnit.GALLON).divide(new Quantity<>(0, VolumeUnit.GALLON));
        });
    }

    @Test
    public void testSubtraction_ResultingInNegative(){
        Quantity<WeightUnit> weight1 = new Quantity<>(3.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(9000.0, WeightUnit.GRAMS);
        
        Quantity<WeightUnit> difference = weight1.subtract(weight2);

        Quantity<WeightUnit> expected = new Quantity<>(-6.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, difference);
        
    }
    
    @Test
    public void testSubtraction_ResultingInZero(){
        Quantity<WeightUnit> weight1 = new Quantity<>(3.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(3000.0, WeightUnit.GRAMS);
        
        Quantity<WeightUnit> difference = weight1.subtract(weight2);

        Quantity<WeightUnit> expected = new Quantity<>(0.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, difference);
        
    }

    @Test
    public void testSubtraction_WithZeroOperand(){
        Quantity<WeightUnit> weight1 = new Quantity<>(3.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(0.0, WeightUnit.GRAMS);
        
        Quantity<WeightUnit> difference = weight1.subtract(weight2);

        Quantity<WeightUnit> expected = new Quantity<>(3.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, difference);
        
    }

    @Test
    public void testSubtraction_WithNegativeValues(){
        Quantity<WeightUnit> weight1 = new Quantity<>(3.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(-9000.0, WeightUnit.GRAMS);
        
        Quantity<WeightUnit> difference = weight1.subtract(weight2);

        Quantity<WeightUnit> expected = new Quantity<>(12.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, difference);
        
    }

    @Test
    public void testSubtractionAddition_Inverse(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(9.0, VolumeUnit.LITRE );
        Quantity<VolumeUnit> volume2 = new Quantity<>(3000.0, VolumeUnit.MILLILITRE);
        
        Quantity<VolumeUnit> result= volume1.add(volume2).subtract(volume2);

        assertTrue(result.equals(volume1));
        
    }
    
    @Test
    public void testSubtraction_NonCommutative(){
        Quantity<WeightUnit> weight1 = new Quantity<>(3.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(1500.0, WeightUnit.GRAMS);
        
        Quantity<WeightUnit> difference1 = weight1.subtract(weight2);
        Quantity<WeightUnit> difference2 = weight2.subtract(weight1);

        assertFalse(difference1.equals(difference2));
        
    }

    @Test
    public void testSubtraction_WithLargeValues(){
        Quantity<WeightUnit> weight1 = new Quantity<>(1e6, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(5e5, WeightUnit.KILOGRAMS);
        
        Quantity<WeightUnit> difference = weight1.subtract(weight2);

        Quantity<WeightUnit> expected = new Quantity<>(5e5, WeightUnit.KILOGRAMS);

        assertEquals(expected, difference);
    }

    @Test
    public void testSubtraction_WithSmallValues(){
        Quantity<WeightUnit> weight1 = new Quantity<>(0.001, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> weight2 = new Quantity<>(0.5, WeightUnit.GRAMS);
        
        Quantity<WeightUnit> difference = weight1.subtract(weight2);

        Quantity<WeightUnit> expected = new Quantity<>(0.0005, WeightUnit.KILOGRAMS);

        assertEquals(expected, difference);
    }

    @Test
    public void testDivision_RatioGreaterThanOne(){
        Quantity<LengthUnit> length1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(144.0, LengthUnit.INCHES);

        double ratio = length1.divide(length2);

        assertTrue( ratio > 1 );

    }

    @Test
    public void testDivision_RatioLessThanOne(){
        Quantity<LengthUnit> length1 = new Quantity<>(144.0, LengthUnit.INCHES);
        Quantity<LengthUnit> length2 = new Quantity<>(15.0, LengthUnit.FEET);

        double ratio = length1.divide(length2);

        assertTrue( ratio < 1 );
    }

    @Test
    public void testDivision_RatioEqualToOne(){
        Quantity<LengthUnit> length1 = new Quantity<>(12.0, LengthUnit.FEET);
        Quantity<LengthUnit> length2 = new Quantity<>(144.0, LengthUnit.INCHES);

        double ratio = length1.divide(length2);

        assertTrue( ratio == 1 );
    }

    @Test
    public void testDivision_NonCommutative(){
        Quantity<VolumeUnit> volume1 = new Quantity<>(9000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(3.0, VolumeUnit.LITRE);
        
        double ratio1 = volume1.divide(volume2);
        double ratio2 = volume2.divide(volume1);

        assertFalse(ratio1 == ratio2);
        
    }

    @Test
    public void testOperationsIntegration(){

        Quantity<VolumeUnit> volume1 = new Quantity<>(9000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume2 = new Quantity<>(6.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> volume3 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> volume4 = new Quantity<>(7.0, VolumeUnit.LITRE);
        
        double ratio = volume1.add(volume2).subtract(volume3).divide(volume4);
        double expected = 2.0;

        assertEquals(expected, ratio);
        
    }
}