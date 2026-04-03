package com.apps.qmaservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="operation_history")
public class OperationHistoryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(nullable=false)
    private String operationType;

    @Column(nullable=false)
    private String operand1;

    @Column
    private String operand2;

    @Column
    private String result;

    public OperationHistoryEntity() {
    }

    public OperationHistoryEntity(String operationType, String operand1, String operand2, String result) {
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = operand2;
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
