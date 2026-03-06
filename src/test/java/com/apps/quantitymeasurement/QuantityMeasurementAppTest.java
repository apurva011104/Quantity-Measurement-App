package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testValidation_NullOperand_AdditionOperation(){
        assertThrows( IllegalArgumentException.class, ()->{
            new Quantity<>(15.0, LengthUnit.CENTIMETERS).add(null);
        });
    }

    @Test
    public void testValidation_NullOperand_SubtractionOperation(){
        assertThrows( IllegalArgumentException.class, ()->{
            new Quantity<>(15.0, WeightUnit.GRAMS).subtract(null);
        });
    }

    @Test
    public void testValidation_NullOperand_DivisionOperation(){
        assertThrows( IllegalArgumentException.class, ()->{
            new Quantity<>(15.0, VolumeUnit.GALLON).divide(null);
        });
    }

    @Test
    public void testValidation_NullTargetUnit_AdditionOperation(){
        assertThrows( IllegalArgumentException.class, ()->{
            new Quantity<>(15.0, LengthUnit.CENTIMETERS).add(new Quantity<>(15.0, LengthUnit.FEET),null);
        });
    }

    @Test
    public void testValidation_NullTargetUnit_SubtractionOperation(){
        assertThrows( IllegalArgumentException.class, ()->{
            new Quantity<>(150.0, WeightUnit.GRAMS).subtract(new Quantity<>(15.0, WeightUnit.KILOGRAMS), null);
        });
    }

    @Test
    public void testRefactoring_Add_DelegatesViaHelper(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(25.0, LengthUnit.FEET);
        Quantity<LengthUnit> sum = quantity1.add(quantity2);

        Quantity<LengthUnit> expected = new Quantity<>(40.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testRefactoring_Subtract_DelegatesViaHelper(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(35.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(25.0, WeightUnit.KILOGRAMS);

        Quantity<WeightUnit> diff = quantity1.subtract(quantity2);
        Quantity<WeightUnit> expected = new Quantity<>(10.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, diff);
    }

    @Test
    public void testRefactoring_Divide_DelegatesViaHelper(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(36.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(12.0, VolumeUnit.LITRE);

        double divide = quantity1.divide(quantity2);
        double expected = 3.0;

        assertEquals(expected, divide);
    }

    @Test
    public void testArithmeticOperation_Add_EnumComputation(){
        double actual = Quantity.ArithmeticOperation.ADD.compute(5.0, 10.0);
        double expected = 15.0;

        assertEquals(expected, actual);
    }

    @Test
    public void testArithmeticOperation_Subtract_EnumComputation(){
        double actual = Quantity.ArithmeticOperation.SUBTRACT.compute(25.0, 10.0);
        double expected = 15.0;

        assertEquals(expected, actual);
    }

    @Test
    public void testArithmeticOperation_Divide_EnumComputation(){
        double actual = Quantity.ArithmeticOperation.DIVIDE.compute(50.0, 5.0);
        double expected = 10.0;

        assertEquals(expected, actual);
    }

    @Test 
    public void testArithmeticOperation_DivideByZero_EnumThrows(){
        assertThrows(ArithmeticException.class, ()->{
            Quantity.ArithmeticOperation.DIVIDE.compute(50.0, 0.0);
        });
    }

    @Test
    public void testOperation_Addition_LengthQuantity_SameUnits(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(25.0, LengthUnit.FEET);

        Quantity<LengthUnit> sum = quantity1.add(quantity2);

        Quantity<LengthUnit> expected= new Quantity<>(40.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_LengthQuantity_CrossUnits(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(4.0, LengthUnit.YARDS);

        Quantity<LengthUnit> sum = quantity1.add(quantity2);

        Quantity<LengthUnit> expected= new Quantity<>(27.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_WeightQuantity_SameUnits(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(15.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(25.0, WeightUnit.KILOGRAMS);

        Quantity<WeightUnit> sum = quantity1.add(quantity2);

        Quantity<WeightUnit> expected= new Quantity<>(40.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_WeightQuantity_CrossUnits(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(15.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(25000.0, WeightUnit.GRAMS);

        Quantity<WeightUnit> sum = quantity1.add(quantity2);

        Quantity<WeightUnit> expected= new Quantity<>(40.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_VolumeQuantity_SameUnits(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(25.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> sum = quantity1.add(quantity2);

        Quantity<VolumeUnit> expected= new Quantity<>(40.0, VolumeUnit.LITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_VolumeQuantity_CrossUnits(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(25000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> sum = quantity1.add(quantity2);

        Quantity<VolumeUnit> expected= new Quantity<>(40.0, VolumeUnit.LITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Feet(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(6.0, LengthUnit.INCHES);
        Quantity<LengthUnit> quantity2 = new Quantity<>(18.0, LengthUnit.INCHES);

        Quantity<LengthUnit> sum = quantity1.add(quantity2, LengthUnit.FEET);

        Quantity<LengthUnit> expected= new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Yards(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(36.0, LengthUnit.INCHES);
        Quantity<LengthUnit> quantity2 = new Quantity<>(3.0, LengthUnit.FEET);

        Quantity<LengthUnit> sum = quantity1.add(quantity2, LengthUnit.YARDS);

        Quantity<LengthUnit> expected= new Quantity<>(2.0, LengthUnit.YARDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Inch(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(4.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(2.0, LengthUnit.YARDS);

        Quantity<LengthUnit> sum = quantity1.add(quantity2, LengthUnit.INCHES);

        Quantity<LengthUnit> expected= new Quantity<>(120.0, LengthUnit.INCHES);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Centimeter(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(2.0, LengthUnit.INCHES);
        Quantity<LengthUnit> quantity2 = new Quantity<>(3.0, LengthUnit.INCHES);

        Quantity<LengthUnit> sum = quantity1.add(quantity2, LengthUnit.CENTIMETERS);

        Quantity<LengthUnit> expected= new Quantity<>(12.7, LengthUnit.CENTIMETERS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Kilogram(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(2000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(3000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> sum = quantity1.add(quantity2, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> expected= new Quantity<>(5.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Gram(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(2.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(3.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> sum = quantity1.add(quantity2, WeightUnit.GRAMS);
        Quantity<WeightUnit> expected= new Quantity<>(5000.0, WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Pound(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(2.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(3000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> sum = quantity1.add(quantity2, WeightUnit.POUNDS);
        Quantity<WeightUnit> expected= new Quantity<>(11.0231, WeightUnit.POUNDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Litre(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(2000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(3000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = quantity1.add(quantity2, VolumeUnit.LITRE);
        Quantity<VolumeUnit> expected= new Quantity<>(5.0, VolumeUnit.LITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Millilitre(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(2.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(3.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = quantity1.add(quantity2, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> expected= new Quantity<>(5000.0, VolumeUnit.MILLILITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_ExplicitTargetUnit_Gallon(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(5000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = quantity1.add(quantity2, VolumeUnit.GALLON);
        Quantity<VolumeUnit> expected= new Quantity<>(5.28344, VolumeUnit.GALLON);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_LengthQuantity_SameUnits(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(65.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(25.0, LengthUnit.FEET);

        Quantity<LengthUnit> sum = quantity1.subtract(quantity2);

        Quantity<LengthUnit> expected= new Quantity<>(40.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_LengthQuantity_CrossUnits(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(25.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(4.0, LengthUnit.YARDS);

        Quantity<LengthUnit> sum = quantity1.subtract(quantity2);

        Quantity<LengthUnit> expected= new Quantity<>(13.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_WeightQuantity_SameUnits(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(65.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(25.0, WeightUnit.KILOGRAMS);

        Quantity<WeightUnit> sum = quantity1.subtract(quantity2);

        Quantity<WeightUnit> expected= new Quantity<>(40.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_WeightQuantity_CrossUnits(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(15.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(2500.0, WeightUnit.GRAMS);

        Quantity<WeightUnit> sum = quantity1.subtract(quantity2);

        Quantity<WeightUnit> expected= new Quantity<>(12.5, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_VolumeQuantity_SameUnits(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(5.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> sum = quantity1.subtract(quantity2);

        Quantity<VolumeUnit> expected= new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_VolumeQuantity_CrossUnits(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(5000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> sum = quantity1.subtract(quantity2);

        Quantity<VolumeUnit> expected= new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Feet(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(30.0, LengthUnit.INCHES);
        Quantity<LengthUnit> quantity2 = new Quantity<>(18.0, LengthUnit.INCHES);

        Quantity<LengthUnit> sum = quantity1.subtract(quantity2, LengthUnit.FEET);

        Quantity<LengthUnit> expected= new Quantity<>(1.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Yards(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(9.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(36.0, LengthUnit.INCHES);

        Quantity<LengthUnit> sum = quantity1.subtract(quantity2, LengthUnit.YARDS);

        Quantity<LengthUnit> expected= new Quantity<>(2.0, LengthUnit.YARDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Inch(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(11.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(3.0, LengthUnit.YARDS);

        Quantity<LengthUnit> sum = quantity1.subtract(quantity2, LengthUnit.INCHES);

        Quantity<LengthUnit> expected= new Quantity<>(24.0, LengthUnit.INCHES);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Centimeter(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(13.0, LengthUnit.INCHES);
        Quantity<LengthUnit> quantity2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> sum = quantity1.subtract(quantity2, LengthUnit.CENTIMETERS);

        Quantity<LengthUnit> expected= new Quantity<>(17.78, LengthUnit.CENTIMETERS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Kilogram(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(8000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(3000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> sum = quantity1.subtract(quantity2, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> expected= new Quantity<>(5.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Gram(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(7.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(3.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> sum = quantity1.subtract(quantity2, WeightUnit.GRAMS);
        Quantity<WeightUnit> expected= new Quantity<>(4000.0, WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Pound(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(18.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(3000.0, WeightUnit.GRAMS);
        Quantity<WeightUnit> sum = quantity1.subtract(quantity2, WeightUnit.POUNDS);
        Quantity<WeightUnit> expected= new Quantity<>(33.0693, WeightUnit.POUNDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Litre(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(8000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(3000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> sum = quantity1.subtract(quantity2, VolumeUnit.LITRE);
        Quantity<VolumeUnit> expected= new Quantity<>(5.0, VolumeUnit.LITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Millilitre(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(8.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(3.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = quantity1.subtract(quantity2, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> expected= new Quantity<>(5000.0, VolumeUnit.MILLILITRE);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_ExplicitTargetUnit_Gallon(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(33000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(15.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> sum = quantity1.subtract(quantity2, VolumeUnit.GALLON);
        Quantity<VolumeUnit> expected= new Quantity<>(4.7551, VolumeUnit.GALLON);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Division_LengthQuantity_SameUnits(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(75.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(25.0, LengthUnit.FEET);

        double ratio = quantity1.divide(quantity2);

        double expected= 3.0;

        assertEquals(expected, ratio);
    }

    @Test
    public void testOperation_Division_LengthQuantity_CrossUnits(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(15.0, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(2.0, LengthUnit.YARDS);

        double ratio = quantity1.divide(quantity2);

        double expected= 2.5;

        assertEquals(expected, ratio);
    }

    @Test
    public void testOperation_Division_WeightQuantity_SameUnits(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(81.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(27.0, WeightUnit.KILOGRAMS);

        double ratio = quantity1.divide(quantity2);

        double expected= 3.0;

        assertEquals(expected, ratio);
    }

    @Test
    public void testOperation_Division_WeightQuantity_CrossUnits(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(15.0, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(2500.0, WeightUnit.GRAMS);

        double ratio = quantity1.divide(quantity2);

        double expected= 6.0;

        assertEquals(expected, ratio);
    }

    @Test
    public void testOperation_Division_VolumeQuantity_SameUnits(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(84.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(14.0, VolumeUnit.LITRE);

        double ratio = quantity1.divide(quantity2);

        double expected= 6.0;

        assertEquals(expected, ratio);
    }

    @Test
    public void testOperation_Division_VolumeQuantity_CrossUnits(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(4.5, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(2500.0, VolumeUnit.MILLILITRE);

        double ratio = quantity1.divide(quantity2);

        double expected= 1.8;

        assertEquals(expected, ratio);
    }

    @Test
    public void testOperation_Addition_LargeQuantities(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(3e6, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(2e6, LengthUnit.FEET);

        Quantity<LengthUnit> sum = quantity1.add(quantity2);

        Quantity<LengthUnit> expected= new Quantity<>(5e6, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Addition_SmallQuantities(){
        Quantity<LengthUnit> quantity1 = new Quantity<>(0.008, LengthUnit.FEET);
        Quantity<LengthUnit> quantity2 = new Quantity<>(0.0005, LengthUnit.FEET);

        Quantity<LengthUnit> sum = quantity1.add(quantity2);

        Quantity<LengthUnit> expected= new Quantity<>(0.0085, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_LargeQuantities(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(1e6, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(4e5, WeightUnit.KILOGRAMS);
        Quantity<WeightUnit> sum = quantity1.subtract(quantity2);
        Quantity<WeightUnit> expected= new Quantity<>(6e5, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Subtraction_SmallQuantities(){
        Quantity<WeightUnit> quantity1 = new Quantity<>(0.008, WeightUnit.GRAMS);
        Quantity<WeightUnit> quantity2 = new Quantity<>(0.005, WeightUnit.GRAMS);
        Quantity<WeightUnit> sum = quantity1.subtract(quantity2);
        Quantity<WeightUnit> expected= new Quantity<>(0.003, WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testOperation_Division_LargeQuantities(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(1e6, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(5e5, VolumeUnit.LITRE);

        double ratio = quantity1.divide(quantity2);

        double expected= 2.0;

        assertEquals(expected, ratio);
    }

    @Test
    public void testOperation_Division_SmallQuantities(){
        Quantity<VolumeUnit> quantity1 = new Quantity<>(0.0001, VolumeUnit.LITRE);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(0.0005, VolumeUnit.LITRE);

        double ratio = quantity1.divide(quantity2);

        double expected= 0.2;

        assertEquals(expected, ratio);
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