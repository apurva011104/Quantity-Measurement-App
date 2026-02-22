package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.Length.LengthUnit;


public class QuantityMeasurementAppTest {

    @Test
    public void testAddition_SameUnit_FeetPlusFeet(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(2.0, LengthUnit.FEET);
        Length sum = length1.add(length2);

        Length expected = new Length(3.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch(){
        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(2.0, LengthUnit.INCHES);
        Length sum = length1.add(length2);

        Length expected = new Length(3.0, LengthUnit.INCHES);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_SameUnit_YardPlusYard(){
        Length length1 = new Length(1.0, LengthUnit.YARDS);
        Length length2 = new Length(2.0, LengthUnit.YARDS);
        Length sum = length1.add(length2);

        Length expected = new Length(3.0, LengthUnit.YARDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_SameUnit_CentimeterPlusCentimeter(){
        Length length1 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length length2 = new Length(2.0, LengthUnit.CENTIMETERS);
        Length sum = length1.add(length2);

        Length expected = new Length(3.0, LengthUnit.CENTIMETERS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInch(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sum = length1.add(length2);

        Length expected = new Length(2.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusFeet(){
        Length length1 = new Length(12.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.FEET);
        Length sum = length1.add(length2);

        Length expected = new Length(24.0, LengthUnit.INCHES);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusYard(){
        Length length1 = new Length(3.0, LengthUnit.FEET);
        Length length2 = new Length(1.0, LengthUnit.YARDS);
        Length sum = length1.add(length2);

        Length expected = new Length(6.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_YardPlusFeet(){
        Length length1 = new Length(1.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        Length sum = length1.add(length2);

        Length expected = new Length(2.0, LengthUnit.YARDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusCentimeter(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(30.48, LengthUnit.CENTIMETERS);
        Length sum = length1.add(length2);

        Length expected = new Length(2.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusFeet(){
        Length length1 = new Length(30.48, LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, LengthUnit.FEET);
        Length sum = length1.add(length2);

        Length expected = new Length(60.96, LengthUnit.CENTIMETERS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusYard(){
        Length length1 = new Length(36.0, LengthUnit.INCHES);
        Length length2 = new Length(1.0, LengthUnit.YARDS);
        Length sum = length1.add(length2);

        Length expected = new Length(72.0, LengthUnit.INCHES);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_YardPlusInch(){
        Length length1 = new Length(1.0, LengthUnit.YARDS);
        Length length2 = new Length(36.0, LengthUnit.INCHES);
        Length sum = length1.add(length2);

        Length expected = new Length(2.0, LengthUnit.YARDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_InchPlusCentimeter(){
        Length length1 = new Length(1.0, LengthUnit.INCHES);
        Length length2 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length sum = length1.add(length2);

        Length expected = new Length(2.0, LengthUnit.INCHES);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch(){
        Length length1 = new Length(2.54, LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, LengthUnit.INCHES);
        Length sum = length1.add(length2);

        Length expected = new Length(5.08, LengthUnit.CENTIMETERS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_YardPlusCentimeter(){
        Length length1 = new Length(1.0, LengthUnit.YARDS);
        Length length2 = new Length(91.44, LengthUnit.CENTIMETERS);
        Length sum = length1.add(length2);

        Length expected = new Length(2.0, LengthUnit.YARDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusYard(){
        Length length1 = new Length(91.44, LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, LengthUnit.YARDS);
        Length sum = length1.add(length2);

        Length expected = new Length(182.88, LengthUnit.CENTIMETERS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_Commutativity(){
        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);
        Length sum12 = length1.add(length2);

        Length length3 = new Length(12.0, LengthUnit.INCHES);
        Length length4 = new Length(1.0, LengthUnit.FEET);
        Length sum34 = length3.add(length4);

        assertTrue(sum12.equals(sum34));
    }

    @Test
    public void testAddition_WithZero(){
        Length length1 = new Length(5.0, LengthUnit.FEET);
        Length length2 = new Length(0.0, LengthUnit.INCHES);
        Length sum = length1.add(length2);

        Length expected = new Length(5.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_NegativeValues(){
        Length length1 = new Length(3.0, LengthUnit.FEET);
        Length length2 = new Length(-12.0, LengthUnit.INCHES);
        Length sum = length1.add(length2);

        Length expected = new Length(2.0, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_NullSecondOperand(){
        assertThrows(IllegalArgumentException.class, ()->{
            Length length = new Length(5.0, LengthUnit.FEET);
            length.add(null);
        });
    }

    @Test
    public void testAddition_LargeValues(){
        Length length1 = new Length(1e6, LengthUnit.FEET);
        Length length2 = new Length(2e6, LengthUnit.FEET);
        Length sum = length1.add(length2);

        Length expected = new Length(3e6, LengthUnit.FEET);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_SmallValues(){
        Length length1 = new Length(0.01, LengthUnit.FEET);
        Length length2 = new Length(0.02, LengthUnit.FEET);
        Length sum = length1.add(length2);

        Length expected = new Length(0.03, LengthUnit.FEET);

        assertEquals(expected, sum);
    }
}
