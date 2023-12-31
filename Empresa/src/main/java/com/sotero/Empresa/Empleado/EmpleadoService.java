package com.sotero.Empresa.Empleado;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EmpleadoService {

	@Autowired
	private final EmpleadoRepository empleadoRepository;

	public EmpleadoService(EmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}

	public void guardarEmpleado(EmpleadoModel empleado) {
		validarDni(empleado.getDni());
		empleado.setSueldo(empleado.getCalcularSueldo(empleado.getCategoria(), empleado.getAnyosTrabajados()));
		empleadoRepository.save(empleado);
	}

	private void validarDni(String dni) {
		String regex = "\\d{8}[A-HJ-NP-TV-Z]";
		if (!dni.matches(regex)) {
			throw new IllegalArgumentException("El DNI no tiene el formato válido.");
		}
	}

	public List<EmpleadoModel> obtenerTodos() {
		List<EmpleadoModel> empleados = empleadoRepository.findAll();
		System.out.println("Empleados encontrados: " + empleados);
		return empleados;
	}

	public EmpleadoModel obtenerPorId(int id) {
		return empleadoRepository.findById(id).orElse(null);
	}

	public List<EmpleadoModel> buscarPorCampo(String search, String filterBy) {
		if (search == null || search.isEmpty()) {
			throw new IllegalArgumentException("El parámetro de búsqueda no puede ser nulo o vacío");
		}

		switch (filterBy) {
		case "nombre":
			return Collections.singletonList(empleadoRepository.findByNombre(search));
		case "dni":
			return Collections.singletonList(empleadoRepository.findByDni(search));
		case "sexo":
			return empleadoRepository.findBySexo(search);
		case "categoria":
			try {
				int categoria = Integer.parseInt(search);
				return empleadoRepository.findByCategoria(categoria);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("La categoría no es un número válido");
			}
		case "anyosTrabajados":
			try {
				int anyosTrabajados = Integer.parseInt(search);
				return empleadoRepository.findByAnyosTrabajados(anyosTrabajados);
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Años trabajados no es un número válido");
			}
		default:
			return Collections.emptyList();
		}
	}

	// Actualizar lista
	public void actualizarSalarios() {
		List<EmpleadoModel> empleados = empleadoRepository.findAll();
		for (EmpleadoModel empleado : empleados) {
			int nuevoSueldo = empleado.getCalcularSueldo(empleado.getCategoria(), empleado.getAnyosTrabajados());
			empleado.setSueldo(nuevoSueldo);
			empleadoRepository.save(empleado);
		}
	}

	// Eliminar Empleado
	public void eliminarEmpleadoPorId(int empleadoId) {
		empleadoRepository.deleteById(empleadoId);
	}

	// Editar Empleado
	public EmpleadoModel obtenerEmpleadoPorId(int empleadoId) {
		Optional<EmpleadoModel> empleadoOptional = empleadoRepository.findById(empleadoId);
		return empleadoOptional.orElse(null);
	}

	// Actualizar Empleado
	@Transactional
	public void actualizarEmpleado(EmpleadoModel empleado) {
		empleadoRepository.save(empleado);
	}
}
