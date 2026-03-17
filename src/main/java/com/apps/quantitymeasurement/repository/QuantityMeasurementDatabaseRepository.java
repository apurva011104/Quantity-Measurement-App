package com.apps.quantitymeasurement.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.units.IMeasurable;
import com.apps.quantitymeasurement.units.LengthUnit;
import com.apps.quantitymeasurement.units.TemperatureUnit;
import com.apps.quantitymeasurement.units.VolumeUnit;
import com.apps.quantitymeasurement.units.WeightUnit;
import com.apps.quantitymeasurement.util.DatabaseConfig;

public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    @Override
    public <U extends IMeasurable> void save(Quantity<U> quantity) throws DatabaseException{

        String query = "INSERT INTO quantities(quantity_value, unit, measurement_type) VALUES (?, ?, ?)";

        try{
            Connection connection = DatabaseConfig.getConnectionInstance();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setDouble(1, quantity.getValue());
            ps.setString(2, quantity.getUnit().getUnitName());
            ps.setString(3, quantity.getUnit().getClass().getSimpleName());

            ps.executeUpdate();
            ps.close();
            
        }
        catch(SQLException e){
            if(e.getMessage().contains("Unique index or primary key violation")){
                System.out.println("Duplicate entry ignored");
                return;
            }
            throw new DatabaseException("Error while saving quantity." , e);
        }
    }

    @Override
    public List<Quantity<?>> getAllMeasurements() throws DatabaseException{

        List<Quantity<?>> quantities = new ArrayList<>();
        String query = "SELECT * FROM quantities";
        try{
            Connection connection = DatabaseConfig.getConnectionInstance();
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                double value = resultSet.getDouble("quantity_value");
                String unit = resultSet.getString("unit");
                String measurementType = resultSet.getString("measurement_type");
                
                IMeasurable u = getUnit(unit, measurementType);

                Quantity<?> quantity = new Quantity<>(value, u);
                quantities.add(quantity);
            }
            ps.close();
            resultSet.close();
        } 
        catch (SQLException e) {
            throw new DatabaseException("Error while retrieving quantities", e);
        }
        return quantities;
    }

    @Override
    public List<Quantity<?>> getMeasurementsByType(String measurementType) throws DatabaseException{
        List<Quantity<?>> quantities = new ArrayList<>();
        String query = "SELECT * FROM quantities WHERE LOWER(measurement_type) = LOWER(?)";
        try{
            Connection connection = DatabaseConfig.getConnectionInstance();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, measurementType);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                double value = resultSet.getDouble("quantity_value");
                String unit = resultSet.getString("unit");
                String type = resultSet.getString("measurement_type");
                
                IMeasurable u = getUnit(unit, type);
                Quantity<?> quantity = new Quantity<>(value, u);
                quantities.add(quantity);
            }

            ps.close();
            resultSet.close();
        } 
        catch (SQLException e) {
            throw new DatabaseException("Error while retrieving quantities", e);
        }
        return quantities;
    }

    @Override
    public void deleteAll() throws DatabaseException{
        String query = "DELETE FROM quantities";
        try{
            Connection connection = DatabaseConfig.getConnectionInstance();
            PreparedStatement ps = connection.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
        }
        catch(SQLException e){
            throw new DatabaseException("Error while deleting all quantities." , e);
        }
    }

    private IMeasurable getUnit(String unit, String measurementType){
        unit = unit.toUpperCase();

        switch(measurementType){

            case "LengthUnit":
                return LengthUnit.valueOf(unit);

            case "VolumeUnit":
                return VolumeUnit.valueOf(unit);
                
            case "WeightUnit":
                return WeightUnit.valueOf(unit);

            case "TemperatureUnit":
                return TemperatureUnit.valueOf(unit);
        }

        throw new IllegalArgumentException("Invalid unit or measurement type");
    }
}
