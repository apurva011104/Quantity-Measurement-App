package com.apps.quantity_measurement_app.service;

import java.util.List;

import com.apps.quantity_measurement_app.domain.Quantity;
import com.apps.quantity_measurement_app.exception.UnsupportedOperationsException;
import com.apps.quantity_measurement_app.units.IMeasurable;

public interface QuantityService {
    
    boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException;

    <U extends IMeasurable> Quantity<?> convert(Quantity<?> quantity, U targetUnit);

    Quantity<?> add(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException;

    <U extends IMeasurable> Quantity<?> add(Quantity<?> quantity1, Quantity<?> quantity2, U targetUnit) throws UnsupportedOperationsException;

    Quantity<?> subtract(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException;

    <U extends IMeasurable> Quantity<?> subtract(Quantity<?> quantity1, Quantity<?> quantity2, U targetUnit) throws UnsupportedOperationsException;

    double divide(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException;

    List<Quantity<?>> getAllHistory();

    List<Quantity<?>> getByMeasurementType(String type);

    void deleteAll();
}
