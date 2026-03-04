package com.apps.quantitymeasurement;

public class Quantity <U extends IMeasurable> {
    
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if(unit == null){
            throw new IllegalArgumentException("Invalid unit");
        }
        if(Double.compare(value, Double.NaN) == 0 || Double.compare(value, Double.NEGATIVE_INFINITY)==0 || Double.compare(value, Double.POSITIVE_INFINITY)==0){
            throw new IllegalArgumentException("Invalid value");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null || obj.getClass()!=this.getClass()){
            return false;
        }
        Quantity object = (Quantity)obj;
        if(object.unit.getClass() != this.unit.getClass()){
            return false;
        }

        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = object.unit.convertToBaseUnit(object.value);

        double rounded1 = Math.round(baseValue1 * 100.0) / 100.0;
        double rounded2 = Math.round(baseValue2 * 100.0) / 100.0;

        return rounded1 == rounded2;
    }

    
    public Quantity<U> convertTo(U targetUnit){
        if(targetUnit == null || targetUnit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid target unit");
        }
        double newValue = targetUnit.convertFromBaseUnit(this.unit.convertToBaseUnit(this.value));
        return new Quantity<>(newValue, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other){
        if(other == null || other.unit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid addition quantity");
        }
        Quantity<U> quantity = other.convertTo(this.unit);
        double sum = value + quantity.value;

        return new Quantity<>(sum, this.unit);
    }
    
    public Quantity<U> add(Quantity<U> other, U targetUnit){
        if(other == null || other.unit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid addition quantity");
        }
        if(targetUnit == null || targetUnit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid target unit");
        }
        Quantity<U> otherTarget = other.convertTo(targetUnit);
        Quantity<U> thisTarget = this.convertTo(targetUnit);
        double sum = thisTarget.value + otherTarget.value;

        return new Quantity<>(sum, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other){
        if(other == null || other.unit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid subtraction quantity");
        }
        Quantity<U> quantity = other.convertTo(this.unit);
        double subtract = value - quantity.value;

        return new Quantity<>(subtract, this.unit);
    }
    
    public Quantity<U> subtract(Quantity<U> other, U targetUnit){
        if(other == null || other.unit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid subtraction quantity");
        }
        if(targetUnit == null || targetUnit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid target unit");
        }
        Quantity<U> otherTarget = other.convertTo(targetUnit);
        Quantity<U> thisTarget = this.convertTo(targetUnit);
        double subtract = thisTarget.value - otherTarget.value;

        return new Quantity<>(subtract, targetUnit);
    }

    public double divide(Quantity<U> other){
        if(other == null || other.unit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid addition quantity");
        }
        if(other.value==0.0){
            throw new ArithmeticException("Cannot be divided by zero");
        }
        double thisBaseValue = this.unit.convertToBaseUnit(this.value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);

        return thisBaseValue/otherBaseValue;
    }
    

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.toString().toLowerCase());
    }

}

