package com.apps.quantity_measurement_app.controller;

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
import com.apps.quantity_measurement_app.dto.requestDto.QuantityRequestDTO;
import com.apps.quantity_measurement_app.dto.requestDto.TwoQuantityRequestDTO;
import com.apps.quantity_measurement_app.exception.UnsupportedOperationsException;
import com.apps.quantity_measurement_app.mapper.OperationHistoryMapper;
import com.apps.quantity_measurement_app.mapper.QuantityMapper;
import com.apps.quantity_measurement_app.service.QuantityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/quantities")
public class QuantityMeasurementController {
    
    private final QuantityService service;

    public QuantityMeasurementController(QuantityService service) {
        this.service = service;
    }

    @PostMapping("/equals")
    public ResponseEntity<?> checkEquality(@Valid @RequestBody TwoQuantityRequestDTO requestDTOs) throws UnsupportedOperationsException{
        Quantity<?> quantity1 = QuantityMapper.dtoToDomain(requestDTOs.getQuantity1());
        Quantity<?> quantity2 = QuantityMapper.dtoToDomain(requestDTOs.getQuantity2());
        boolean result = service.checkEquality(quantity1, quantity2);
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody QuantityRequestDTO dto, @RequestParam String targetUnit) throws Exception{
        Quantity<?> quantity = QuantityMapper.dtoToDomain(dto);
        Quantity<?> converted = service.convert(quantity, targetUnit);
        return ResponseEntity.ok(QuantityMapper.domainToDto(converted));
        
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(
                            @RequestBody TwoQuantityRequestDTO requestDTOs,
                            @RequestParam(required = false) String targetUnit) 
                            throws UnsupportedOperationsException{
        Quantity<?> quantity1 = QuantityMapper.dtoToDomain(requestDTOs.getQuantity1());
        Quantity<?> quantity2 = QuantityMapper.dtoToDomain(requestDTOs.getQuantity2());
        Quantity<?> sum;
        if (targetUnit == null) {
            sum = service.add(quantity1, quantity2);
        } else {
            sum = service.add(quantity1, quantity2, targetUnit);
        }
        return ResponseEntity.ok(QuantityMapper.domainToDto(sum));
    }

    @PostMapping("/subtract")
    public ResponseEntity<?> subtract(
                            @RequestBody TwoQuantityRequestDTO requestDTOs,
                            @RequestParam(required = false) String targetUnit)
                            throws UnsupportedOperationsException{
        Quantity<?> quantity1 = QuantityMapper.dtoToDomain(requestDTOs.getQuantity1());
        Quantity<?> quantity2 = QuantityMapper.dtoToDomain(requestDTOs.getQuantity2());
        Quantity<?> diff;
        if (targetUnit == null) {
            diff = service.subtract(quantity1, quantity2);
        } else {
            diff = service.subtract(quantity1, quantity2, targetUnit);
        }
        return ResponseEntity.ok(QuantityMapper.domainToDto(diff));
        
    }

    @PostMapping("/divide")
    public ResponseEntity<?> divide(@RequestBody TwoQuantityRequestDTO requestDTOs) throws UnsupportedOperationsException{
        Quantity<?> quantity1 = QuantityMapper.dtoToDomain(requestDTOs.getQuantity1());
        Quantity<?> quantity2 = QuantityMapper.dtoToDomain(requestDTOs.getQuantity2());
        double ratio = service.divide(quantity1, quantity2);
        return ResponseEntity.ok(ratio);
    }

    @GetMapping("/operationsHistory")
    public ResponseEntity<?> getOperationHistory() {
        return ResponseEntity.ok(
                service.getOperationHistory()
                        .stream()
                        .map(OperationHistoryMapper::domainToDTO)
                        .toList());
    }

    @GetMapping("/operationsHistory/{operationType}")
    public ResponseEntity<?> getOperationHistory(@PathVariable String operationType) {
        return ResponseEntity.ok(
                service.getOperationHistory(operationType)
                        .stream()
                        .map(OperationHistoryMapper::domainToDTO)
                        .toList());
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAll() {
        service.deleteOperationHistory();
        return ResponseEntity.ok("All quantities record deleted");
    }
    
}
