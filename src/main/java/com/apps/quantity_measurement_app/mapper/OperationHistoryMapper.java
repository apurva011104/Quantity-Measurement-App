package com.apps.quantity_measurement_app.mapper;

import com.apps.quantity_measurement_app.domain.OperationHistory;
import com.apps.quantity_measurement_app.dto.responseDto.OperationHistoryResponseDTO;
import com.apps.quantity_measurement_app.entity.OperationHistoryEntity;
import com.apps.quantity_measurement_app.util.OperationType;

public class OperationHistoryMapper {
    
    public static OperationHistoryEntity domainToEntity(OperationHistory history){
        String operationType = history.getOperationType().name();
        String operand1 = history.getOperand1();
        String operand2 = history.getOperand2();
        String result = history.getResult();

        OperationHistoryEntity entity 
                            = new OperationHistoryEntity(operationType, operand1,operand2, result);
        
        return entity;
    }

    public static OperationHistory entityToDomain(OperationHistoryEntity entity ){
        OperationType operationType = OperationType.valueOf(entity.getOperationType());
        String operand1 = entity.getOperand1();
        String operand2 = entity.getOperand2();
        String result = entity.getResult();

        return new OperationHistory(operationType, operand1, operand2, result);
    }

    public static OperationHistoryResponseDTO domainToDTO(OperationHistory history){
        return new OperationHistoryResponseDTO(
                        history.getOperationType().name(), history.getOperand1(), history.getOperand2(), history.getResult());
    }

}
