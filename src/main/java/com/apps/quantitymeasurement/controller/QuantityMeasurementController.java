package com.apps.quantitymeasurement.controller;

import java.util.Set;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.UnsupportedOperationsException;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementService;
import com.apps.quantitymeasurement.units.IMeasurable;

public class QuantityMeasurementController {
    
    private final IQuantityMeasurementService quantityMeasurementService;

    public QuantityMeasurementController() {
        this.quantityMeasurementService = new QuantityMeasurementService();
    }

    public QuantityMeasurementController(IQuantityMeasurementService quantityMeasurementService) {
        this.quantityMeasurementService = quantityMeasurementService;
    }

    public boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException{
        return quantityMeasurementService.checkEquality(quantity1, quantity2);
    }

    public <U extends IMeasurable> Quantity<U> convert(Quantity<U> quantity, U targetUnit){
        return quantityMeasurementService.convert(quantity, targetUnit);
    }

    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2) 
                    throws UnsupportedOperationsException {
        return quantityMeasurementService.add(quantity1, quantity2);
    } 

    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit)
                    throws UnsupportedOperationsException {
        return quantityMeasurementService.add(quantity1, quantity2, targetUnit);
    }
    
    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2)
                    throws UnsupportedOperationsException {
        return quantityMeasurementService.subtract(quantity1, quantity2);
    } 

    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit)
                    throws UnsupportedOperationsException {
        return quantityMeasurementService.subtract(quantity1, quantity2, targetUnit);
    }

    public <U extends IMeasurable> double divide(Quantity<U> quantity1, Quantity<U> quantity2)
                    throws UnsupportedOperationsException {
        return quantityMeasurementService.divide(quantity1, quantity2);
    } 

    public Set<Quantity<?>> getQuantityMeasurementHistory(){
        return quantityMeasurementService.getQuantityMeasurementHistory();
    }

}
