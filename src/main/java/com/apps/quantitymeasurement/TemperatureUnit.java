package com.apps.quantitymeasurement;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable{
    
    CELSIUS(true,false,false),
    FAHRENHEIT(false,false,true),
    KELVIN(false,true,false);

    private final Function<Double, Double> FAHRENHEIT_TO_CELSIUS = (fahrenheit) -> ((fahrenheit-32)*5)/9;
    private final Function<Double, Double> KELVIN_TO_CELSIUS = (kelvin) -> kelvin-273.15;
    private final Function<Double, Double> CELSIUS_TO_CELSIUS = (celsius) -> celsius;
    private final Function<Double, Double> CELSIUS_TO_KELVIN = (celsius) -> celsius+273.15;
    private final Function<Double, Double> CELSIUS_TO_FAHRENHEIT = (celsius) -> ((celsius*9)/5)+32;
    
    private final boolean isCelsius;
    private final boolean isKelvin;
    private final boolean isFahrenheit;

    private TemperatureUnit(boolean isCelsius, boolean isKelvin, boolean isFahrenheit) {
        this.isCelsius = isCelsius;
        this.isKelvin = isKelvin;
        this.isFahrenheit = isFahrenheit;
    }
   

    SupportsArithmetic supportsArithmetic = () -> false;

    @Override
    public String getUnitName(){
        return this.toString().toLowerCase();
    }

    @Override
    public double getConversionFactor(){
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value){
        if(isCelsius){
            return CELSIUS_TO_CELSIUS.apply(value);
        }
        if(isKelvin){
            return KELVIN_TO_CELSIUS.apply(value);
        }
        return FAHRENHEIT_TO_CELSIUS.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue){
        if(isCelsius){
            return CELSIUS_TO_CELSIUS.apply(baseValue);
        }
        if(isKelvin){
            return CELSIUS_TO_KELVIN.apply(baseValue);
        }
        return CELSIUS_TO_FAHRENHEIT.apply(baseValue);
    }

    @Override
    public boolean supportsArithmetic(){
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) throws UnsupportedOperationsException{
        if(!supportsArithmetic()){
            throw new UnsupportedOperationsException("Temperature does not supports "+operation+" operations.");
        }
    }

}
