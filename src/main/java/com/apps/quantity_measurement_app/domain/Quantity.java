package com.apps.quantity_measurement_app.domain;

import com.apps.quantity_measurement_app.exception.UnsupportedOperationsException;
import com.apps.quantity_measurement_app.units.IMeasurable;

public class Quantity <U extends IMeasurable> {
    
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if(unit == null){
            throw new IllegalArgumentException("Invalid unit");
        }
        if(Double.isNaN(value) || Double.isInfinite(value)){
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
        return String.format("%.2f %s", value, unit.getUnitName());
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        double rounded = Math.round(baseValue * 100.0) / 100.0;
    
        return Double.hashCode(rounded) + unit.getClass().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(obj==null || obj.getClass()!=this.getClass()){
            return false;
        }
        Quantity<?> object = (Quantity<?>)obj;
        if(object.unit.getClass() != this.unit.getClass()){
            return false;
        }

        double baseValue1 = this.unit.convertToBaseUnit(this.value);
        double baseValue2 = object.unit.convertToBaseUnit(object.value);

        double rounded1 = Math.round(baseValue1 * 100.0) / 100.0;
        double rounded2 = Math.round(baseValue2 * 100.0) / 100.0;

        return rounded1 == rounded2;
    }

    
    public<V extends IMeasurable> Quantity<?> convertTo(V targetUnit){
        if(targetUnit == null || targetUnit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid target unit");
        }
        double newValue = targetUnit.convertFromBaseUnit(this.unit.convertToBaseUnit(this.value));
        return new Quantity<>(newValue, targetUnit);
    }

    public Quantity<? extends IMeasurable> add(Quantity<? extends IMeasurable> other) throws UnsupportedOperationsException{
        validateArithmeticOperands(other);
        this.unit.validateOperationSupport("Add");
        double sum = computeInBaseUnit(other, this.unit, ArithmeticOperation.ADD);
        return new Quantity<>(sum, this.unit);
    }
    
    public <V extends IMeasurable> Quantity<? extends IMeasurable> add(Quantity<? extends IMeasurable> other, V targetUnit) throws UnsupportedOperationsException{
        validateArithmeticOperands(other, targetUnit);
        this.unit.validateOperationSupport("Add");
        double sum = computeInBaseUnit(other, targetUnit, ArithmeticOperation.ADD);
        return new Quantity<>(sum, targetUnit);
    }

    public Quantity<? extends IMeasurable> subtract(Quantity<? extends IMeasurable> other)throws UnsupportedOperationsException{
        validateArithmeticOperands(other);
        this.unit.validateOperationSupport("Subtract");
        double subtract = computeInBaseUnit(other, this.unit, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(subtract, this.unit);
    }
    
    public <V extends IMeasurable> Quantity<? extends IMeasurable> subtract(Quantity<? extends IMeasurable> other, V targetUnit) throws UnsupportedOperationsException{
        validateArithmeticOperands(other, targetUnit);
        this.unit.validateOperationSupport("Subtract");
        double subtract = computeInBaseUnit(other, targetUnit, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(subtract, targetUnit);
    }

    public double divide(Quantity<? extends IMeasurable> other) throws UnsupportedOperationsException{
        validateArithmeticOperands(other);
        this.unit.validateOperationSupport("Divide");
        double ratio = computeInBaseUnit(other,ArithmeticOperation.DIVIDE);
        return ratio;
    }

    private void validateArithmeticOperands(Quantity<? extends IMeasurable> other){
        if(other == null || other.unit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid operand");
        }
    }

    private <V extends IMeasurable> void validateArithmeticOperands(Quantity<?> other, V targetUnit){  
        validateArithmeticOperands(other);
        if(targetUnit == null || targetUnit.getClass() != this.unit.getClass()){
            throw new IllegalArgumentException("Invalid target unit");
        }
    }

    private <V extends IMeasurable>  double computeInBaseUnit(Quantity<?> other, ArithmeticOperation operation){
        double thisBaseValue = this.unit.convertToBaseUnit(this.value);
        double otherBaseValue = other.unit.convertToBaseUnit(other.value);
        double baseValue = operation.compute(thisBaseValue, otherBaseValue);
        return baseValue;
    }

    private <V extends IMeasurable> double computeInBaseUnit(Quantity<?> other, V targetUnit, ArithmeticOperation operation){
        double baseValue = computeInBaseUnit(other, operation);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

}