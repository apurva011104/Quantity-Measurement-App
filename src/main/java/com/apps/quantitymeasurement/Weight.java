package com.apps.quantitymeasurement;

public class Weight {
    
    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
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

    public WeightUnit getUnit() {
        return unit;
    }

    private double convertToBaseUnit(){
        return unit.convertToBaseUnit(value);
    }

    private double convertFromBaseToTargetUnit(double weightInKgs, WeightUnit targetUnit){
        return targetUnit.convertFromBaseUnit(weightInKgs);
    }

    private Weight addAndConvert(Weight thatWeight, WeightUnit targetUnit){
        double sumInBaseUnit = add(thatWeight).convertToBaseUnit();
        double sumInTargetUnit = convertFromBaseToTargetUnit(sumInBaseUnit, targetUnit);

        return new Weight(sumInTargetUnit, targetUnit);
    }

    public boolean compare(Weight thatWeight){
        double thatWeightValue = Math.round(thatWeight.convertToBaseUnit() * 1000.0) /1000.0;
        double thisWeightValue =  Math.round(this.convertToBaseUnit() * 1000.0) /1000.0;

        return Double.compare(thisWeightValue, thatWeightValue)==0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || obj.getClass()!=this.getClass()){
            return false;
        }
        Weight anotherObj = (Weight) obj;
        return compare(anotherObj);
    }

    public Weight convertTo(WeightUnit targetUnit){
        if(targetUnit == null){
            throw new IllegalArgumentException("Target unit cannot be null.");
        }
        double valueInBaseUnit = convertToBaseUnit();
        double valueInTargetUnit = convertFromBaseToTargetUnit(valueInBaseUnit, targetUnit);
        return new Weight(valueInTargetUnit, targetUnit);
    }

    public Weight add(Weight thatWeight){
        if(thatWeight==null){
            throw new IllegalArgumentException("Cannot be added to null");
        }
        double thisWeightInKgs = convertToBaseUnit();
        double thatWeightInKgs = thatWeight.convertToBaseUnit();

        double sum = thisWeightInKgs + thatWeightInKgs;
        double weightInThisUnit = convertFromBaseToTargetUnit(sum, unit);

        return new Weight(weightInThisUnit, unit);
    }

    public Weight add(Weight thatWeight, WeightUnit targetUnit){
        if(thatWeight == null){
            throw new IllegalArgumentException("Cannot be added to null");
        }
        if(targetUnit==null){
            throw new IllegalArgumentException("Invalid target unit");
        }
        return addAndConvert(thatWeight, targetUnit);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.toString().toLowerCase());
    }

}
