package com.apps.quantity_measurement_app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class TwoQuantityRequestDTO {
    @Valid
    @NotNull
    private QuantityRequestDTO quantity1;

    @Valid
    @NotNull
    private QuantityRequestDTO quantity2;

    public QuantityRequestDTO getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(QuantityRequestDTO quantity1) {
        this.quantity1 = quantity1;
    }

    public QuantityRequestDTO getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(QuantityRequestDTO quantity2) {
        this.quantity2 = quantity2;
    }
}
