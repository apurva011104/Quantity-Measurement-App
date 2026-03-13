package com.apps.quantitymeasurement.service;

import java.util.Set;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.UnsupportedOperationsException;
import com.apps.quantitymeasurement.units.IMeasurable;

public interface IQuantityMeasurementService {

    public boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException;

    public <U extends IMeasurable> Quantity<U> convert(Quantity<U> quantity, U targetUnit);
    
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2)
                            throws UnsupportedOperationsException;

    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit)
                            throws UnsupportedOperationsException;

    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2)
                            throws UnsupportedOperationsException;

    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit)
                            throws UnsupportedOperationsException;

    public <U extends IMeasurable> double divide(Quantity<U> quantity1, Quantity<U> quantity2)
                            throws UnsupportedOperationsException;

    public Set<Quantity<?>> getQuantityMeasurementHistory();

}

