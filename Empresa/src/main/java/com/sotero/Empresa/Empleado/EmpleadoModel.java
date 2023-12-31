package com.sotero.Empresa.Empleado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmpleadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 60)
    private String nombre;
    @Column(nullable = false, length = 60)
    private String dni;
    @Column(nullable = false, length = 1)
    private String sexo;
    @Column(nullable = false)
    private int categoria;
    @Column(nullable = false)
    private int anyosTrabajados;
    private int sueldo;
    private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
            230000 };

    public EmpleadoModel(String dni, String sexo, int categoria, int anyosTrabajados, int sueldo) {
        this.dni = dni;
        this.sexo = sexo;
        this.categoria = categoria;
        if (this.categoria <= 0) {
            categoria = 1;
            this.categoria = categoria;
        }
        this.anyosTrabajados = anyosTrabajados;
        if (this.anyosTrabajados < 0) {
            anyosTrabajados = 0;
            this.anyosTrabajados = anyosTrabajados;
        }
        this.sueldo = getCalcularSueldo(categoria, anyosTrabajados);
    }

    public EmpleadoModel() {
    }

    public int getCalcularSueldo(int categoria, int anyos) {
        if (categoria < 1 || categoria > SUELDO_BASE.length) {
            throw new IllegalArgumentException("Categoría de empleado no válida: " + categoria);
        }

        int sueldoBase = SUELDO_BASE[categoria - 1];
        int sueldo = sueldoBase + 5000 * anyos;

        return sueldo;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() < 3 || nombre.length() > 60) {
            throw new IllegalArgumentException("El nombre " + nombre + " no es valido");
        } else if (nombre.isBlank()) {
            throw new IllegalArgumentException("El campo nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni.length() < 9) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido");
        }
        this.dni = dni;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
       
        this.sexo = sexo;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        if (categoria < 1 || categoria > 10) {
            throw new IllegalArgumentException("La categoria debe estar entre 1 y 10");
        }
        this.categoria = categoria;
    }

    public int getAnyosTrabajados() {
        return anyosTrabajados;
    }

    public void setAnyosTrabajados(int anyosTrabajados) {
        if (anyosTrabajados < 0 || anyosTrabajados > 60) {
            throw new IllegalArgumentException("Los años no pueden ser negativos ni superiores los 60");
        }
        this.anyosTrabajados = anyosTrabajados;
    }
}
