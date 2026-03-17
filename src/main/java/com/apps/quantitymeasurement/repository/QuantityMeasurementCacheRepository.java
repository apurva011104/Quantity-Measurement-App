package com.apps.quantitymeasurement.repository;

import java.util.ArrayList;
import java.util.List;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.units.IMeasurable;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository{

    private final List<Quantity<?>> measurements = new ArrayList<>();

    @Override
    public <U extends IMeasurable> void save(Quantity<U> quantity){
        measurements.add(quantity);
    }

    @Override
    public List<Quantity<?>> getAllMeasurements(){
        return measurements;
    }

    @Override
    public List<Quantity<?>> getMeasurementsByType(String measurementType){
        List<Quantity<?>> measurementsByType = new ArrayList<>();
        for(Quantity<?> quantity: measurements){
            if(quantity.getUnit().getClass().getSimpleName().equalsIgnoreCase("LengthUnit")){
                measurementsByType.add(quantity);
            }
        }
        return measurementsByType;
    }

    @Override
    public void deleteAll(){
        measurements.clear();
    }
}
