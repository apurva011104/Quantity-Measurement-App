package com.apps.qmaservice.dto.responseDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class OperationHistoryResponseDTO {
    
    @NotNull(message="Operation Type cannot be empty")
    @Pattern(regexp = "COMPARE|CONVERT|ADD|SUBTRACT|DIVIDE", message="Invalid operation type")
    private String operationType;

    @NotNull(message="Operand1 cannot be empty")
    private String operand1;

    private String operand2;

    @NotNull(message="Result cannot be empty")
    private String result;

    public OperationHistoryResponseDTO() {
    }

    public OperationHistoryResponseDTO(String operationType, String operand1, String operand2, String result) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Operation History Response DTO{"
                    +"Operation Type: " + operationType
                    +"Operand1: " + operand1
                    +"Operand2: " + operand2
                    +"Result: " + result +"}";
    }
}
