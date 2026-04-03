package com.apps.qmaservice.domain;

import com.apps.qmaservice.util.OperationType;

public class OperationHistory {

    private OperationType operationType;
    private String operand1;
    private String operand2;
    private String result;

    public OperationHistory(){
    }

    public OperationHistory(OperationType operationType, String operand1, String operand2, String result) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
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
        return "Operation History{"
                    +"Operation Type: " + operationType
                    +"Operand1: " + operand1
                    +"Operand2: " + operand2
                    +"Result: " + result +"}";
    }

    
    
}
