package org.jhuachambe.msvc.servicios3.services;

import org.jhuachambe.msvc.servicios3.models.Usuario;
import org.jhuachambe.msvc.servicios3.models.entity.Servicio;

import java.util.List;
import java.util.Optional;

public interface ServicioService {
    List<Servicio> listar();
    Optional<Servicio> porID(Long id);
    Optional<Servicio> porIDconUsuarios(Long id);
    Servicio guardar(Servicio servicio);
    void eliminar(Long id);
    void eliminarServicioUsuarioPorId(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long id);
    Optional<Usuario> crearUsuario(Usuario usuario, Long id);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long id);
}
