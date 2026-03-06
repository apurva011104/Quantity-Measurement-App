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

    protected enum ArithmeticOperation{
        ADD{
            @Override
            public double compute(double thisBaseValue, double thatBaseValue){
                return  thisBaseValue + thatBaseValue;
            }
        },
        SUBTRACT{
            @Override
            public double compute(double thisBaseValue, double thatBaseValue){
                return  thisBaseValue - thatBaseValue;
            }
        },
        DIVIDE{
            @Override
            public double compute(double thisBaseValue, double thatBaseValue){
                if(thatBaseValue == 0.0){
                    throw new ArithmeticException("Cannot be divided by 0");
                }
                return  thisBaseValue / thatBaseValue;
            }
        };

        public abstract double compute(double thisBaseValue, double thatBaseValue);
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit.toString().toLowerCase());
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
        validateArithmeticOperands(other);
        double sum = performArithmeticOperation(other, this.unit, ArithmeticOperation.ADD);

        return new Quantity<>(sum, this.unit);
    }
    
    public Quantity<U> add(Quantity<U> other, U targetUnit){
        validateArithmeticOperands(other, targetUnit);
        double sum = performArithmeticOperation(other, targetUnit, ArithmeticOperation.ADD);
        return new Quantity<>(sum, targetUnit);
    }

    public Quantity<U> subtract(Quantity<U> other){
        validateArithmeticOperands(other);
        double subtract = performArithmeticOperation(other, this.unit, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(subtract, this.unit);
    }
    
    public Quantity<U> subtract(Quantity<U> other, U targetUnit){
        validateArithmeticOperands(other, targetUnit);
        double subtract = performArithmeticOperation(other, targetUnit, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(subtract, targetUnit);
    }

    public double divide(Quantity<U> other){
        validateArithmeticOperands(other);
        double ratio = performArithmeticOperation(other,ArithmeticOperation.DIVIDE);
        return ratio;
    }

    private void validateArithmeticOperands(Quantity<U> other){
        if(other == null || other.unit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid operand");
        }
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit){
        validateArithmeticOperands(other);
        if(targetUnit == null || targetUnit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid target unit");
        }
    }

    private double performArithmeticOperation(Quantity<U> other, ArithmeticOperation operation){
        double thisBaseValue = this.unit.convertToBaseUnit(this.value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);
        double baseValue = operation.compute(thisBaseValue, otherBaseValue);
        return baseValue;
    }

    private double performArithmeticOperation(Quantity<U> other, U targetUnit, ArithmeticOperation operation){
        double baseValue = performArithmeticOperation(other, operation);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

}

