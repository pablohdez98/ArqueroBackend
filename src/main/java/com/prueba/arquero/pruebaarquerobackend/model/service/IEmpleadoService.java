package com.prueba.arquero.pruebaarquerobackend.model.service;

import com.prueba.arquero.pruebaarquerobackend.model.entity.Empleado;

import java.util.List;

public interface IEmpleadoService {

    public List<Empleado> getEmpleados();
    public Empleado getEmpleado(Long empleadoId);
    public boolean saveEmpleado(Empleado empleado);
    public boolean updateEmpleado(Long id, Empleado empleado);
    public boolean deleteEmpleado(Long empleadoId);

}
