package com.apps.quantity_measurement_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="operation_history")
public class OperationHistoryEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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
        return "Operation History{"
                    +"Operation Type: " + operationType
                    +"Operand1: " + operand1
                    +"Operand2: " + operand2
                    +"Result: " + result +"}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
