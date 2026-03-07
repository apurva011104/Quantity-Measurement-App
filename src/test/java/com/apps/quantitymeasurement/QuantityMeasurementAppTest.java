package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class QuantityMeasurementAppTest {

    @Test
    public void testTemperatureQuantity_ObjectFormation(){
        assertDoesNotThrow(()->{
            new Quantity<>(15.0, TemperatureUnit.CELSIUS);
        });
    }

    @Test
    public void testTemperatureQuantity_NonFiniteValue(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Quantity<>(Double.POSITIVE_INFINITY, TemperatureUnit.CELSIUS);
        });
    }

    @Test
    public void testTemperatureQuantity_NullUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            Quantity<TemperatureUnit> quantity = new Quantity<>(Double.POSITIVE_INFINITY, null);
        });
    }

    @Test
    public void testTemperatureEquality_CelsiusToCelsius_SameValue(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(15.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(15.0, TemperatureUnit.CELSIUS);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void testTemperatureEquality_FahrenheitToFahrenheit_SameValue(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void testTemperatureEquality_KelvinToKelvin_SameValue(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(300.0, TemperatureUnit.KELVIN);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(300.0, TemperatureUnit.KELVIN);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(15.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(59.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void testTemperatureEquality_CelsiusToKelvin(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(15.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(288.15, TemperatureUnit.KELVIN);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void testTemperatureEquality_FahrenheitToKelvin(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(25.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(269.261, TemperatureUnit.KELVIN);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void testTemperatureEquality_CelsiusToFahrenheit_Negative40Equal(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(-40.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(-40.0, TemperatureUnit.FAHRENHEIT);

        assertTrue(quantity1.equals(quantity2));
    }

    @Test
    public void testTemperatureEquality_SymmetricProperty(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(15.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(59.0, TemperatureUnit.FAHRENHEIT);

        boolean result1 = quantity1.equals(quantity2);
        boolean result2 = quantity2.equals(quantity1);

        assertTrue(result1==result2);
    }

    @Test
    public void testTemperatureEquality_ReflexiveProperty(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(300.0, TemperatureUnit.KELVIN);

        assertTrue(quantity1.equals(quantity1));
    }

    @Test
    public void testTemperatureEquality_NullValue(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(300.0, TemperatureUnit.KELVIN);

        assertFalse(quantity1.equals(null));
    }

    @ParameterizedTest
    @ValueSource(doubles={-40.0, -20.0, 0.0, 25.0, 50.0, 100.0})
    public void testTemperatureConversion_CelsiusToFahrenheit_VariousValues(double value){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(value, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.FAHRENHEIT);
        double expected = TemperatureUnit.FAHRENHEIT.convertFromBaseUnit(value);
        assertEquals(expected, converted.getValue(), 0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles={-40.0, -4.0, 0.0, 77.0, 122.0, 212.0})
    public void testTemperatureConversion_FahrenheitToCelsius_VariousValues(double value){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(value, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.CELSIUS);
        double expected = TemperatureUnit.FAHRENHEIT.convertToBaseUnit(value);
        assertEquals(expected, converted.getValue(), 0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles={-40.0, -20.0, 0.0, 25.0, 50.0, 100.0})
    public void testTemperatureConversion_CelsiusToKelvin_VariousValues(double value){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(value, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.KELVIN);
        double expected = TemperatureUnit.KELVIN.convertFromBaseUnit(value);
        assertEquals(expected, converted.getValue(), 0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles={233.15, 253.15, 273.15, 298.15, 323.15, 373.15})
    public void testTemperatureConversion_KelvinToCelsius_VariousValues(double value){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(value, TemperatureUnit.KELVIN);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.CELSIUS);
        double expected = TemperatureUnit.KELVIN.convertToBaseUnit(value);
        assertEquals(expected, converted.getValue(), 0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles={-40.0, -4.0, 0.0, 77.0, 122.0, 212.0})
    public void testTemperatureConversion_FahrenheitToKelvin_VariousValues(double value){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(value, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.KELVIN);
        double expected = TemperatureUnit.KELVIN.convertFromBaseUnit(TemperatureUnit.FAHRENHEIT.convertToBaseUnit(value));
        assertEquals(expected, converted.getValue(), 0.01);
    }

    @ParameterizedTest
    @ValueSource(doubles={233.15, 253.15, 273.15, 298.15, 323.15, 373.15})
    public void testTemperatureConversion_KelvinToFahrenheit_VariousValues(double value){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(value, TemperatureUnit.KELVIN);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.FAHRENHEIT);
        double expected = TemperatureUnit.FAHRENHEIT.convertFromBaseUnit(TemperatureUnit.KELVIN.convertToBaseUnit(value));
        assertEquals(expected, converted.getValue(), 0.01);
    }

    @Test
    public void testTemperatureConversion_RoundTrip_PreservesValue(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> preserved = converted.convertTo(TemperatureUnit.CELSIUS);

        assertTrue(quantity1.equals(preserved));
    }

    @Test
    public void testTemperatureConversion_SameUnit(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.CELSIUS);

        assertTrue(quantity1.equals(converted));
    }

    @Test
    public void testTemperatureConversion_ZeroValue(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.FAHRENHEIT);
        double expected = TemperatureUnit.FAHRENHEIT.convertFromBaseUnit(0.0);

        assertEquals(expected, converted.getValue(), 0.01);
    }

    @Test
    public void testTemperatureConversion_NegativeValues(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(-20.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.FAHRENHEIT);
        double expected = TemperatureUnit.FAHRENHEIT.convertFromBaseUnit(-20.0);

        assertEquals(expected, converted.getValue(), 0.01);
    }

    @Test
    public void testTemperatureConversion_LargeValues(){
        Quantity<TemperatureUnit> quantity1 = new Quantity<>(1000.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = quantity1.convertTo(TemperatureUnit.FAHRENHEIT);
        double expected = TemperatureUnit.FAHRENHEIT.convertFromBaseUnit(1000.0);

        assertEquals(expected, converted.getValue(), 0.01);
    }

    @Test
    public void testTemperatureUnsupportedOperation_Add(){
        assertThrows(UnsupportedOperationsException.class, ()->{
            new Quantity<>(25.0, TemperatureUnit.CELSIUS).add(new Quantity<>(50.0, TemperatureUnit.FAHRENHEIT));
        });
    }

    @Test
    public void testTemperatureUnsupportedOperation_Subtract(){
        assertThrows(UnsupportedOperationsException.class, ()->{
            new Quantity<>(25.0, TemperatureUnit.CELSIUS).subtract(new Quantity<>(50.0, TemperatureUnit.FAHRENHEIT));
        });
    }

    @Test
    public void testTemperatureUnsupportedOperation_Divide(){
        assertThrows(UnsupportedOperationsException.class, ()->{
            new Quantity<>(25.0, TemperatureUnit.CELSIUS).divide(new Quantity<>(50.0, TemperatureUnit.FAHRENHEIT));
        });
    }

    @Test
    public void testOperationSupportMethods_LengthUnit(){
        assertTrue(LengthUnit.FEET.supportsArithmetic());
    }

    @Test
    public void testOperationSupportMethods_WeightUnit(){
        assertTrue(WeightUnit.GRAMS.supportsArithmetic());
    }

    @Test
    public void testOperationSupportMethods_VolumeUnit(){
        assertTrue(VolumeUnit.GALLON.supportsArithmetic());
    }

    @Test
    public void testOperationSupportMethods_TemperatureUnit(){
        assertFalse(TemperatureUnit.CELSIUS.supportsArithmetic());
    }
}