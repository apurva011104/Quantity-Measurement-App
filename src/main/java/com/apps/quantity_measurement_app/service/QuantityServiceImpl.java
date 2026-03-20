package com.apps.quantity_measurement_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.apps.quantity_measurement_app.domain.OperationHistory;
import com.apps.quantity_measurement_app.domain.Quantity;
import com.apps.quantity_measurement_app.exception.UnsupportedOperationsException;
import com.apps.quantity_measurement_app.mapper.OperationHistoryMapper;
import com.apps.quantity_measurement_app.mapper.QuantityMapper;
import com.apps.quantity_measurement_app.repository.OperationHistoryRepository;
import com.apps.quantity_measurement_app.repository.QuantityRepository;
import com.apps.quantity_measurement_app.units.IMeasurable;
import com.apps.quantity_measurement_app.util.OperationType;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class QuantityServiceImpl implements QuantityService {

    private final QuantityRepository quantityRepository;
    private final OperationHistoryRepository operationHistoryRepository;
    
    public QuantityServiceImpl(QuantityRepository quantityRepository , OperationHistoryRepository operationHistoryRepository) {
        this.quantityRepository = quantityRepository;
        this.operationHistoryRepository = operationHistoryRepository;
    }

    private void saveQuantities(Quantity<?>... quantities) {
        for (Quantity<?> q : quantities) {
            if (q == null) {
                throw new IllegalArgumentException("Quantity cannot be null");
            }
            try {
                quantityRepository.save(QuantityMapper.toEntity(q));
                System.out.println("Saving: " + q);
            } 
            catch (DataIntegrityViolationException e) {
                System.out.println("Duplicate data entry");
            }
                
        }
    }

    private void saveOperationHistory(OperationHistory history){
        if(history==null){
            throw new IllegalArgumentException("Operation history cannot be null");
        }
        operationHistoryRepository.save(OperationHistoryMapper.toEntity(history));
        System.out.println("Operation History saved successfully");
    }

    private void validateQuantities(Quantity<?>... quantities) {
        for (Quantity<?> q : quantities) {
            if (q == null) {
                throw new IllegalArgumentException("Invalid quantity");
            }
        }
    }

    private void validateTargetUnit(IMeasurable targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Invalid target unit");
        }
    }

    @Override
    public boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException {
        validateQuantities(quantity1, quantity2);
        if (!quantity1.getUnit().getClass().equals(quantity2.getUnit().getClass())) {
            throw new UnsupportedOperationsException("Different measurement types");
        }
        boolean result = quantity1.equals(quantity2);
        saveQuantities(quantity1, quantity2);
        OperationHistory history = new OperationHistory(
                                        OperationType.COMPARE,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        Boolean.toString(result));
        saveOperationHistory(history);
        return result;
    }

    @Override
    public <U extends IMeasurable> Quantity<?> convert(Quantity<?> quantity, U targetUnit) {
        validateQuantities(quantity);
        validateTargetUnit(targetUnit);
        Quantity<?> result = quantity.convertTo(targetUnit);
        saveQuantities(quantity);
        OperationHistory history = new OperationHistory(
                                        OperationType.CONVERT,
                                        quantity.toString(),
                                        null,
                                        result.toString());
        saveOperationHistory(history);
        return result;
    }

    @Override
    public Quantity<?> add(Quantity<?> quantity1, Quantity<?> quantity2)
            throws UnsupportedOperationsException {
        validateQuantities(quantity1, quantity2);
        Quantity<?> result = quantity1.add(quantity2);
        saveQuantities(quantity1, quantity2);
        OperationHistory history = new OperationHistory(
                                        OperationType.ADD,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        result.toString());
        saveOperationHistory(history);
        return result;
    }

    @Override
    public <U extends IMeasurable> Quantity<?> add(Quantity<?> quantity1, Quantity<?> quantity2, U targetUnit) throws UnsupportedOperationsException{
        validateQuantities(quantity1, quantity2);
        validateTargetUnit(targetUnit);
        Quantity<?> result = quantity1.add(quantity2, targetUnit);
        saveQuantities(quantity1, quantity2);
        OperationHistory history = new OperationHistory(
                                        OperationType.ADD,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        result.toString());
        saveOperationHistory(history);
        return result;
    }

    @Override
    public Quantity<?> subtract(Quantity<?> quantity1, Quantity<?> quantity2)
            throws UnsupportedOperationsException {
        validateQuantities(quantity1, quantity2);
        Quantity<?> result = quantity1.subtract(quantity2);
        saveQuantities(quantity1, quantity2);
        OperationHistory history = new OperationHistory(
                                        OperationType.SUBTRACT,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        result.toString());
        saveOperationHistory(history);
        return result;
    }

    @Override
    public <U extends IMeasurable> Quantity<?> subtract(Quantity<?> quantity1, Quantity<?> quantity2, U targetUnit) throws UnsupportedOperationsException{
        validateQuantities(quantity1, quantity2);
        validateTargetUnit(targetUnit);
        Quantity<?> result = quantity1.subtract(quantity2, targetUnit);
        saveQuantities(quantity1, quantity2);
        OperationHistory history = new OperationHistory(
                                        OperationType.SUBTRACT,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        result.toString());
        saveOperationHistory(history);
        return result;
    }


    @Override
    public double divide(Quantity<?> quantity1, Quantity<?> quantity2)
            throws UnsupportedOperationsException {
        validateQuantities(quantity1, quantity2);
        double ratio = Math.round(quantity1.divide(quantity2) * 100.0) / 100.0;
        saveQuantities(quantity1, quantity2);
        OperationHistory history = new OperationHistory(
                                        OperationType.DIVIDE,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        Double.toString(ratio));
        saveOperationHistory(history);
        return ratio;
    }

    @Override
    public List<Quantity<?>> getAllHistory() {
        return quantityRepository.findAll()
                .stream()
                .map(QuantityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Quantity<?>> getByMeasurementType(String measurementType) {
        return quantityRepository.findByMeasurementTypeIgnoreCase(measurementType)
                .stream()
                .map(QuantityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OperationHistory> getOperationHistory(){
        return  operationHistoryRepository.findAll()
                        .stream()
                        .map(OperationHistoryMapper::toOperationHistory)
                        .toList();
    }

    @Override
    public List<OperationHistory> getOperationHistory(String operationType){
        return  operationHistoryRepository.findByOperationTypeIgnoreCase(operationType)
                        .stream()
                        .map(OperationHistoryMapper::toOperationHistory)
                        .toList();
    }

    @Override
    public void deleteAll() {
        quantityRepository.deleteAll();
        operationHistoryRepository.deleteAll();
    }
}