package com.sotero.Empresa.Nomina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NominaRepository extends JpaRepository<NominaModel, Integer> {

    NominaModel save(NominaModel nomina);

    Optional<NominaModel> findById(Integer id);

    List<NominaModel> findAll();

    void deleteById(Integer id);

}
