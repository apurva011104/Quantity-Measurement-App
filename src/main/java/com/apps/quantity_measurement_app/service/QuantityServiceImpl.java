package com.apps.quantity_measurement_app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.apps.quantity_measurement_app.domain.Quantity;
import com.apps.quantity_measurement_app.exception.UnsupportedOperationsException;
import com.apps.quantity_measurement_app.mapper.QuantityMapper;
import com.apps.quantity_measurement_app.repository.QuantityRepository;
import com.apps.quantity_measurement_app.units.IMeasurable;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class QuantityServiceImpl implements QuantityService {

    private final QuantityRepository repository;

    public QuantityServiceImpl(QuantityRepository repository) {
        this.repository = repository;
    }

    private void save(Quantity<?>... quantities) {
        for (Quantity<?> q : quantities) {
            if (q != null) {
                System.out.println("Saving: " + q);
                repository.save(QuantityMapper.toEntity(q));
            }
        }
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
        save(quantity1, quantity2);
        return quantity1.equals(quantity2);
    }

    @Override
    public <U extends IMeasurable> Quantity<?> convert(Quantity<?> quantity, U targetUnit) {
        validateQuantities(quantity);
        validateTargetUnit(targetUnit);
        Quantity<?> result = quantity.convertTo(targetUnit);
        save(quantity);
        return result;
    }

    @Override
    public Quantity<?> add(Quantity<?> quantity1, Quantity<?> quantity2)
            throws UnsupportedOperationsException {
        validateQuantities(quantity1, quantity2);
        Quantity<?> result = quantity1.add(quantity2);
        save(quantity1, quantity2);
        return result;
    }

    @Override
    public <U extends IMeasurable> Quantity<?> add(Quantity<?> quantity1, Quantity<?> quantity2, U targetUnit) throws UnsupportedOperationsException{
        validateQuantities(quantity1, quantity2);
        validateTargetUnit(targetUnit);
        Quantity<?> result = quantity1.add(quantity2, targetUnit);
        save(quantity1, quantity2);
        return result;
    }

    @Override
    public Quantity<?> subtract(Quantity<?> quantity1, Quantity<?> quantity2)
            throws UnsupportedOperationsException {
        validateQuantities(quantity1, quantity2);
        Quantity<?> result = quantity1.subtract(quantity2);
        save(quantity1, quantity2);
        return result;
    }

    @Override
    public <U extends IMeasurable> Quantity<?> subtract(Quantity<?> quantity1, Quantity<?> quantity2, U targetUnit) throws UnsupportedOperationsException{
        validateQuantities(quantity1, quantity2);
        validateTargetUnit(targetUnit);
        Quantity<?> result = quantity1.subtract(quantity2, targetUnit);
        save(quantity1, quantity2);
        return result;
    }


    @Override
    public double divide(Quantity<?> quantity1, Quantity<?> quantity2)
            throws UnsupportedOperationsException {
        validateQuantities(quantity1, quantity2);
        double ratio = Math.round(quantity1.divide(quantity2) * 100.0) / 100.0;
        save(quantity1, quantity2);
        return ratio;
    }

    @Override
    public List<Quantity<?>> getAllHistory() {
        return repository.findAll()
                .stream()
                .map(QuantityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Quantity<?>> getByMeasurementType(String type) {
        return repository.findByMeasurementTypeIgnoreCase(type)
                .stream()
                .map(QuantityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}