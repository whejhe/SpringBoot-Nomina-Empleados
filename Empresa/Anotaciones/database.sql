DROP DATABASE IF EXISTS Empresa;

CREATE DATABASE Empresa;

USE Empresa;

CREATE TABLE Empleado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    dni VARCHAR(10) NOT NULL,
    sexo CHAR(1) NOT NULL,
    categoria INT NOT NULL,
    anyosTrabajados INT NOT NULL
);

CREATE TABLE Nomina (
    idNomina INT AUTO_INCREMENT PRIMARY KEY,
    sueldo INT NOT NULL
);

-- Insertar empleados ficticios
INSERT INTO empleado_model (nombre, dni, sexo, categoria, anyos_trabajados)
VALUES ('Juan Pérez', '12345678A', 'M', 2, 3);

INSERT INTO empleado_model (nombre, dni, sexo, categoria, anyos_trabajados)
VALUES ('María Gómez', '98765432B', 'F', 3, 5);

INSERT INTO empleado_model (nombre, dni, sexo, categoria, anyos_trabajados)
VALUES ('Javier Rodríguez', '56789012C', 'M', 1, 2);

INSERT INTO empleado_model (nombre, dni, sexo, categoria, anyos_trabajados)
VALUES ('Ana Martínez', '34567890D', 'F', 4, 7);

INSERT INTO empleado_model (nombre, dni, sexo, categoria, anyos_trabajados)
VALUES ('Carlos López', '45678901E', 'M', 3, 4);

SELECT * FROM empleado_model;

-- Insertar nóminas ficticias
INSERT INTO nomina_model (id_nomina, sueldo)
VALUES (1, 60000);

INSERT INTO nomina_model (id_nomina, sueldo)
VALUES (2, 80000);

INSERT INTO nomina_model (id_nomina, sueldo)
VALUES (3, 55000);

INSERT INTO nomina_model (id_nomina, sueldo)
VALUES (4, 72000);

INSERT INTO nomina_model (id_nomina, sueldo)
VALUES (5, 68000);

SELECT * FROM nomina_model;
