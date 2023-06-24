package org.jhuachambe.msvc.usuarios.controllers;

import jakarta.validation.Valid;
import org.jhuachambe.msvc.usuarios.models.entity.Usuario;
import org.jhuachambe.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(name = "/")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/")
    public List<Usuario> listar(){
        return  service.listar();
    };

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = service.porID(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get()) ;
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result){
        if(result.hasErrors()){
            return Validar(result);
        }
        if(!usuario.getEmail().isEmpty() && service.porEmail(usuario.getEmail()).isPresent()){
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("Mensaje", "Ya existo un usuario con ese correo electronico"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id){

        if(result.hasErrors()){
            return Validar(result);
        }

        Optional<Usuario> o = service.porID(id);
        if(o.isPresent()){
            Usuario usuariodb = o.get();
            if(!usuario.getEmail().isEmpty() && !usuario.getEmail().equalsIgnoreCase(usuariodb.getEmail()) && service.porEmail(usuario.getEmail()).isPresent()){
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("Mensaje", "Ya existo un usuario con ese correo electronico"));
            }
            usuariodb.setNombre(usuario.getNombre());
            usuariodb.setEmail(usuario.getEmail());
            usuariodb.setPassword(usuario.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuariodb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Usuario> o = service.porID(id);
        if(o.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios-por-servicio")
    public ResponseEntity<?> obtenerUsuariosPorServicio(@RequestParam List<Long> ids){
        return ResponseEntity.ok(service.listarPorIds(ids));
    }

    private ResponseEntity<Map<String, String>> Validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo "+err.getField()+" "+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
