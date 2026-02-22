package com.apps.quantitymeasurement;

public class Length {

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        private LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }

    }

    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if(unit==null){
            throw new IllegalArgumentException("Length Unit cannot be null");
        }
        if(Double.compare(value, Double.NaN)==0 || value==Double.POSITIVE_INFINITY || value==Double.NEGATIVE_INFINITY){
            throw new IllegalArgumentException("Invalid length value");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit(){
        double conversionFactor;
        if(unit==LengthUnit.FEET){
            conversionFactor =  LengthUnit.FEET.conversionFactor;
        }
        else if(unit==LengthUnit.YARDS){
            conversionFactor =  LengthUnit.YARDS.conversionFactor;
        }
        else if(unit==LengthUnit.CENTIMETERS){
            conversionFactor =  LengthUnit.CENTIMETERS.conversionFactor;
        }
        else{
            conversionFactor =  LengthUnit.INCHES.conversionFactor;
        }
    
        return Math.round(value * conversionFactor * 100.0) / 100.0;
    }

    public boolean compare(Length thatLength){
        double thatLengthValue = thatLength.convertToBaseUnit();
        double thisLengthValue = this.convertToBaseUnit();

        return Double.compare(thisLengthValue, thatLengthValue)==0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || obj.getClass()!=this.getClass()){
            return false;
        }
        Length anotherObj = (Length)obj;
        return compare(anotherObj);
    }

    public Length convertTo(LengthUnit targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        double valueInBaseUnit = convertToBaseUnit();
        double targetUnitConversionFactor = targetUnit.conversionFactor;
        double valueInTargetUnit = valueInBaseUnit / targetUnitConversionFactor;
        valueInTargetUnit = Math.round(valueInTargetUnit * 100.0) / 100.0;
        return new Length(valueInTargetUnit, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.toString().toLowerCase());
    }

}
