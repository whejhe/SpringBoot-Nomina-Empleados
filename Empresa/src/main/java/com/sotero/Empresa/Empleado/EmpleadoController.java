package com.sotero.Empresa.Empleado;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {

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

    @GetMapping({ "/agregarEmpleado", "/crear" })
    public String mostrarFormularioAgregar(Model model) {
        model.addAttribute("empleado", new EmpleadoModel());
        return "empleados/agregarEmpleado";
    }
   
    @PostMapping("/crear")
    public String crearEmpleado(@ModelAttribute EmpleadoModel empleado, Model model) {
        try {
            empleadoService.guardarEmpleado(empleado);
            return "redirect:/empleados";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear empleado: " + e.getMessage());
            return "empleados/agregarEmpleado";
        }
    }
    
    //Eliminar Empleados
    @PostMapping("/deleteById")
    public String eliminarEmpleado(@RequestParam("empleadoId") int empleadoId) {
        empleadoService.eliminarEmpleadoPorId(empleadoId);
        return "redirect:/empleados";
    }
    
    
    //Actualizar Lista
    @GetMapping("/actualizarLista")
    public String actualizarLista() {
        empleadoService.actualizarSalarios();
        return "redirect:/empleados";
    }
    
    //Editar Empleado
    @GetMapping("/editarEmpleado")
    public String mostrarFormularioEdicion(@RequestParam("empleadoId") int empleadoId, Model model) {
        EmpleadoModel empleado = empleadoService.obtenerEmpleadoPorId(empleadoId);
        model.addAttribute("empleado", empleado);
        return "empleados/editarEmpleado";
    }
    
    //Actualizar Empleado
    @PostMapping("/actualizarEmpleado")
    public String actualizarEmpleado(@ModelAttribute("empleado") EmpleadoModel empleado) {
        empleadoService.actualizarEmpleado(empleado);
        return "redirect:/empleados";
    }
}
