package com.apps.quantity_measurement_app.dto.responseDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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
