package com.prueba.arquero.pruebaarquerobackend.model.service;

import com.prueba.arquero.pruebaarquerobackend.model.entity.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public EmpleadoService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Empleado> getEmpleados() {
        final String sql = "SELECT * FROM empleados";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            Long id = resultSet.getLong("id");
            String nombre = resultSet.getString("nombre");
            String apellido1 = resultSet.getString("apellido1");
            String apellido2 = resultSet.getString("apellido2");
            String dni = resultSet.getString("dni");
            String domicilio = resultSet.getString("domicilio");
            Empleado emp = new Empleado();
            emp.setId(id);
            emp.setNombre(nombre);
            emp.setApellido1(apellido1);
            emp.setApellido2(apellido2);
            emp.setDni(dni);
            emp.setDomicilio(domicilio);
            return emp;
        });
    }

    public Empleado getEmpleado(Long empleadoId){
        final String sql = "SELECT * FROM empleados WHERE id = ?";
        Empleado empleado = jdbcTemplate.queryForObject(sql, new Object[]{empleadoId}, (resultSet, i) -> {
            Long id = resultSet.getLong("id");
            String nombre = resultSet.getString("nombre");
            String apellido1 = resultSet.getString("apellido1");
            String apellido2 = resultSet.getString("apellido2");
            String dni = resultSet.getString("dni");
            String domicilio = resultSet.getString("domicilio");
            Empleado emp = new Empleado();
            emp.setId(id);
            emp.setNombre(nombre);
            emp.setApellido1(apellido1);
            emp.setApellido2(apellido2);
            emp.setDni(dni);
            emp.setDomicilio(domicilio);
            return emp;
        });
        return empleado;
    }

    public boolean saveEmpleado(Empleado empleado) {
        if (!empleado.verifyDNI()) return false;

        final String sql = "INSERT INTO empleados (nombre, apellido1, apellido2, dni, domicilio) VALUES (?,?,?,?,?)";
        return jdbcTemplate.update(sql, new Object[]{empleado.getNombre(), empleado.getApellido1(), empleado.getApellido2(), empleado.getDni(), empleado.getDomicilio()}) == 1;
    }

    public boolean updateEmpleado(Long id, Empleado empleado){
        final String sql = "UPDATE empleados SET nombre=?, apellido1=?, apellido2=?, dni=?, domicilio=? WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[]{empleado.getNombre(), empleado.getApellido1(), empleado.getApellido2(), empleado.getDni(), empleado.getDomicilio(), id}) == 1;
    }

    public boolean deleteEmpleado(Long id) {
        final String sql = "DELETE FROM empleados WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[]{id}) == 1;
    }
}
