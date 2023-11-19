package com.sotero.Empresa.Empleado;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {//Modifica lo necesario para las nuevas implementaciones

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public String listaEmpleados(Model model, @RequestParam(required = false) String search,
            @RequestParam(required = false) String filterBy) {
        List<EmpleadoModel> empleados;

        if (search != null && !search.isEmpty() && filterBy != null && !filterBy.isEmpty()) {
            empleados = (List<EmpleadoModel>) empleadoService.buscarPorCampo(search, filterBy);
        } else {

            empleados = empleadoService.obtenerTodos();
        }

        model.addAttribute("empleados", empleados);
        return "empleados/listaEmpleados";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("empleado", new EmpleadoModel());
        return "empleados/formulario";
    }

    @PostMapping("/crear")
    public String crearEmpleado(@ModelAttribute EmpleadoModel empleado, Model model) {
        try {
            empleadoService.guardarEmpleado(empleado);
            return "redirect:/empleados";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear empleado: " + e.getMessage());
            return "empleados/formulario";
        }
    }


    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        EmpleadoModel empleado = empleadoService.obtenerPorId(id);
        model.addAttribute("empleado", empleado);
        return "empleados/formulario";
    }

    @PostMapping("/editar/{id}")
    public String editarEmpleado(@PathVariable int id, @ModelAttribute EmpleadoModel empleado) {
        empleado.setId(id);
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpleado(@PathVariable int id) {
        empleadoService.eliminarEmpleado(id);
        return "redirect:/empleados";
    }
}
