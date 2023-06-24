package org.jhuachambe.msvc.usuarios.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-servicios3", url = "localhost:8002")
public interface ServicioClienteRest {
    @DeleteMapping("/eliminar-servicio-usuario/{id}")
    void eliminarServicioUsuarioPorId(@PathVariable Long id);
}
