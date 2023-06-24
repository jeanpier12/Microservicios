package org.jhuachambe.msvc.servicios3.services;

import org.jhuachambe.msvc.servicios3.clients.UsuarioClientRest;
import org.jhuachambe.msvc.servicios3.models.Usuario;
import org.jhuachambe.msvc.servicios3.models.entity.Servicio;
import org.jhuachambe.msvc.servicios3.models.entity.ServicioUsuario;
import org.jhuachambe.msvc.servicios3.repositories.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicioServiceImpl implements ServicioService{
    @Autowired
    private ServicioRepository repository;

    @Autowired
    private UsuarioClientRest cliente;

    @Override
    @Transactional(readOnly = true)
    public List<Servicio> listar() {
        return (List<Servicio>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> porID(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Servicio guardar(Servicio servicio) {
        return repository.save(servicio);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void eliminarServicioUsuarioPorId(Long id) {
        repository.eliminarServicioUsuarioPorId(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Servicio> porIDconUsuarios(Long id) {
        Optional<Servicio> o = repository.findById(id);
        if(o.isPresent()){
            Servicio servicio = o.get();
            if (!servicio.getServicioUsuarios().isEmpty()){
                List<Long> ids = servicio.getServicioUsuarios().stream()
                        .map(ServicioUsuario::getUsuarioId).toList();

                List<Usuario> usuarios = cliente.obtenerUsuariosPorServicio(ids);
                servicio.setUsuarios(usuarios);
            }
            return Optional.of(servicio);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long id) {
        Optional<Servicio> o = repository.findById(id);
        if (o.isPresent()){
            Usuario usuarioMsvc = cliente.detalle(usuario.getId());

            Servicio servicio = o.get();
            ServicioUsuario servicioUsuario = new ServicioUsuario();
            servicioUsuario.setUsuarioId(usuarioMsvc.getId());

            servicio.addServicioUsuario(servicioUsuario);
            repository.save(servicio);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long id) {
        Optional<Servicio> o = repository.findById(id);
        if (o.isPresent()){
            Usuario usuarionuevoMsvc = cliente.crear(usuario);

            Servicio servicio = o.get();
            ServicioUsuario servicioUsuario = new ServicioUsuario();
            servicioUsuario.setUsuarioId(usuarionuevoMsvc.getId());

            servicio.addServicioUsuario(servicioUsuario);
            repository.save(servicio);
            return Optional.of(usuarionuevoMsvc);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long id) {
        Optional<Servicio> o = repository.findById(id);
        if (o.isPresent()){
            Usuario usuarioMsvc = cliente.detalle(usuario.getId());

            Servicio servicio = o.get();
            ServicioUsuario servicioUsuario = new ServicioUsuario();
            servicioUsuario.setUsuarioId(usuarioMsvc.getId());

            servicio.removeServicioUsuario(servicioUsuario);
            repository.save(servicio);
            return Optional.of(usuarioMsvc);
        }
        return Optional.empty();
    }
}
