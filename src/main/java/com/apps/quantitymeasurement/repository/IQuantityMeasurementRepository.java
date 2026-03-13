package com.apps.quantitymeasurement.repository;

import java.util.Set;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.units.IMeasurable;

public interface IQuantityMeasurementRepository {

    <U extends IMeasurable> void save(Quantity<U> quantity);

    Set<Quantity<?>> getAllMeasurements();

}
