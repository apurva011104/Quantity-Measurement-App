package com.apps.quantitymeasurement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_KilogramToKilogram_SameValue(){
        Weight weight1 = new Weight(5.0 , WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(5.0 , WeightUnit.KILOGRAMS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_KilogramToKilogram_DifferentValue(){
        Weight weight1 = new Weight(5.0 , WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(15.0 , WeightUnit.KILOGRAMS);

        assertFalse(weight1.equals(weight2));
    }

    @Test
    public void testEquality_GramToGram_SameValue(){
        Weight weight1 = new Weight(135.0 , WeightUnit.GRAMS);
        Weight weight2 = new Weight(135.0 , WeightUnit.GRAMS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_GramToGram_DifferentValue(){
        Weight weight1 = new Weight(135.0 , WeightUnit.GRAMS);
        Weight weight2 = new Weight(525.0 , WeightUnit.GRAMS);

        assertFalse(weight1.equals(weight2));
    }

    @Test
    public void testEquality_PoundToPound_SameValue(){
        Weight weight1 = new Weight(13.0 , WeightUnit.POUNDS);
        Weight weight2 = new Weight(13.0 , WeightUnit.POUNDS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_PoundToPound_DifferentValue(){
        Weight weight1 = new Weight(45.0 , WeightUnit.POUNDS);
        Weight weight2 = new Weight(95.0 , WeightUnit.POUNDS);

        assertFalse(weight1.equals(weight2));
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue(){
        Weight weight1 = new Weight(5.0 , WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(5000.0 , WeightUnit.GRAMS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue(){
        Weight weight1 = new Weight(5.0 , WeightUnit.GRAMS);
        Weight weight2 = new Weight(0.005 , WeightUnit.KILOGRAMS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_PoundToGram_EquivalentValue(){
        Weight weight1 = new Weight(1.0 , WeightUnit.POUNDS);
        Weight weight2 = new Weight(453.592 , WeightUnit.GRAMS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_GramToPound_EquivalentValue(){
        Weight weight1 = new Weight(2267.96, WeightUnit.GRAMS);
        Weight weight2 = new Weight(5.0 , WeightUnit.POUNDS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_PoundToKilogram_EquivalentValue(){
        Weight weight1 = new Weight(1.0 , WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(2.20462 , WeightUnit.POUNDS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_KilogramToPound_EquivalentValue(){
        Weight weight1 = new Weight(6.0 , WeightUnit.POUNDS);
        Weight weight2 = new Weight(2.72155 , WeightUnit.KILOGRAMS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_WeightVsLength_Incompatible(){
        Weight weight = new Weight(5.0 , WeightUnit.GRAMS);
        Length length = new Length(5.0, LengthUnit.FEET);

        assertFalse(weight.equals(length));
    }

    @Test
    public void testEquality_NullComparison(){
        Weight weight = new Weight(5.0 , WeightUnit.GRAMS);

        assertFalse(weight.equals(null));
    }

    @Test
    public void testEquality_SameReference(){
        Weight weight = new Weight(5.0 , WeightUnit.GRAMS);

        assertTrue(weight.equals(weight));
    }

    @Test
    public void testEquality_NullUnit(){
        assertThrows( IllegalArgumentException.class , () -> {
            new Weight(55.0, null);
        });
    }

    @Test
    public void testEquality_TransitiveProperty(){
        Weight weight1 = new Weight(5.0 , WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(5000.0 , WeightUnit.GRAMS);
        Weight weight3 = new Weight(11.0231, WeightUnit.POUNDS);

        assertTrue(weight1.equals(weight2) && weight2.equals(weight3) && weight1.equals(weight3));
    }

    @Test
    public void testEquality_ZeroValue() {
        Weight weight1 = new Weight(0.0 , WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(0.0 , WeightUnit.GRAMS);

        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testEquality_NegativeWeight(){
        Weight weight1 = new Weight(-5.0 , WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(-5000.0 , WeightUnit.GRAMS);
    
        assertTrue(weight1.equals(weight2));
    }

    @Test
    public void testConversion_RoundTrip(){
        Weight weightInKgs = new Weight(5.0 , WeightUnit.KILOGRAMS);
        Weight convertedToGrams = weightInKgs.convertTo(WeightUnit.GRAMS);
        Weight convertedToKgs = convertedToGrams.convertTo(WeightUnit.KILOGRAMS);
    
        assertTrue(weightInKgs.equals(convertedToKgs));
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram(){
        Weight weight1 = new Weight(2.0, WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(5.0, WeightUnit.KILOGRAMS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(7.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_SameUnit_GramPlusGram(){
        Weight weight1 = new Weight(250.0, WeightUnit.GRAMS);
        Weight weight2 = new Weight(125.0, WeightUnit.GRAMS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(375.0, WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_SameUnit_PoundPlusPound(){
        Weight weight1 = new Weight(25.0, WeightUnit.POUNDS);
        Weight weight2 = new Weight(52.0, WeightUnit.POUNDS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(77.0, WeightUnit.POUNDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram(){
        Weight weight1 = new Weight(2.0, WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(5000.0, WeightUnit.GRAMS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(7.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_GramPlusKilogram(){
        Weight weight1 = new Weight(2000.0, WeightUnit.GRAMS);
        Weight weight2 = new Weight(5.0, WeightUnit.KILOGRAMS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(7000.0, WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusPound(){
        Weight weight1 = new Weight(11.0, WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(121.254, WeightUnit.POUNDS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(66.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram(){
        Weight weight1 = new Weight(25.0, WeightUnit.POUNDS);
        Weight weight2 = new Weight(48.0, WeightUnit.KILOGRAMS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(130.822, WeightUnit.POUNDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusGram(){
        Weight weight1 = new Weight(26.0, WeightUnit.POUNDS);
        Weight weight2 = new Weight(2000.0, WeightUnit.GRAMS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(30.409245, WeightUnit.POUNDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_CrossUnit_GramPlusPound(){
        Weight weight1 = new Weight(2000.0, WeightUnit.GRAMS);
        Weight weight2 = new Weight(5.0, WeightUnit.POUNDS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(4267.96, WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram(){
        Weight weight1 = new Weight(2000.0, WeightUnit.GRAMS);
        Weight weight2 = new Weight(11.0231, WeightUnit.POUNDS);
        Weight sum = weight1.add(weight2, WeightUnit.KILOGRAMS);
        Weight expected = new Weight(7.0, WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Gram(){
        Weight weight1 = new Weight(2.0, WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(11.0231, WeightUnit.POUNDS);
        Weight sum = weight1.add(weight2, WeightUnit.GRAMS);
        Weight expected = new Weight(7000.0, WeightUnit.GRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Pound(){
        Weight weight1 = new Weight(2000.0, WeightUnit.GRAMS);
        Weight weight2 = new Weight(3.0, WeightUnit.KILOGRAMS);
        Weight sum = weight1.add(weight2, WeightUnit.POUNDS);
        Weight expected = new Weight(11.0231, WeightUnit.POUNDS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_Commutativity(){
        Weight weight1 = new Weight(2000.0, WeightUnit.GRAMS);
        Weight weight2 = new Weight(3.0, WeightUnit.KILOGRAMS);

        Weight sum1 = weight1.add(weight2);
        Weight sum2 = weight2.add(weight1);

        assertTrue(sum1.equals(sum2));
    }

    @Test
    public void testAddition_WithZero(){
        Weight weight1 = new Weight(2000.0, WeightUnit.GRAMS);
        Weight weight2 = new Weight(0.0, WeightUnit.KILOGRAMS);
        Weight sum = weight1.add(weight2, WeightUnit.KILOGRAMS);
        Weight expected = new Weight(2.0,  WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_NegativeValues(){
        Weight weight1 = new Weight(5000.0, WeightUnit.GRAMS);
        Weight weight2 = new Weight(-3.0, WeightUnit.KILOGRAMS);
        Weight sum = weight1.add(weight2, WeightUnit.KILOGRAMS);
        Weight expected = new Weight(2.0,  WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

    @Test
    public void testAddition_LargeValues(){
        Weight weight1 = new Weight(1e6, WeightUnit.KILOGRAMS);
        Weight weight2 = new Weight(2e6, WeightUnit.KILOGRAMS);
        Weight sum = weight1.add(weight2);
        Weight expected = new Weight(3e6,  WeightUnit.KILOGRAMS);

        assertEquals(expected, sum);
    }

}