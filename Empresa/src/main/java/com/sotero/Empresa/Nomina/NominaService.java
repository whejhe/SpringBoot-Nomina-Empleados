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
        List<NominaModel> nominas = nominaRepository.findAll();

        StringBuilder reporte = new StringBuilder();
        for (NominaModel nomina : nominas) {
            reporte.append("Sueldo: ").append(nomina.getSueldo()).append("\n");
        }

        return reporte.toString();
    }

    public void procesarNominasAutomaticamente() {
        List<EmpleadoModel> empleados = empleadoRepository.findAll();

        for (EmpleadoModel empleado : empleados) {
        	int categoria = empleadoModel.getCategoria();
        	int anyos = empleadoModel.getAnyosTrabajados();
            int salario = empleadoService.calcularSueldo(categoria, anyos);
            NominaModel nomina = new NominaModel(salario);

            nominaRepository.save(nomina);
        }
    }

    public List<NominaModel> obtenerTodas() {
        return nominaRepository.findAll();
    }

}
