package com.apps.quantitymeasurement;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.units.LengthUnit;
import com.apps.quantitymeasurement.units.TemperatureUnit;
import com.apps.quantitymeasurement.units.VolumeUnit;
import com.apps.quantitymeasurement.units.WeightUnit;
import com.apps.quantitymeasurement.util.DatabaseConfig;

public class QuantityMeasurementAppTest {

    private Connection connection;

    @BeforeEach
    private void initializeConnection() throws DatabaseException, SQLException{
        this.connection = DatabaseConfig.getConnectionInstance();
        connection.prepareStatement("DELETE FROM quantities").executeUpdate();
    }
    
    @Test
    public void testDatabaseConfiguration_PropertiesFileExists() throws IOException{
        InputStream input = DatabaseConfig.class.getClassLoader()
                                .getResourceAsStream("application.properties");
        assertTrue(input != null);
    }

    @ParameterizedTest
    @ValueSource(strings={"db.url","db.username","db.password","db.driver"})
    public void testDatabaseConfiguration_LoadedFromProperties(String property) throws IOException{
        Properties properties = new Properties();
        InputStream input = DatabaseConfig.class.getClassLoader()
                                .getResourceAsStream("application.properties");
        properties.load(input);
        String value = properties.getProperty(property);
        assertTrue(value != null, property+" is null" );
        input.close();
    }
    
    @Test
    public void testDatabaseConfiguration_DatabaseConnectionEstablished() throws DatabaseException, SQLException{
        assertTrue(connection != null && !connection.isClosed());
    }

    @Test
    public void testDatabaseConfiguration_TableExists() throws DatabaseException, SQLException{
        assertTrue( connection.getMetaData().getTables(null, null, "QUANTITIES", null).next());
    }

    @Test
    public void testDatabaseRepository_SaveEntity() throws DatabaseException, SQLException{
        QuantityMeasurementDatabaseRepository repository = new QuantityMeasurementDatabaseRepository();
        repository.save(new Quantity<>(15.0, LengthUnit.FEET));
        ResultSet rs = connection.prepareStatement("SELECT COUNT(*) FROM quantities WHERE quantity_value=15.0 AND unit='feet'").executeQuery();
        rs.next();
        int count = rs.getInt(1);
        assertTrue(count==1);
        rs.close();
    }

    @Test
    public void testDatabaseRepository_SaveEntity_DuplicatePrevention() throws DatabaseException, SQLException{
        QuantityMeasurementDatabaseRepository repository = new QuantityMeasurementDatabaseRepository();
        Quantity<LengthUnit> quantity = new Quantity<>(33.0, LengthUnit.FEET);
        repository.save(quantity);
        repository.save(quantity);
        ResultSet rs = connection.prepareStatement("SELECT COUNT(*) FROM quantities WHERE quantity_value=33.0 AND unit='feet'").executeQuery();
        rs.next();
        int count = rs.getInt(1);
        assertTrue(count==1);
        rs.close();
    }

    @Test
    public void testDatabaseRepository_RetrieveAllMeasurements() throws DatabaseException, SQLException{
        QuantityMeasurementDatabaseRepository repository = new QuantityMeasurementDatabaseRepository();
        Quantity<LengthUnit> quantity1 = new Quantity<>(12.0, LengthUnit.FEET);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(25.0, VolumeUnit.GALLON);
        Quantity<WeightUnit> quantity3 = new Quantity<>(34.0, WeightUnit.GRAMS);
        Quantity<TemperatureUnit> quantity4 = new Quantity<>(12.0, TemperatureUnit.CELSIUS);

        List<Quantity<?>> quantities = Arrays.asList(quantity1, quantity2, quantity3, quantity4);

        for(Quantity<?> quantity: quantities){
            repository.save(quantity);
        }

        List<Quantity<?>> retrieved = repository.getAllMeasurements();

        for(Quantity<?> quantity: quantities){
            assertTrue(retrieved.contains(quantity), quantity+" not found in retrieved");
        }

        for(Quantity<?> quantity: retrieved){
            assertTrue(quantities.contains(quantity), quantity+" not found in quantities");
        }

    }

    @Test
    public void testDatabaseRepository_QueryByMeasurementType() throws DatabaseException, SQLException{
        QuantityMeasurementDatabaseRepository repository = new QuantityMeasurementDatabaseRepository();
        Quantity<LengthUnit> quantity1 = new Quantity<>(12.0, LengthUnit.FEET);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(25.0, VolumeUnit.GALLON);
        Quantity<WeightUnit> quantity3 = new Quantity<>(34.0, WeightUnit.GRAMS);
        Quantity<TemperatureUnit> quantity4 = new Quantity<>(12.0, TemperatureUnit.CELSIUS);
        Quantity<WeightUnit> quantity5 = new Quantity<>(22.0, WeightUnit.KILOGRAMS);

        List<Quantity<?>> quantities = Arrays.asList(quantity1, quantity2, quantity3, quantity4,quantity5);

        for(Quantity<?> quantity: quantities){
            repository.save(quantity);
        }

        List<Quantity<?>> expected = Arrays.asList(quantity3, quantity5);

        List<Quantity<?>> retrieved = repository.getMeasurementsByType("WeightUnit");

        for(Quantity<?> quantity: expected){
            assertTrue(retrieved.contains(quantity), quantity+" not found in retrieved");
        }

        for(Quantity<?> quantity: retrieved){
            assertTrue(expected.contains(quantity), quantity+" not found in quantities");
        }
    }

    @Test
    public void testDatabaseRepository_deleteAll() throws DatabaseException, SQLException{
        QuantityMeasurementDatabaseRepository repository = new QuantityMeasurementDatabaseRepository();
        Quantity<LengthUnit> quantity1 = new Quantity<>(12.0, LengthUnit.FEET);
        Quantity<VolumeUnit> quantity2 = new Quantity<>(25.0, VolumeUnit.GALLON);
        Quantity<WeightUnit> quantity3 = new Quantity<>(34.0, WeightUnit.GRAMS);
        Quantity<TemperatureUnit> quantity4 = new Quantity<>(12.0, TemperatureUnit.CELSIUS);
        Quantity<WeightUnit> quantity5 = new Quantity<>(22.0, WeightUnit.KILOGRAMS);

        List<Quantity<?>> quantities = Arrays.asList(quantity1, quantity2, quantity3, quantity4,quantity5);

        for(Quantity<?> quantity: quantities){
            repository.save(quantity);
        }

        repository.deleteAll();

        List<Quantity<?>> emptyList = repository.getAllMeasurements();

        assertTrue(emptyList.isEmpty());
    }
}
