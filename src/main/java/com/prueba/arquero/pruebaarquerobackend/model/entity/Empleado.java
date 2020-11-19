package com.prueba.arquero.pruebaarquerobackend.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Entity
@Table(name="empleados")
public class Empleado implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nombre")
    @NotBlank(message = "El campo 'nombre' es requerido")
    @Size(min = 3, max = 25, message = "'nombre' debe tener entre 3 y 25 caracteres")
    private String nombre;

    @Column(name="apellido1")
    @NotBlank(message = "El campo 'apellido1' es requerido")
    @Size(min = 3, max = 25, message = "'apellido1' debe tener entre 3 y 25 caracteres")
    private String apellido1;

    @Column(name="apellido2")
    @NotBlank(message = "El campo 'apellido2' es requerido")
    @Size(min = 3, max = 25, message = "'apellido2' debe tener entre 3 y 25 caracteres")
    private String apellido2;

    @Column(name="dni")
    @NotBlank(message = "El campo 'dni' es requerido")
    @Pattern(regexp = "[0-9]{8}[A-Za-z]", message = "DNI incorrecto")
    private String dni;

    @Column(name="domicilio")
    @NotBlank(message = "El campo 'domicilio' es requerido")
    @Size(min = 3, max = 50, message = "'nombre' debe tener entre 3 y 50 caracteres")
    private String domicilio;

    public Empleado(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public boolean verifyDNI() {

        String alphabet = "TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = 0;
        int num = Integer.parseInt(this.dni.substring(0, 8));
        char letra = this.dni.charAt(8);

        resto = num % 23;
        char checkLetra = alphabet.charAt(resto);

        if (Character.toUpperCase(letra) != checkLetra) {
            return false;
        }
        return true;
    }
}
