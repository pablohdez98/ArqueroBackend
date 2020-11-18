package com.prueba.arquero.pruebaarquerobackend.rest;

import com.prueba.arquero.pruebaarquerobackend.model.entity.Empleado;
import com.prueba.arquero.pruebaarquerobackend.model.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping (value="/api/empleado")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public List<Empleado> getEmpleados() {
        return empleadoService.getEmpleados();
    }

    @GetMapping(path = "{id}")
    public Empleado getEmpleado(@PathVariable("id") long id) {
        return empleadoService.getEmpleado(id);
    }

    @PostMapping
    public void saveEmpleado(@NonNull @RequestBody Empleado empleado) {
        empleadoService.saveEmpleado(empleado);
    }

    @PutMapping(path = "{id}")
    public void updateEmpleado(@PathVariable("id") long id, @NonNull @RequestBody Empleado empleado) {
        empleadoService.updateEmpleado(id, empleado);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmpleado(@PathVariable("id") Long id) {
        empleadoService.deleteEmpleado(id);
    }

}
