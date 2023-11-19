package com.sotero.Empresa.Nomina;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NominaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNomina;

	private int sueldo;

	public NominaModel(int salario) {
	}

	public int getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(int idNomina) {
		this.idNomina = idNomina;
	}

	public int getSueldo() {
		return sueldo;
	}

	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}

}