package com.apps.quantity_measurement_app.controller;

import java.util.Map;
import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apps.quantity_measurement_app.domain.Quantity;
import com.apps.quantity_measurement_app.dto.QuantityRequestDTO;
import com.apps.quantity_measurement_app.dto.QuantityResponseDTO;
import com.apps.quantity_measurement_app.dto.TwoQuantityRequestDTO;
import com.apps.quantity_measurement_app.entity.QuantityEntity;
import com.apps.quantity_measurement_app.exception.UnsupportedOperationsException;
import com.apps.quantity_measurement_app.mapper.QuantityMapper;
import com.apps.quantity_measurement_app.service.QuantityService;
import com.apps.quantity_measurement_app.units.IMeasurable;
import com.apps.quantity_measurement_app.units.LengthUnit;
import com.apps.quantity_measurement_app.units.TemperatureUnit;
import com.apps.quantity_measurement_app.units.VolumeUnit;
import com.apps.quantity_measurement_app.units.WeightUnit;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/quantities")
public class QuantityMeasurementController {
    private final QuantityService service;

    public QuantityMeasurementController(QuantityService service) {
        this.service = service;
    }

    private Quantity<?> toDomain(QuantityRequestDTO dto){
        QuantityEntity entity = new QuantityEntity(
                                dto.getValue(),
                                dto.getUnit(),
                                dto.getMeasurementType());

        return QuantityMapper.toDomain(entity);
    }

    private QuantityResponseDTO toDTO(Quantity<?> quantity){
        QuantityResponseDTO responseDTO = new QuantityResponseDTO(
                                quantity.getValue(), quantity.getUnit().getUnitName(), quantity.getUnit().getClass().getSimpleName());
        return responseDTO;
    }

    private static final Map<String, Function<String,? extends IMeasurable>> units = Map.of(
                "LengthUnit", unitType -> LengthUnit.valueOf(unitType),
                "WeightUnit", unitType -> WeightUnit.valueOf(unitType),
                "VolumeUnit", unitType -> VolumeUnit.valueOf(unitType),
                "TemperatureUnit", unitType -> TemperatureUnit.valueOf(unitType)
            );
            
    private IMeasurable getUnit(String measurementType, String unit){
        Function<String,? extends IMeasurable> f = units.get(measurementType);
        if(f==null){
            throw new IllegalArgumentException("Invalid "+unit+" for measurement type"+measurementType);
        }
        return f.apply(unit.toUpperCase());
    }

    @PostMapping("/equals")
    public ResponseEntity<?> checkEquality(@Valid @RequestBody TwoQuantityRequestDTO requestDTOs){
        Quantity<?> quantity1 = toDomain(requestDTOs.getQuantity1());
        Quantity<?> quantity2 = toDomain(requestDTOs.getQuantity2());
        try{
            boolean result = service.checkEquality(quantity1, quantity2);
            return ResponseEntity.ok(result);
        }
        catch(UnsupportedOperationsException | IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody QuantityRequestDTO dto, @RequestParam String targetUnit) {
        try {
            Quantity<?> quantity = toDomain(dto);
            String measurementType = quantity.getUnit().getClass().getSimpleName();
            IMeasurable target = getUnit( measurementType, targetUnit);
            Quantity<?> converted = service.convert(quantity, target);
            return ResponseEntity.ok(toDTO(converted));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
                            @RequestBody TwoQuantityRequestDTO requestDTOs,
                            @RequestParam(required = false) String targetUnit) {
        Quantity<?> quantity1 = toDomain(requestDTOs.getQuantity1());
        Quantity<?> quantity2 = toDomain(requestDTOs.getQuantity2());
        try {
            Quantity<?> sum;
            if (targetUnit == null) {
                sum = service.add(quantity1, quantity2);
            } else {
                String measurementType = quantity1.getUnit().getClass().getSimpleName();
                IMeasurable target = getUnit(measurementType, targetUnit);
                sum = service.add(quantity1, quantity2, target);
            }
            return ResponseEntity.ok(toDTO(sum));
        } 
        catch (UnsupportedOperationsException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/subtract")
    public ResponseEntity<?> subtract(
                            @RequestBody TwoQuantityRequestDTO requestDTOs,
                            @RequestParam(required = false) String targetUnit) {
        Quantity<?> quantity1 = toDomain(requestDTOs.getQuantity1());
        Quantity<?> quantity2 = toDomain(requestDTOs.getQuantity2());
        try {
            Quantity<?> diff;
            if (targetUnit == null) {
                diff = service.subtract(quantity1, quantity2);
            } else {
                String measurementType = quantity1.getUnit().getClass().getSimpleName();
                IMeasurable target = getUnit(measurementType, targetUnit);
                diff = service.subtract(quantity1, quantity2, target);
            }
            return ResponseEntity.ok(toDTO(diff));
        } 
        catch (UnsupportedOperationsException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/divide")
    public ResponseEntity<?> divide(@RequestBody TwoQuantityRequestDTO requestDTOs) {
        try{
            Quantity<?> quantity1 = toDomain(requestDTOs.getQuantity1());
            Quantity<?> quantity2 = toDomain(requestDTOs.getQuantity2());
            double ratio = service.divide(quantity1, quantity2);
            return ResponseEntity.ok(ratio);
        }
        catch(UnsupportedOperationsException | ArithmeticException | IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/history")
    public ResponseEntity<?> getAllHistory() {
        return ResponseEntity.ok(
                    service.getAllHistory().stream()
                           .map(this::toDTO)
                           .toList());
    }
    
    @GetMapping("/history/{type}")
    public ResponseEntity<?> getByType(@PathVariable String type) {
        return ResponseEntity.ok(
                service.getByMeasurementType(type).stream()
                                .map(this::toDTO)
                                .toList());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok("All records deleted");
    }
    
}
