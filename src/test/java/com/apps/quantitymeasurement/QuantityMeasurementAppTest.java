package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class QuantityMeasurementAppTest {
    
    @Test
    public void testEquality_FeetToFeet_SameValue(){
        Length length1 = new Length(12.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.FEET);

        assertTrue(length1.equals(length2));

    }

    @Test
    public void testEquality_InchToInch_SameValue(){
        Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(length1.equals(length2));

    }

    @Test
    public void testEquality_NullComparison(){
        Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length2 = null;

        assertFalse(length1.equals(length2));

    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(length1.equals(length2));

    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue(){
        Length length1 = new Length(12.0, Length.LengthUnit.FEET);
        Length length2 = new Length(15.0, Length.LengthUnit.FEET);

        assertFalse(length1.equals(length2));

    }

    @Test
    public void testEquality_InchToInch_DifferentValue(){
        Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(15.0, Length.LengthUnit.INCHES);

        assertFalse(length1.equals(length2));

    }

    @Test
    public void testEquality_InvalidUnit(){
        assertThrows(Exception.class, ()->{
            new Length(12.0, null);
        });

    }

    @Test
    public void testEquality_NullUnit(){
        assertThrows(IllegalArgumentException.class, ()->{
            new Length(12.0, null);
        });

    }


    @Test
    public void testEquality_SameReference(){
        Length length = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(length.equals(length));

    }
}
