package com.apps.quantity_measurement_app.mapper;

import java.util.Map;
import java.util.function.Function;

import com.apps.quantity_measurement_app.domain.Quantity;
import com.apps.quantity_measurement_app.dto.requestDto.QuantityRequestDTO;
import com.apps.quantity_measurement_app.dto.responseDto.QuantityResponseDTO;
import com.apps.quantity_measurement_app.entity.QuantityEntity;
import com.apps.quantity_measurement_app.units.IMeasurable;
import com.apps.quantity_measurement_app.units.LengthUnit;
import com.apps.quantity_measurement_app.units.TemperatureUnit;
import com.apps.quantity_measurement_app.units.VolumeUnit;
import com.apps.quantity_measurement_app.units.WeightUnit;

public final class QuantityMapper {

    private QuantityMapper(){
    }

    private static final Map<String, Function<String,? extends IMeasurable>> units = Map.of(
                "LengthUnit", unitType -> LengthUnit.valueOf(unitType),
                "WeightUnit", unitType -> WeightUnit.valueOf(unitType),
                "VolumeUnit", unitType -> VolumeUnit.valueOf(unitType),
                "TemperatureUnit", unitType -> TemperatureUnit.valueOf(unitType)
            );

    public static Quantity<?> dtoToDomain(QuantityRequestDTO dto){
        QuantityEntity entity = new QuantityEntity(
                                dto.getQuantityValue(),
                                dto.getUnit(),
                                dto.getMeasurementType());

        return entityToDomain(entity);
    }

    public static QuantityResponseDTO domainToDto(Quantity<?> quantity){
        QuantityResponseDTO responseDTO = new QuantityResponseDTO(
                                quantity.getValue(), quantity.getUnit().getUnitName(), quantity.getUnit().getClass().getSimpleName());
        return responseDTO;
    }

    public static QuantityEntity domainToEntity(Quantity<?> quantity){
        if(quantity==null){
            throw new IllegalArgumentException("Quantity cannot be null");
        }
        return new QuantityEntity(
        quantity.getValue(),
        quantity.getUnit().getUnitName(),
        quantity.getUnit().getClass().getSimpleName());
    }

    @SuppressWarnings("unchecked")
    public static Quantity<? extends IMeasurable> entityToDomain(QuantityEntity entity){
        if(entity==null){
            throw new IllegalArgumentException("Entity cannot be null");
        }
        double value = entity.getQuantityValue();
        String entityUnit = entity.getUnit();
        String measurementType = entity.getMeasurementType();

        if(entityUnit==null || measurementType==null){
            throw new IllegalArgumentException("Invalid unit '" + entityUnit + "' for measurement type '" + measurementType + "'");
        }
        if(!units.containsKey(measurementType)){
            throw new IllegalArgumentException("Unknown measurement type: " + measurementType);
        }
        try {
            IMeasurable unit = units.get(measurementType).apply(entityUnit.toUpperCase());
            return new Quantity<>(value, unit);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown unit type: " + entityUnit);
        }
    }
}
