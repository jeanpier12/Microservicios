package org.jhuachambe.msvc.servicios3.clients;

import org.jhuachambe.msvc.servicios3.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-usuarios", url = "localhost:8001")
public interface UsuarioClientRest {
    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/")
    Usuario crear(@RequestBody Usuario usuario);

    @GetMapping("/usuarios-por-servicio")
    List<Usuario> obtenerUsuariosPorServicio(@RequestParam Iterable<Long> ids);



}
