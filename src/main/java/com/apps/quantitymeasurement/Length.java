package com.apps.quantitymeasurement;

public class Length {
    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0);

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
        if(unit!=LengthUnit.INCHES){
            return value * LengthUnit.FEET.conversionFactor;
        }
        return value;
    }

    public boolean compare(Length thatLength){
        double thatLengthValue = thatLength.convertToBaseUnit();
        double thisLengthValue = this.convertToBaseUnit();

        return thisLengthValue == thatLengthValue;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || obj.getClass()!=this.getClass()){
            return false;
        }
        Length anotherObj = (Length)obj;

        return compare(anotherObj);
    }

    public static void main(String[] args) {
        Length length1 = new Length(12.0, LengthUnit.FEET);
        Length length2 = new Length(144.0, LengthUnit.INCHES);
        System.out.println("Are lengths equal? " + length1.equals(length2));
    }
}
