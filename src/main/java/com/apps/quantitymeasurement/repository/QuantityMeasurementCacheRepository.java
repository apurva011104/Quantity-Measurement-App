package com.apps.quantitymeasurement.repository;

import java.util.HashSet;
import java.util.Set;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.units.IMeasurable;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository{

    private final Set<Quantity<?>> measurements = new HashSet<>();

    @Override
    public <U extends IMeasurable> void save(Quantity<U> quantity){
        measurements.add(quantity);
    }

    @Override
    public Set<Quantity<?>> getAllMeasurements(){
        return  measurements;
    }
}
