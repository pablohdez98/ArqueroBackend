package com.prueba.arquero.pruebaarquerobackend.model.service;

import com.prueba.arquero.pruebaarquerobackend.model.dao.IEmpleadoDao;
import com.prueba.arquero.pruebaarquerobackend.model.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    private IEmpleadoDao empleadoDao;

    public List<Empleado> getEmpleados(){
        return null;
    }

    public Empleado getEmpleado(Long empleadoId){
        return null;
    }

    public void saveEmpleado(Empleado empleado){
    }

    public void updateEmpleado(Empleado empleado){

    }

    public void deleteEmpleado(Long id){
    }
}
