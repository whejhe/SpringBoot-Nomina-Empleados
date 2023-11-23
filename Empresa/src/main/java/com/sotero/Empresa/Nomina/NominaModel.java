package com.sotero.Empresa.Nomina;

import com.sotero.Empresa.Empleado.EmpleadoModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class NominaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNomina;

	@ManyToOne
	@JoinColumn(name = "id")
	private EmpleadoModel empleado;

	public NominaModel() {

	}

	public int getIdNomina() {
		return idNomina;
	}

	public void setIdNomina(int idNomina) {
		this.idNomina = idNomina;
	}

}
