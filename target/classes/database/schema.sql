DROP TABLE IF EXISTS quantities;

CREATE TABLE IF NOT EXISTS quantities(
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity_value DOUBLE NOT NULL,
    unit VARCHAR(50) NOT NULL,
    measurement_type VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(quantity_value, unit, measurement_type)
);