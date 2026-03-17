package com.apps.quantitymeasurement.controller;

import java.util.List;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.exception.UnsupportedOperationsException;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementService;
import com.apps.quantitymeasurement.units.IMeasurable;

public class QuantityMeasurementController {
    
    private final IQuantityMeasurementService quantityMeasurementService;

    public QuantityMeasurementController(){
        this.quantityMeasurementService = new QuantityMeasurementService();
    }

    public QuantityMeasurementController(IQuantityMeasurementService quantityMeasurementService) {
        if(quantityMeasurementService==null){
            throw new IllegalArgumentException("Invalid service.");
        }
        this.quantityMeasurementService = quantityMeasurementService;
    }

    public boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException, DatabaseException{
        return quantityMeasurementService.checkEquality(quantity1, quantity2);
    }

    public <U extends IMeasurable> Quantity<U> convert(Quantity<U> quantity, U targetUnit) throws DatabaseException{
        return quantityMeasurementService.convert(quantity, targetUnit);
    }

    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2) 
                    throws UnsupportedOperationsException, DatabaseException {
        return quantityMeasurementService.add(quantity1, quantity2);
    } 

    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit)
                    throws UnsupportedOperationsException, DatabaseException {
        return quantityMeasurementService.add(quantity1, quantity2, targetUnit);
    }
    
    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2)
                    throws UnsupportedOperationsException, DatabaseException {
        return quantityMeasurementService.subtract(quantity1, quantity2);
    } 

    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit)
                    throws UnsupportedOperationsException, DatabaseException {
        return quantityMeasurementService.subtract(quantity1, quantity2, targetUnit);
    }

    public <U extends IMeasurable> double divide(Quantity<U> quantity1, Quantity<U> quantity2)
                    throws UnsupportedOperationsException, DatabaseException {
        return quantityMeasurementService.divide(quantity1, quantity2);
    } 

    public List<Quantity<?>> getQuantityMeasurementHistory() throws DatabaseException{
        return quantityMeasurementService.getQuantityMeasurementHistory();
    }

    public List<Quantity<?>> getQuantityMeasurementHistoryByMeasurementType(String measurementType) throws DatabaseException{
        return quantityMeasurementService.getQuantityMeasurementHistoryByMeasurementType(measurementType);
    }

    public void deleteAll() throws DatabaseException{
        quantityMeasurementService.deleteAll();
    }
}
