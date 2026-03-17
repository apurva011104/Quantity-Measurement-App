package com.apps.quantitymeasurement.service;

import java.util.List;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.exception.UnsupportedOperationsException;
import com.apps.quantitymeasurement.units.IMeasurable;

public interface IQuantityMeasurementService {

    public boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException, DatabaseException;

    public <U extends IMeasurable> Quantity<U> convert(Quantity<U> quantity, U targetUnit) throws DatabaseException;
    
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2)
                            throws UnsupportedOperationsException, DatabaseException;

    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit)
                            throws UnsupportedOperationsException, DatabaseException;

    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2)
                            throws UnsupportedOperationsException, DatabaseException;

    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit)
                            throws UnsupportedOperationsException, DatabaseException;

    public <U extends IMeasurable> double divide(Quantity<U> quantity1, Quantity<U> quantity2)
                            throws UnsupportedOperationsException, DatabaseException;

    public List<Quantity<?>> getQuantityMeasurementHistory() throws DatabaseException;

    public List<Quantity<?>> getQuantityMeasurementHistoryByMeasurementType(String measurementType) throws DatabaseException;

    public void deleteAll() throws DatabaseException;

}

