package com.apps.quantitymeasurement;

public interface IMeasurable {

    SupportsArithmetic supportsArithmetic = () -> true;

    public String getUnitName();

    public double getConversionFactor();

    public double convertToBaseUnit(double value);

    public double convertFromBaseUnit(double baseValue);

    default boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }

    default void validateOperationSupport(String operation) throws  UnsupportedOperationsException{
    }
}
