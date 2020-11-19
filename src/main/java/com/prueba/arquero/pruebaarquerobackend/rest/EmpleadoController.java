package com.prueba.arquero.pruebaarquerobackend.rest;

import com.google.gson.Gson;
import com.prueba.arquero.pruebaarquerobackend.model.entity.Empleado;
import com.prueba.arquero.pruebaarquerobackend.model.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/api/empleado")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    @Autowired
    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @GetMapping
    public void getEmpleados(HttpServletResponse response) throws IOException {
        List<Empleado> empleados = empleadoService.getEmpleados();
        if (empleados != null) {
            Gson json = new Gson();
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print("{\"status\": " + HttpServletResponse.SC_OK + ",\"message\": \"Lista de empleados obtenida con éxito\", \"empleados\": " + json.toJson(empleados) + "}");
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo obtener la lista de empleados");
        }        
    }

    @GetMapping(path = "{id}")
    public void getEmpleado(@PathVariable("id") long id, HttpServletResponse response) throws IOException{
        Empleado empleado = empleadoService.getEmpleado(id);
        if (empleado != null) {
            Gson json = new Gson();
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print("{\"status\": " + HttpServletResponse.SC_OK + ",\"message\": \"Empleado obtenido con éxito\", \"empleado\": " + json.toJson(empleado) + "}");
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo obtener al empleado");
        }        
    }

    @PostMapping
    public void saveEmpleado(@Valid @NonNull @RequestBody Empleado empleado, HttpServletResponse response) throws IOException {
        if (empleadoService.saveEmpleado(empleado)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print("{\"status\": " + HttpServletResponse.SC_CREATED + ",\"message\": \"Empleado creado correctamente\"}");
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo crear al empleado");
        }
    }

    @PutMapping(path = "{id}")
    public void updateEmpleado(@PathVariable("id") long id, @Valid @NonNull @RequestBody Empleado empleado, HttpServletResponse response)
            throws IOException {
        if (empleadoService.updateEmpleado(id, empleado)) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print("{\"status\": " + HttpServletResponse.SC_OK + ",\"message\": \"Empleado actualizado correctamente\"}");
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo actualizar al empleado");
        }
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmpleado(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {
        if (empleadoService.deleteEmpleado(id)) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print("{\"status\": " + HttpServletResponse.SC_OK + ",\"message\": \"Empleado eliminado correctamente\"}");
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo eliminar al empleado");
        }
    }
}
