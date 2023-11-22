package com.sotero.Empresa.Empleado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoModel, Integer> {

    EmpleadoModel save(EmpleadoModel empleado);

    Optional<EmpleadoModel> findById(Integer id);

    List<EmpleadoModel> findAll();

    EmpleadoModel findByNombre(String nombre);

    EmpleadoModel findByDni(String dni);

    EmpleadoModel findBySueldo(Integer sueldo);

    List<EmpleadoModel> findBySexo(String sexo);

    List<EmpleadoModel> findByCategoria(int categoria);

    List<EmpleadoModel> findByAnyosTrabajados(int anyosTrabajados);

    void deleteById(Integer id);

}
