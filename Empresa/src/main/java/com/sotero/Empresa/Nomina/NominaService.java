package com.sotero.Empresa.Nomina;

import org.springframework.stereotype.Service;

import com.sotero.Empresa.Empleado.EmpleadoModel;
import com.sotero.Empresa.Empleado.EmpleadoRepository;
import com.sotero.Empresa.Empleado.EmpleadoService;

import java.util.List;

@Service
public class NominaService {

    private final NominaRepository nominaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EmpleadoService empleadoService;
    private final EmpleadoModel empleadoModel;

    public NominaService(NominaRepository nominaRepository, EmpleadoRepository empleadoRepository,
            EmpleadoService empleadoService) {
        this.nominaRepository = nominaRepository;
        this.empleadoRepository = empleadoRepository;
        this.empleadoService = empleadoService;
        this.empleadoModel = new EmpleadoModel();
    }

    public String generarReporteNominas() {
        return null;
    }

    public List<NominaModel> obtenerTodas() {
        List<NominaModel> nominas = nominaRepository.findAll();
        System.out.println("Nominas encontradas: " + nominas);
        return nominas;
    }

}
