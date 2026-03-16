package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.UnsupportedOperationsException;
import com.apps.quantitymeasurement.service.QuantityMeasurementService;
import com.apps.quantitymeasurement.units.LengthUnit;
import com.apps.quantitymeasurement.units.TemperatureUnit;
import com.apps.quantitymeasurement.units.VolumeUnit;
import com.apps.quantitymeasurement.units.WeightUnit;

public class QuantityMeasurementAppTest {

    @Test
    public void testService_CompareEquality_SameUnit_Success() throws UnsupportedOperationsException{
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<LengthUnit> quantity1 = new Quantity<>(45.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(45.0, LengthUnit.FEET);

        assertTrue(service.checkEquality(quantity1, quantity2));
    }

    @Test
    public void testService_CompareEquality_DifferentUnit_Success() throws UnsupportedOperationsException{
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<LengthUnit> quantity1 = new Quantity<>(45.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(15.0, LengthUnit.YARDS);

        assertTrue(service.checkEquality(quantity1, quantity2));
    }

    @Test
    public void testService_CompareEquality_CrossCategory_Error(){
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<WeightUnit> quantity1 = new Quantity<>(5.0, WeightUnit.KILOGRAMS);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(15.0, VolumeUnit.GALLON);

        assertThrows( UnsupportedOperationsException.class, ()->{
            service.checkEquality(quantity1, quantity2);
        });
    }

    @Test
    public void testService_Convert_Success(){
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<TemperatureUnit> quantity = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = service.convert(quantity, TemperatureUnit.FAHRENHEIT);

        Quantity<TemperatureUnit> expected = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertEquals(expected, converted);
    }

    @Test
    public void testService_Add_Success() throws UnsupportedOperationsException{
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<LengthUnit> quantity1 = new Quantity<>(14.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(3.0, LengthUnit.YARDS);
        Quantity<LengthUnit> sum = service.add(quantity1, quantity2);

        Quantity<LengthUnit> expected = new Quantity<>(23.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testService_Add_ExplicitTargetUnit_Success() throws UnsupportedOperationsException{
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<LengthUnit> quantity1 = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(3.0, LengthUnit.YARDS);
        Quantity<LengthUnit> sum = service.add(quantity1, quantity2,LengthUnit.INCHES);

        Quantity<LengthUnit> expected = new Quantity<>(168.0, LengthUnit.INCHES);

        assertEquals(expected, sum);
    }

    @Test
    public void testService_Add_UnsupportedOperation_Error(){
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<TemperatureUnit> quantity1 = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertThrows( UnsupportedOperationsException.class, ()->{
            service.add(quantity1, quantity2);
        });
    }

    @Test
    public void testService_Subtract_Success() throws UnsupportedOperationsException{
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<WeightUnit> quantity1 = new Quantity<>(19.0,  WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(3000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> sum = service.subtract(quantity1, quantity2);

        Quantity<WeightUnit> expected = new Quantity<>(16.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testService_Subtract_ExplicitTargetUnit_Success() throws UnsupportedOperationsException{
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<WeightUnit> quantity1 = new Quantity<>(9.0,  WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(3000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> sum = service.subtract(quantity1, quantity2, WeightUnit.GRAMS);

        Quantity<WeightUnit> expected = new Quantity<>(6000.0, WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testService_Subtract_UnsupportedOperation_Error(){
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<TemperatureUnit> quantity1 = new Quantity<>(13.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(32.0, TemperatureUnit.FAHRENHEIT);

        assertThrows( UnsupportedOperationsException.class, ()->{
            service.subtract(quantity1, quantity2);
        });
    }

    @Test
    public void testService_Divide_Success() throws UnsupportedOperationsException{
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<VolumeUnit> quantity1 = new Quantity<>(8.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(500, VolumeUnit.MILLILITRE);
        double ratio = service.divide(quantity1, quantity2);

        double expected = 17.0;

        assertEquals(expected, ratio, 0.001);
    }

    @Test
    public void testService_DivideByZero_Error() throws UnsupportedOperationsException{
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<VolumeUnit> quantity1 = new Quantity<>(8.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(0.0, VolumeUnit.MILLILITRE);
        
        assertThrows( ArithmeticException.class, ()->{
            service.divide(quantity1, quantity2);
        });
    }

    @Test
    public void testService_Divide_UnsupportedOperation_Error(){
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<TemperatureUnit> quantity1 = new Quantity<>(45.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(15.0, TemperatureUnit.FAHRENHEIT);

        assertThrows( UnsupportedOperationsException.class, ()->{
            service.divide(quantity1, quantity2);
        });
    }

    @Test
    public void testController_Equality_SameUnit_Success() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<LengthUnit> quantity1 = new Quantity<>(4.0, LengthUnit.CENTIMETERS);
        Quantity<LengthUnit> quantity2 = new Quantity<>(4.0, LengthUnit.CENTIMETERS);

        assertTrue(controller.checkEquality(quantity1, quantity2));
    }

    @Test
    public void testController_Equality_DifferentUnit_Success() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<TemperatureUnit> quantity1 = new Quantity<>(12.0, TemperatureUnit.FAHRENHEIT);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(262.039, TemperatureUnit.KELVIN);

        assertTrue(controller.checkEquality(quantity1, quantity2));
    }

    @Test
    public void testController_Equality_CrossCategory_Success() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<WeightUnit> quantity1 = new Quantity<>(4.0, WeightUnit.GRAMS);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(12.0, VolumeUnit.LITRE);

        assertThrows( UnsupportedOperationsException.class, ()->{
            controller.checkEquality(quantity1, quantity2);
        });
    }

    @Test 
    public void testController_Conversion_Success(){
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<TemperatureUnit> quantity = new Quantity<>(0.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = controller.convert(quantity, TemperatureUnit.KELVIN);

        Quantity<TemperatureUnit> expected = new Quantity<>(273.15, TemperatureUnit.KELVIN);

        assertEquals(expected, converted);
    }

    @Test
    public void testController_Add_Success() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<VolumeUnit> quantity1 = new Quantity<>(4.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(3000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = controller.add(quantity1, quantity2);

        Quantity<VolumeUnit> expected = new Quantity<>(7.0, VolumeUnit.LITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testController_Add_ExplicitTargetUnit_Success() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<VolumeUnit> quantity1 = new Quantity<>(4.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(8360.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = controller.add(quantity1, quantity2);

        Quantity<VolumeUnit> expected = new Quantity<>(3.397, VolumeUnit.GALLON);

        assertEquals(expected, sum);
    }

    @Test
    public void testController_Add_UnsupportedOperation_Error(){
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<TemperatureUnit> quantity1 = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(64.0, TemperatureUnit.FAHRENHEIT);

        assertThrows( UnsupportedOperationsException.class, ()->{
            controller.add(quantity1, quantity2);
        });
    }

    @Test
    public void testController_Subtract_Success() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<LengthUnit> quantity1 = new Quantity<>(85.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(25.0, LengthUnit.YARDS);
        Quantity<LengthUnit> subtract = controller.subtract(quantity1, quantity2);

        Quantity<LengthUnit> expected = new Quantity<>(10.0, LengthUnit.FEET);

        assertEquals(expected, subtract);
    }

    @Test
    public void testController_Subtract_ExplicitTargetUnit_Success() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<LengthUnit> quantity1 = new Quantity<>(132.0, LengthUnit.INCHES);
        Quantity<LengthUnit> quantity2 = new Quantity<>(8.0, LengthUnit.FEET);
        Quantity<LengthUnit> subtract = controller.subtract(quantity1, quantity2, LengthUnit.YARDS);

        Quantity<LengthUnit> expected = new Quantity<>(1.0, LengthUnit.YARDS);

        assertEquals(expected, subtract);
    }

    @Test
    public void testController_Subtract_UnsupportedOperation_Error(){
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<TemperatureUnit> quantity1 = new Quantity<>(25.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(64.0, TemperatureUnit.FAHRENHEIT);

        assertThrows( UnsupportedOperationsException.class, ()->{
            controller.subtract(quantity1, quantity2);
        });
    }

    @Test
    public void testController_Divide_Success() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<WeightUnit> quantity1 = new Quantity<>(8.5, WeightUnit.POUNDS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(500.0, WeightUnit.GRAMS);
        double ratio = controller.divide(quantity1, quantity2);

        double expected = 7.7111;

        assertEquals(expected, ratio, 0.001);
    }

    @Test
    public void testController_DivideByZero_Error() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<WeightUnit> quantity1 = new Quantity<>(2.5, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(0.0, WeightUnit.GRAMS);
        
        assertThrows( ArithmeticException.class, ()->{
            controller.divide(quantity1, quantity2);
        });
    }

    @Test
    public void testController_Divide_UnsupportedOperation_Error(){
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<TemperatureUnit> quantity1 = new Quantity<>(2.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> quantity2 = new Quantity<>(38.0, TemperatureUnit.FAHRENHEIT);

        assertThrows( UnsupportedOperationsException.class, ()->{
            controller.divide(quantity1, quantity2);
        });
    }

    @Test
    public void testLayerSeparation_ServiceIndependence(){
        QuantityMeasurementService service = new QuantityMeasurementService();

        Quantity<LengthUnit> quantity = new Quantity<>(39.0, LengthUnit.FEET);
        Quantity<LengthUnit> converted = service.convert(quantity, LengthUnit.YARDS);

        Quantity<LengthUnit> expected = new Quantity<>(13.0, LengthUnit.YARDS);

        assertEquals(expected, converted);
    }

    @Test
    public void testLayerSeparation_ControllerIndependence(){
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<VolumeUnit> quantity = new Quantity<>(39.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> converted = controller.convert(quantity, VolumeUnit.GALLON);

        Quantity<VolumeUnit> expected = new Quantity<>(10.3027,  VolumeUnit.GALLON);

        assertEquals(expected, converted);
    }

    @Test
    public void testDataFlow_ControllerToService() throws UnsupportedOperationsException{
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<WeightUnit> quantity1 = new Quantity<>(15.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(26.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> sum = controller.add(quantity1, quantity2);

        Quantity<WeightUnit> expected = new Quantity<>(41.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test 
    public void testDataFlow_ServiceToController(){
        QuantityMeasurementController controller = new QuantityMeasurementController();

        Quantity<TemperatureUnit> quantity = new Quantity<>(15.0, TemperatureUnit.CELSIUS);
        Quantity<TemperatureUnit> converted = controller.convert(quantity, TemperatureUnit.KELVIN);
        
        Quantity<TemperatureUnit> expected = new Quantity<>(288.15, TemperatureUnit.KELVIN);

        assertEquals(expected, converted);
    }

    @Test
    public void testService_NullRepository_Rejection(){
        assertThrows( IllegalArgumentException.class, ()->{
            new QuantityMeasurementService(null);
        } );
    }

    @Test
    public void testService_NullQuantityEntity_Rejection(){
        assertThrows( IllegalArgumentException.class, ()->{
            new QuantityMeasurementService().convert(null, LengthUnit.CENTIMETERS);
        } );
    }

    @Test
    public void testController_NullService_Prevention(){
        assertThrows( IllegalArgumentException.class, ()->{
            new QuantityMeasurementController(null);
        } );
    }
}
