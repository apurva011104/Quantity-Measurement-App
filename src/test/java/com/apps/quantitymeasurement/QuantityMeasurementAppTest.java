package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.Length.LengthUnit;


public class QuantityMeasurementAppTest {

    @Test
    public void testAddition_ExplicitTargetUnit_Feet(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sum = length1.add(length2, LengthUnit.FEET);

        Length expected = new Length(2.0, LengthUnit.FEET);

        assertTrue(sum.equals(expected));
    }


    @Test
    public void testAddition_ExplicitTargetUnit_Inches(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sum = length1.add(length2, LengthUnit.INCHES);

        Length expected = new Length(24.0, LengthUnit.INCHES);

        assertTrue(sum.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sum = length1.add(length2, LengthUnit.YARDS);
        System.out.println(sum.getValue());

        Length expected = new Length(0.6667, LengthUnit.YARDS);

        assertTrue(sum.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sum = length1.add(length2);

        Length expected = new Length(60.96, LengthUnit.CENTIMETERS);

        assertTrue(sum.equals(expected));
    }


    @Test
    public void testAddition_ExplicitTargetUnit_SameAsFirstOperand(){
        Length length1 = new Length(36.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.YARDS);
        Length sum = length1.add(length2, LengthUnit.INCHES);

        Length expected = new Length(72.0, LengthUnit.INCHES);

        assertTrue(sum.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SameAsSecondOperand(){
        Length length1 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, LengthUnit.INCHES);
        Length sum = length1.add(length2, LengthUnit.INCHES);

        Length expected = new Length(2.0, LengthUnit.INCHES);

        assertTrue(sum.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sum12 = length1.add(length2, LengthUnit.CENTIMETERS);

        Length length3 = new Length(12.0, LengthUnit.INCHES);
        Length length4 = new Length(1.0, LengthUnit.FEET);
        Length sum34 = length3.add(length4, LengthUnit.CENTIMETERS);

        assertTrue(sum12.equals(sum34));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(0.0, LengthUnit.INCHES);
        Length sum = length1.add(length2, LengthUnit.YARDS);

        Length expected = new Length(1.6667, LengthUnit.YARDS);

        assertTrue(sum.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(-2.0, LengthUnit.FEET);
        Length sum = length1.add(length2, LengthUnit.INCHES);

        Length expected = new Length(36.0, LengthUnit.INCHES);

        assertTrue(sum.equals(expected));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NullTargetUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            Length length1 = new Length(5.0, LengthUnit.FEET);
            Length length2 = new Length(8.0, LengthUnit.FEET);
            length1.add(length2,null);
        });
    }

    @Test
    public void testAddition_LargeValues(){
        Length length1 = new Length(1000.0, LengthUnit.FEET);
        Length length2 = new Length(500.0, LengthUnit.FEET);
        Length sum = length1.add(length2, LengthUnit.INCHES);

        Length expected = new Length(18000.0, LengthUnit.INCHES);

        assertTrue(sum.equals(expected));
    }

    @Test
    public void testAddition_SmallValues(){
        Length length1 = new Length(12.0, LengthUnit.INCHES);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sum = length1.add(length2, LengthUnit.YARDS);

        Length expected = new Length(0.6667, LengthUnit.YARDS);

        assertTrue(sum.equals(expected));
    }
}
