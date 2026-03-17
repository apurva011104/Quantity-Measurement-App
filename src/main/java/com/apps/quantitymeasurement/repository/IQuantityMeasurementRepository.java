package com.apps.quantitymeasurement.repository;

import java.util.List;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.units.IMeasurable;

public interface IQuantityMeasurementRepository {

    <U extends IMeasurable> void save(Quantity<U> quantity) throws DatabaseException;

    List<Quantity<?>> getAllMeasurements() throws DatabaseException;

    List<Quantity<?>> getMeasurementsByType(String measurementType) throws DatabaseException;

    void deleteAll() throws DatabaseException;
}
