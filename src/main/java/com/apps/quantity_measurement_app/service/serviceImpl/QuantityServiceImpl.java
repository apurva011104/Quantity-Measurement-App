package com.apps.quantity_measurement_app.service.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.apps.quantity_measurement_app.domain.OperationHistory;
import com.apps.quantity_measurement_app.domain.Quantity;
import com.apps.quantity_measurement_app.entity.User;
import com.apps.quantity_measurement_app.exception.UnsupportedOperationsException;
import com.apps.quantity_measurement_app.mapper.OperationHistoryMapper;
import com.apps.quantity_measurement_app.repository.OperationHistoryRepository;
import com.apps.quantity_measurement_app.repository.UserRepository;
import com.apps.quantity_measurement_app.service.QuantityService;
import com.apps.quantity_measurement_app.units.IMeasurable;
import com.apps.quantity_measurement_app.units.LengthUnit;
import com.apps.quantity_measurement_app.units.TemperatureUnit;
import com.apps.quantity_measurement_app.units.VolumeUnit;
import com.apps.quantity_measurement_app.units.WeightUnit;
import com.apps.quantity_measurement_app.util.OperationType;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QuantityServiceImpl implements QuantityService {

    private final OperationHistoryRepository operationHistoryRepository;
    private final UserRepository userRepository;
    

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void saveOperationHistory(OperationHistory history){
        if(history==null){
            throw new IllegalArgumentException("Operation history cannot be null");
        }
        User user = getCurrentUser();
        var entity = OperationHistoryMapper.domainToEntity(history);
        entity.setUser(user);
        operationHistoryRepository.save(entity);
        System.out.println("Operation History saved successfully");
    }

    private void validateQuantities(Quantity<?>... quantities) {
        for (Quantity<?> q : quantities) {
            if (q == null) {
                throw new IllegalArgumentException("Invalid quantity");
            }
        }
    }

    private void validateTargetUnit(String targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Invalid target unit");
        }
    }

    private static final Map<String, Function<String,? extends IMeasurable>> units = Map.of(
                "LengthUnit", unitType -> LengthUnit.valueOf(unitType.toUpperCase()),
                "WeightUnit", unitType -> WeightUnit.valueOf(unitType.toUpperCase()),
                "VolumeUnit", unitType -> VolumeUnit.valueOf(unitType.toUpperCase()),
                "TemperatureUnit", unitType -> TemperatureUnit.valueOf(unitType.toUpperCase())
            );
            
    private IMeasurable getUnit(String measurementType, String unit){
        Function<String,? extends IMeasurable> f = units.get(measurementType);
        if(f==null){
            throw new IllegalArgumentException("Invalid "+unit+" for measurement type"+measurementType);
        }
        return f.apply(unit.toUpperCase());
    }

    @Override
    public boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException {
        validateQuantities(quantity1, quantity2);
        if (!quantity1.getUnit().getClass().equals(quantity2.getUnit().getClass())) {
            throw new UnsupportedOperationsException("Different measurement types");
        }
        boolean result = quantity1.equals(quantity2);
        OperationHistory history = new OperationHistory(
                                        OperationType.COMPARE,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        Boolean.toString(result));
        saveOperationHistory(history);
        return result;
    }

    @Override
    public Quantity<?> convert(Quantity<?> quantity, String targetUnit) {
        validateQuantities(quantity);
        validateTargetUnit(targetUnit);
        String measurementType = quantity.getUnit().getClass().getSimpleName();
        IMeasurable target = getUnit(measurementType, targetUnit);
        Quantity<?> result = quantity.convertTo(target);
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
        OperationHistory history = new OperationHistory(
                                        OperationType.ADD,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        result.toString());
        saveOperationHistory(history);
        return result;
    }

    @Override
    public Quantity<?> add(Quantity<?> quantity1, Quantity<?> quantity2, String targetUnit) throws UnsupportedOperationsException{
        validateQuantities(quantity1, quantity2);
        validateTargetUnit(targetUnit);
        String measurementType = quantity1.getUnit().getClass().getSimpleName();
        IMeasurable target = getUnit( measurementType, targetUnit);
        Quantity<?> result = quantity1.add(quantity2, target);
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
        OperationHistory history = new OperationHistory(
                                        OperationType.SUBTRACT,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        result.toString());
        saveOperationHistory(history);
        return result;
    }

    @Override
    public Quantity<?> subtract(Quantity<?> quantity1, Quantity<?> quantity2, String targetUnit) throws UnsupportedOperationsException{
        validateQuantities(quantity1, quantity2);
        validateTargetUnit(targetUnit);
        String measurementType = quantity1.getUnit().getClass().getSimpleName();
        IMeasurable target = getUnit( measurementType, targetUnit);
        Quantity<?> result = quantity1.subtract(quantity2, target);
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
        OperationHistory history = new OperationHistory(
                                        OperationType.DIVIDE,
                                        quantity1.toString(),
                                        quantity2.toString(),
                                        Double.toString(ratio));
        saveOperationHistory(history);
        return ratio;
    }

    @Override
    public List<OperationHistory> getOperationHistory(){

        User user = getCurrentUser();

        return operationHistoryRepository.findByUser(user)
                .stream()
                .map(OperationHistoryMapper::entityToDomain)
                .toList();
    }

    @Override
    public List<OperationHistory> getOperationHistory(String operationType){

        User user = getCurrentUser();

        return operationHistoryRepository
                .findByUserAndOperationTypeIgnoreCase(user, operationType)
                .stream()
                .map(OperationHistoryMapper::entityToDomain)
                .toList();
    }


    @Override
    public void deleteOperationHistory() {
        User user = getCurrentUser();
        operationHistoryRepository.deleteByUser(user);
    }
}