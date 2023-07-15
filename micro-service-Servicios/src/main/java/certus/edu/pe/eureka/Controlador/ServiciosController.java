package certus.edu.pe.eureka.Controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import certus.edu.pe.eureka.implementacion.IServicios;
import certus.edu.pe.eureka.modelo.Servicios;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/servicios")
public class ServiciosController {
    
    @Autowired
    private IServicios servicioService;
    

    @GetMapping("/Listar")
    public List<Servicios> mostrarServicios() {
    	return  servicioService.buscarTodas();
    }
    
    @PostMapping("/create")
    public Servicios guardar(@RequestBody Servicios servicio) {
    	return servicioService.guardar(servicio);
		
    }
    

    @GetMapping("/ver/{id}")
    public Servicios VerporId(@PathVariable("id") int id) {
     return  servicioService.buscarPorId(id);
       
    }

   

    @PutMapping("/update/{id}")   
    public ResponseEntity<Servicios> actualizarServicio(@PathVariable Integer id, @RequestBody Servicios servicio) {
        servicio.setId(id);
        Servicios ServicioActualizado = servicioService.actualizar(servicio);
        if (ServicioActualizado != null) {
        	return ResponseEntity.ok(ServicioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")  
 public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable("id") int idServicio) {
    	
	 servicioService.eliminar(idServicio);
        //se crea una variable respuesta
    	Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
		
   
   

}
