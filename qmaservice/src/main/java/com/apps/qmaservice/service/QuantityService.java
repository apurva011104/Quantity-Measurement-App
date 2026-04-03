package com.apps.qmaservice.service;

import java.util.List;

import com.apps.qmaservice.domain.OperationHistory;
import com.apps.qmaservice.domain.Quantity;
import com.apps.qmaservice.exception.UnsupportedOperationsException;

public interface QuantityService {
    
    boolean checkEquality(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException;

    Quantity<?> convert(Quantity<?> quantity, String targetUnit);

    Quantity<?> add(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException;

    Quantity<?> add(Quantity<?> quantity1, Quantity<?> quantity2, String targetUnit) throws UnsupportedOperationsException;

    Quantity<?> subtract(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException;

    Quantity<?> subtract(Quantity<?> quantity1, Quantity<?> quantity2, String targetUnit) throws UnsupportedOperationsException;

    double divide(Quantity<?> quantity1, Quantity<?> quantity2) throws UnsupportedOperationsException;

    List<OperationHistory> getOperationHistory();

    List<OperationHistory> getOperationHistory(String operationType);

    void deleteOperationHistory();
}
