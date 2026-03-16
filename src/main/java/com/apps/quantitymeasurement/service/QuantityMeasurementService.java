package com.apps.quantitymeasurement.service;

import java.util.Set;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.UnsupportedOperationsException;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.units.IMeasurable;

public class QuantityMeasurementService implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementService() {
        repository = new QuantityMeasurementCacheRepository();
    }

    public QuantityMeasurementService(IQuantityMeasurementRepository repository) {
        if(repository==null){
            throw new IllegalArgumentException("Invalid repository");
        }
        this.repository = repository;
    }


    private void saveQuantity(Quantity<?>... quantities){
        for(Quantity<?> quantity : quantities){
            if(quantity == null){
                repository.save(quantity);
            }
        }
        
    }

    private void validateEntities(Quantity<?>... quantities){
        for(Quantity<?> quantity : quantities){
            if(quantity == null){
                throw new IllegalArgumentException("Invalid quantity entity");
            }
        }
    }

    @Override
    public boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException{
        validateEntities(quantity1, quantity2);
        if(quantity1.getUnit().getClass()!=quantity2.getUnit().getClass()){
            throw new UnsupportedOperationsException("Quantities must be of same measurement category");
        }
        saveQuantity(quantity1, quantity2);
        return quantity1.equals(quantity2);
    }

    @Override
    public <U extends IMeasurable> Quantity<U> convert(Quantity<U> quantity, U targetUnit){
        validateEntities(quantity);
        saveQuantity(quantity);
        return quantity.convertTo(targetUnit);
    }
    
    @Override
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2) throws UnsupportedOperationsException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        return quantity1.add(quantity2);
    }

    @Override
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) throws UnsupportedOperationsException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        return quantity1.add(quantity2, targetUnit);
    }

    @Override
    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2) throws UnsupportedOperationsException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        return quantity1.subtract(quantity2);
    }

    @Override
    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) throws UnsupportedOperationsException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        return quantity1.subtract(quantity2, targetUnit);
    }

    @Override
    public <U extends IMeasurable> double divide(Quantity<U> quantity1, Quantity<U> quantity2) throws UnsupportedOperationsException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        return quantity1.divide(quantity2);
    }

    @Override
    public Set<Quantity<?>> getQuantityMeasurementHistory(){
        return repository.getAllMeasurements();
    }
    
}
