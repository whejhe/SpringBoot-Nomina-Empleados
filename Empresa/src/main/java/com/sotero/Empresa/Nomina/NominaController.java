package com.sotero.Empresa.Nomina;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/nominas")
public class NominaController {

    private final NominaService nominaService;

    public NominaController(NominaService nominaService) {
        this.nominaService = nominaService;
    }

    @GetMapping
    public String listaNominas(Model model) {
        List<NominaModel> nominas = nominaService.obtenerTodas();
        model.addAttribute("nominas", nominas);
        return "nominas/lista";
    }

    @GetMapping("/generar-reporte")
    public String generarReporte(Model model) {
        String reporte = nominaService.generarReporteNominas();
        model.addAttribute("reporte", reporte);
        return "nominas/reporte";
    }

    @GetMapping("/procesar-automaticamente")
    public String procesarAutomaticamente() {
        nominaService.procesarNominasAutomaticamente();
        return "redirect:/nominas";
    }
}
