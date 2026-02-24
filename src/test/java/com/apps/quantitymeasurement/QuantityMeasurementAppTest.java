package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {


    @Test
    public void testLengthUnitEnum_InchesConstant(){
        double conversionFactor = LengthUnit.INCHES.getConversionFactor();
        double expected = 1.0;

        assertEquals( expected , conversionFactor);
    }

    @Test
    public void testLengthUnitEnum_FeetConstant(){
        double conversionFactor = LengthUnit.FEET.getConversionFactor();
        double expected = 12.0;

        assertEquals( expected , conversionFactor);
    }

    @Test
    public void testLengthUnitEnum_YardsConstant(){
        double conversionFactor = LengthUnit.YARDS.getConversionFactor();
        double expected = 36.0;

        assertEquals( expected , conversionFactor);
    }
    
    @Test
    public void testLengthUnitEnum_CentimetersConstant(){
        double conversionFactor = Math.round(LengthUnit.CENTIMETERS.getConversionFactor() * 1000.0) / 1000.0;
        double expected = 0.394;

        assertEquals( expected , conversionFactor);
    }

    @Test
    public void testConvertToBaseUnit_InchesToInches(){
        double value = 15.0;
        double valueInBaseUnit = LengthUnit.INCHES.convertToBaseUnit(value);
        double expected = value * LengthUnit.INCHES.getConversionFactor();

        assertEquals(expected, valueInBaseUnit);
    }

    @Test
    public void testConvertToBaseUnit_FeetToInches(){
        double value = 15.0;
        double valueInBaseUnit = LengthUnit.FEET.convertToBaseUnit(value);
        double expected = value * LengthUnit.FEET.getConversionFactor();

        assertEquals(expected, valueInBaseUnit);
    }

    @Test
    public void testConvertToBaseUnit_YardsToInches(){
        double value = 15.0;
        double valueInBaseUnit = LengthUnit.YARDS.convertToBaseUnit(value);
        double expected = value * LengthUnit.YARDS.getConversionFactor();

        assertEquals(expected, valueInBaseUnit);
    }

    @Test
    public void testConvertToBaseUnit_CentimetersToInches(){
        double value = 15.0;
        double valueInBaseUnit = LengthUnit.CENTIMETERS.convertToBaseUnit(value);
        double expected = value * LengthUnit.CENTIMETERS.getConversionFactor();

        assertEquals(expected, valueInBaseUnit);
    }

    @Test 
    public void testConvertFromBaseUnit_InchesToInches(){
        double valueInBaseUnit = 15.0;
        double valueFromBaseUnit = LengthUnit.INCHES.convertFromBaseUnit(valueInBaseUnit);
        double expected = valueInBaseUnit / LengthUnit.INCHES.getConversionFactor();

        assertEquals(expected, valueFromBaseUnit);
    }

    @Test 
    public void testConvertFromBaseUnit_InchesToFeet(){
        double valueInBaseUnit = 15.0;
        double valueFromBaseUnit = LengthUnit.FEET.convertFromBaseUnit(valueInBaseUnit);
        double expected = valueInBaseUnit / LengthUnit.FEET.getConversionFactor();

        assertEquals(expected, valueFromBaseUnit);
    }

    @Test 
    public void testConvertFromBaseUnit_InchesToYards(){
        double valueInBaseUnit = 15.0;
        double valueFromBaseUnit = LengthUnit.YARDS.convertFromBaseUnit(valueInBaseUnit);
        double expected = valueInBaseUnit / LengthUnit.YARDS.getConversionFactor();

        assertEquals(expected, valueFromBaseUnit);
    }

    @Test 
    public void testConvertFromBaseUnit_InchesToCentimeters(){
        double valueInBaseUnit = 15.0;
        double valueFromBaseUnit = LengthUnit.CENTIMETERS.convertFromBaseUnit(valueInBaseUnit);
        double expected = valueInBaseUnit / LengthUnit.CENTIMETERS.getConversionFactor();

        assertEquals(expected, valueFromBaseUnit);
    }

    @Test
    public void testQuantityLengthRefactored_Equality(){
        Length length1 = new Length(6.0, LengthUnit.FEET);
        Length length2 = new Length(2.0, LengthUnit.YARDS);

        assertTrue(length1.equals(length2));
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo(){
        Length length = new Length(6.0, LengthUnit.FEET);
        Length convertedLength = length.convertTo(LengthUnit.YARDS);
        Length expected = new Length(2.0, LengthUnit.YARDS);

        assertEquals(expected, convertedLength);
    }

    @Test 
    public void testQuantityLengthRefactored_Add(){
        Length length1 = new Length(6.0, LengthUnit.FEET);
        Length length2 = new Length(2.0, LengthUnit.YARDS);

        Length sum = length1.add(length2);

        Length expected = new Length(12.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test 
    public void testQuantityLengthRefactored_AddWithTargetUnit(){
        Length length1 = new Length(6.0, LengthUnit.FEET);
        Length length2 = new Length(2.0, LengthUnit.YARDS);

        Length sum = length1.add(length2, LengthUnit.YARDS);

        Length expected = new Length(4.0, LengthUnit.YARDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testQuantityLengthRefactored_NullUnit(){
        assertThrows( IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    @Test
    public void testQuantityLengthRefactored_InvalidValue(){
        assertThrows( IllegalArgumentException.class, () -> {
            new Length(Double.NaN, LengthUnit.FEET);
        });
    }


}