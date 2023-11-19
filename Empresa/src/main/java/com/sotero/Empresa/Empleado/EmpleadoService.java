package com.sotero.Empresa.Empleado;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private static final int SUELDO_BASE[] = { 50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000,
            230000 };

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public int calcularSueldo(int categoria, int anyos) {
        if (categoria < 1 || categoria > SUELDO_BASE.length) {
            throw new IllegalArgumentException("Categoría de empleado no válida: " + categoria);
        }

        int sueldoBase = SUELDO_BASE[categoria - 1];
        int sueldo = sueldoBase + 5000 * anyos;

        return sueldo;
    }

    public void guardarEmpleado(EmpleadoModel empleado) {
        validarDni(empleado.getDni());

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

    public void eliminarEmpleado(int id) {
        empleadoRepository.deleteById(id);
    }

    public List<EmpleadoModel> buscarPorCualquierCampo(String search) {
        List<EmpleadoModel> empleados = empleadoRepository
                .findByNombreContainingOrDniContainingOrSexoContainingOrCategoriaContainingOrAnyosTrabajadosContaining(
                        search, search, search, search, search);
        System.out.println("Empleados encontrados: " + empleados);
        return empleados;
    }

    public EmpleadoModel buscarPorCampo(String search, String filterBy) {
        switch (filterBy) {
            case "nombre":
                return empleadoRepository.findByNombre(search);
            case "dni":
                return empleadoRepository.findByDni(search);
            case "sexo":
                return empleadoRepository.findBySexo(search);
            case "categoria":
                try {
                    int categoria = Integer.parseInt(search);
                    List<EmpleadoModel> empleados = empleadoRepository.findByCategoria(categoria);
                    return empleados.isEmpty() ? null : empleados.get(0);
                } catch (NumberFormatException e) {
                    return null;
                }
            case "anyosTrabajados":
                try {
                    int anyosTrabajados = Integer.parseInt(search);
                    List<EmpleadoModel> empleados = empleadoRepository.findByAnyosTrabajados(anyosTrabajados);
                    return empleados.isEmpty() ? null : empleados.get(0);
                } catch (NumberFormatException e) {
                    return null;
                }
            default:
                return null;
        }
    }

}
