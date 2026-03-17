package com.apps.quantitymeasurement.service;

import java.util.List;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.exception.UnsupportedOperationsException;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.units.IMeasurable;

public class QuantityMeasurementService implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementService() {
        repository = new QuantityMeasurementDatabaseRepository();
        
    }

    public QuantityMeasurementService(IQuantityMeasurementRepository repository) {
        if(repository==null){
            throw new IllegalArgumentException("Invalid repository");
        }
        this.repository = repository;
    }


    private void saveQuantity(Quantity<?>... quantities) throws DatabaseException{
        for(Quantity<?> quantity : quantities){
            if(quantity != null){
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
    public boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException, DatabaseException{
        validateEntities(quantity1, quantity2);
        if(quantity1.getUnit().getClass()!=quantity2.getUnit().getClass()){
            throw new UnsupportedOperationsException("Quantities must be of same measurement category");
        }
        saveQuantity(quantity1, quantity2);
        return quantity1.equals(quantity2);
    }

    @Override
    public <U extends IMeasurable> Quantity<U> convert(Quantity<U> quantity, U targetUnit) throws DatabaseException{
        validateEntities(quantity);
        saveQuantity(quantity);
        Quantity<U> converted = quantity.convertTo(targetUnit);
        saveQuantity(converted);
        return converted;
    }
    
    @Override
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2) throws UnsupportedOperationsException, DatabaseException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        Quantity<U> sum = quantity1.add(quantity2);
        saveQuantity(sum);
        return sum;
    }

    @Override
    public <U extends IMeasurable> Quantity<U> add(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) throws UnsupportedOperationsException, DatabaseException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        Quantity<U> sum = quantity1.add(quantity2, targetUnit);
        saveQuantity(sum);
        return sum;
    }

    @Override
    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2) throws UnsupportedOperationsException, DatabaseException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        Quantity<U> subtract = quantity1.add(quantity2);
        saveQuantity(subtract);
        return subtract;
    }

    @Override
    public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> quantity1, Quantity<U> quantity2, U targetUnit) throws UnsupportedOperationsException, DatabaseException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        Quantity<U> subtract = quantity1.add(quantity2, targetUnit);
        saveQuantity(subtract);
        return subtract;
    }

    @Override
    public <U extends IMeasurable> double divide(Quantity<U> quantity1, Quantity<U> quantity2) throws UnsupportedOperationsException, DatabaseException{
        validateEntities(quantity1, quantity2);
        saveQuantity(quantity1, quantity2);
        return quantity1.divide(quantity2);
    }

    @Override
    public List<Quantity<?>> getQuantityMeasurementHistory() throws  DatabaseException{
        return repository.getAllMeasurements();
    }

    @Override
    public List<Quantity<?>> getQuantityMeasurementHistoryByMeasurementType(String measurementType) throws DatabaseException{
        String[] measurementTypes = {"LengthUnit" , "WeightUnit", "VolumeUnit", "TemperatureUnit"};
        for(String type: measurementTypes){
            if(measurementType.equalsIgnoreCase(type)){
                return repository.getMeasurementsByType(type);
            }
        }
        throw new IllegalArgumentException("Illegal measurement type");
    }
    
    @Override
    public void deleteAll() throws DatabaseException{
        repository.deleteAll();
    }
}
