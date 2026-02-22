package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.Length.LengthUnit;


public class QuantityMeasurementAppTest {

    @Test
    public void testConversion_FeetToInches(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        double valueExpected = 12.0;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test
    public void testConversion_InchesToFeet(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(24.0, LengthUnit.INCHES, LengthUnit.FEET);
        double valueExpected = 2.0;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test
    public void testConversion_YardsToInches(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(1.0, LengthUnit.YARDS, LengthUnit.INCHES);
        double valueExpected = 36.0;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test
    public void testConversion_InchesToYards(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(72.0, LengthUnit.INCHES, LengthUnit.YARDS);
        double valueExpected = 2.0;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test
    public void testConversion_CentimetersToInches(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        double valueExpected = 1.0;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test
    public void testConversion_InchesToCentimeters(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(1.0, LengthUnit.INCHES, LengthUnit.CENTIMETERS);
        double valueExpected = 2.54;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test
    public void testConversion_FeetToYards(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(6.0, LengthUnit.FEET, LengthUnit.YARDS);
        double valueExpected = 2.0;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test
    public void testConversion_YardsToFeet(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(1.0, LengthUnit.YARDS, LengthUnit.FEET);
        double valueExpected = 3.0;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test
    public void testConversion_CentimetersToYards(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(91.44, LengthUnit.CENTIMETERS, LengthUnit.YARDS);
        double valueExpected = 1.0;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test
    public void testConversion_YardsToCentimeters(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(1.0, LengthUnit.YARDS, LengthUnit.CENTIMETERS);
        double valueExpected = 91.44;
        assertEquals(valueExpected, converted.getValue());
    }

    @Test 
    public void testConversion_RoundTrip_PreservesValue(){
        double value = 12.0;
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(value, LengthUnit.FEET , LengthUnit.YARDS);
        Length convertedToOriginal = QuantityMeasurementApp.demonstrateLengthConversion(converted, LengthUnit.FEET);

        assertEquals(value,convertedToOriginal.getValue());
    }

    @Test
    public void testConversion_ZeroValue(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);

        assertEquals(0.0, converted.getValue());
    }

    @Test
    public void testConversion_NegativeValue(){
        Length converted = QuantityMeasurementApp.demonstrateLengthConversion(-1.0, LengthUnit.FEET, LengthUnit.INCHES);
        double expectedValue = -12.0;

        assertEquals(expectedValue, converted.getValue());
    }

    @Test
    public void testConversion_InvalidUnit_Throws(){
        assertThrows(IllegalArgumentException.class ,()-> {
            QuantityMeasurementApp.demonstrateLengthConversion(-1.0, LengthUnit.FEET, null);
        });
    }

    @Test
    public void testConversion_NaN_Throws(){
        assertThrows(IllegalArgumentException.class ,()-> {
            QuantityMeasurementApp.demonstrateLengthConversion(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES);
        });
    }

    @Test
    public void testConversion_NegativeInfinity_Throws(){
        assertThrows(IllegalArgumentException.class ,()-> {
            QuantityMeasurementApp.demonstrateLengthConversion(Double.NEGATIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES);
        });
    }

    @Test
    public void testConversion_PositiveInfinity_Throws(){
        assertThrows(IllegalArgumentException.class ,()-> {
            QuantityMeasurementApp.demonstrateLengthConversion(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES);
        });
    }
}
