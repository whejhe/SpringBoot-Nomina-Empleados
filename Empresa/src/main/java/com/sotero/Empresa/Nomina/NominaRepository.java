package com.sotero.Empresa.Nomina;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NominaRepository extends JpaRepository<NominaModel, Integer> {

    NominaModel save(NominaModel nomina);

    Optional<NominaModel> findById(Integer id);

    List<NominaModel> findAll();
    
    NominaModel findBySueldo(int sueldo);

    void deleteById(Integer id);

}
