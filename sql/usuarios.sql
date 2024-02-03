-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS fp2_23b;
USE fp2_23b;

-- Crear la tabla de usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(255) NOT NULL,
    puntuacion INT NOT NULL
);

-- Insertar datos de ejemplo
INSERT INTO usuarios (usuario, puntuacion) VALUES
('Pepito', 10),
('Juanito', 150),
('Reginald', 80);
