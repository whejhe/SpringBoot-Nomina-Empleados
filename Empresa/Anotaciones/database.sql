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