package org.jhuachambe.msvc.servicios3.controllers;

import feign.FeignException;
import jakarta.validation.Valid;
import org.jhuachambe.msvc.servicios3.models.Usuario;
import org.jhuachambe.msvc.servicios3.models.entity.Servicio;
import org.jhuachambe.msvc.servicios3.services.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(name = "/")
public class ServicioController {
    @Autowired
    private ServicioService service;

    @GetMapping
    public ResponseEntity<List<Servicio>> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Servicio> o = service.porIDconUsuarios(id);//service.porID(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Servicio servicio, BindingResult result){
        if(result.hasErrors()){
            return Validar(result);
        }
        Servicio servicioDb = service.guardar(servicio);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Servicio servicio, BindingResult result, @PathVariable Long id){
        if(result.hasErrors()){
            return Validar(result);
        }

        Optional<Servicio> o = service.porID(id);
        if(o.isPresent()){
            Servicio servicioDb = o.get();
            servicioDb.setNombre(servicio.getNombre());
            servicioDb.setPrecio(servicio.getPrecio());
            servicioDb.setDiasAtencion(servicio.getDiasAtencion());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(servicioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Servicio> o = service.porID(id);
        if(o.isPresent()){
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-usuario/{id}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> o;
        try{
            o = service.asignarUsuario(usuario, id);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje","no existe el usuario por id o error en la comunicacion: "
                            + e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-usuario/{servicioId}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long servicioId){
        Optional<Usuario> o = null;
        try{
            o = service.crearUsuario(usuario, servicioId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje","No se logro crear el usuario o error en la comunicacion: "
                            + e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{servicioId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long servicioId){
        Optional<Usuario> o = null;
        try{
            o = service.eliminarUsuario(usuario, servicioId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje","no existe el usuario por id o error en la comunicacion: "
                            + e.getMessage()));
        }

        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-servicio-usuario/{id}")
    public ResponseEntity<?> eliminarServicioUsuarioPorId(@PathVariable Long id){
        service.eliminarServicioUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Map<String, String>> Validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo "+err.getField()+" "+err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }
}
