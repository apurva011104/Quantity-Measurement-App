package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Inches;

public class QuantityMeasurementAppTest {
    
    @Test
    public void testFeetEquality_SameValue(){
        Feet feet1 = new Feet(12.0);
        Feet feet2 = new Feet(12.0);

        assertTrue(feet1.equals(feet2));

    }

    @Test
    public void testFeetEquality_DifferentValue(){
        Feet feet1 = new Feet(12.0);
        Feet feet2 = new Feet(13.0);

        assertFalse(feet1.equals(feet2));

    }

    @Test
    public void testFeetEquality_NullComparison(){
        Feet feet1 = new Feet(12.0);
        Feet feet2 = null;

        assertFalse(feet1.equals(feet2));

    }

    @Test
    public void testFeetEquality_DifferentClass(){
        Feet feet1 = new Feet(12.0);
        Double feet2 = 12.0;

        assertFalse(feet1.equals(feet2));

    }

    @Test
    public void testFeetEquality_SameReference(){
        Feet feet1 = new Feet(12.0);

        assertTrue(feet1.equals(feet1));

    }

    @Test
    public void testInchesEquality_DifferentValue(){
        Inches inches1 = new Inches(12.0);
        Inches inches2 = new Inches(13.0);

        assertFalse(inches1.equals(inches2));

    }

    @Test
    public void testInchesEquality_NullComparison(){
        Inches inches1 = new Inches(12.0);
        Inches inches2 = null;

        assertFalse(inches1.equals(inches2));

    }

    @Test
    public void testInchesEquality_DifferentClass(){
        Inches inches = new Inches(12.0);
        Feet feet = new Feet(12.0);

        assertFalse(inches.equals(feet));

    }

    @Test
    public void testInchesEquality_SameReference(){
        Inches inches = new Inches(12.0);
        
        assertTrue(inches.equals(inches));

    }
}
