package certus.edu.pe.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


import certus.edu.pe.modelo.Categoria;
import certus.edu.pe.servios.ICategoriasService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/categorias")
public class CategoriasController {
	
	// Inyectamos una instancia desde nuestro ApplicationContext   
    @Autowired
   	private ICategoriasService serviceCategorias;
	  
    /**
	 * Metodo que muestra la lista de categorias sin paginacion
	 * @param model
	 * @param page
	 * @return
	 */
    @GetMapping("/Lista")
    public List<Categoria> mostrarLista() {
        return serviceCategorias.buscarTodas();
    }
    

   
    @PostMapping("/save")
    public Categoria guardar(@RequestBody Categoria categoria) {
        return serviceCategorias.guardar(categoria);
        	//ResponseEntity.ok("Los datos de la categoría fueron guardados!");
    }

    //buscar por id
    @GetMapping("/ver/{id}")
    public Categoria VerporId(@PathVariable("id") int idCategoria) {
        return serviceCategorias.buscarPorId(idCategoria);
    }

    
    
    
    @PutMapping("actualizar/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria categoriaActualizada = serviceCategorias.actualizar(categoria);
        if (categoriaActualizada != null) {
        	return ResponseEntity.ok(categoriaActualizada);
          //  return ResponseEntity.ok("La categoria fue actualizada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminar(@PathVariable("id") int idCategoria) {
    	
        serviceCategorias.eliminar(idCategoria);
        //se crea una variable respuesta
    	Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
		
}
