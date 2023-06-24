package org.jhuachambe.msvc.servicios3.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.jhuachambe.msvc.servicios3.models.Usuario;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "servicios2")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "servicio_id")
    private List<ServicioUsuario> servicioUsuarios;

    @Transient
    private List<Usuario> usuarios;

    public Servicio() {
        servicioUsuarios = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    @NotNull
    private Float precio;
    @NotNull
    private Long diasAtencion;

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

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Long getDiasAtencion() {
        return diasAtencion;
    }

    public void setDiasAtencion(Long diasAtencion) {
        this.diasAtencion = diasAtencion;
    }

    public void addServicioUsuario(ServicioUsuario servicioUsuario){
        servicioUsuarios.add(servicioUsuario);
    }

    public void removeServicioUsuario(ServicioUsuario servicioUsuario){
        servicioUsuarios.remove(servicioUsuario);
    }
    public List<ServicioUsuario> getServicioUsuarios() {
        return servicioUsuarios;
    }

    public void setServicioUsuarios(List<ServicioUsuario> servicioUsuarios) {
        this.servicioUsuarios = servicioUsuarios;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
