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
    public void testEquality_CentimeterToCentimeter_SameValue(){
        Length length1 = new Length(12.0, Length.LengthUnit.CENTIMETERS);
        Length length2 = new Length(12.0, Length.LengthUnit.CENTIMETERS);

        assertTrue(length1.equals(length2));
    }

    @Test
    public void testEquality_YardToYard_SameValue(){
        Length length1 = new Length(12.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(12.0, Length.LengthUnit.YARDS);

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
    public void testEquality_CentimeterToCentimeter_DifferentValue(){
        Length length1 = new Length(12.0, Length.LengthUnit.CENTIMETERS);
        Length length2 = new Length(15.0, Length.LengthUnit.CENTIMETERS);

        assertFalse(length1.equals(length2));

    }

    @Test
    public void testEquality_YardToYard_DifferentValue(){
        Length length1 = new Length(12.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(15.0, Length.LengthUnit.YARDS);

        assertFalse(length1.equals(length2));

    }

    @Test
    public void testEquality_InchToCentimeter_EquivalentValue(){
        Length length1 = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length length2 = new Length(1.0, Length.LengthUnit.INCHES);

        assertTrue(length1.equals(length2));

    }

    @Test
    public void testEquality_InchToYard_EquivalentValue(){
        Length length1 = new Length(36.0, Length.LengthUnit.INCHES);
        Length length2 = new Length(1.0, Length.LengthUnit.YARDS);

        assertTrue(length1.equals(length2));

    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(12.0, Length.LengthUnit.INCHES);

        assertTrue(length1.equals(length2));

    }

    @Test
    public void testEquality_CentimeterToFeet_EquivalentValue(){
        Length length1 = new Length(1.0, Length.LengthUnit.FEET);
        Length length2 = new Length(30.48, Length.LengthUnit.CENTIMETERS);

        assertTrue(length1.equals(length2));

    }

    @Test
    public void testEquality_CentimeterToYard_EquivalentValue(){
        Length length1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length length2 = new Length(91.44, Length.LengthUnit.CENTIMETERS);

        assertTrue(length1.equals(length2));

    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue(){
        Length length1 = new Length(3.0, Length.LengthUnit.FEET);
        Length length2 = new Length(1.0, Length.LengthUnit.YARDS);

        assertTrue(length1.equals(length2));

    }



    @Test
    public void testEquality_NullComparison(){
        Length length1 = new Length(12.0, Length.LengthUnit.INCHES);
        Length length2 = null;

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
