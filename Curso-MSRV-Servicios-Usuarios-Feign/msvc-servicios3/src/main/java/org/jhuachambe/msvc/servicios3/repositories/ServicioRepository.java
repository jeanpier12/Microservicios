package org.jhuachambe.msvc.servicios3.repositories;

import org.jhuachambe.msvc.servicios3.models.entity.Servicio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ServicioRepository extends CrudRepository<Servicio, Long> {
    @Modifying
    @Query("delete from ServicioUsuario su where su.usuarioId=?1")
    void eliminarServicioUsuarioPorId(Long id);
}
