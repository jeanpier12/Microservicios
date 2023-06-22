package certus.edu.pe.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    

    @GetMapping("/indexPaginate")
    public Page<Categoria> mostrarIndexPaginado(Pageable page) {
        return serviceCategorias.buscarTodas(page);
    }

   
    @PostMapping("/save")
    public ResponseEntity<String> guardar(@RequestBody Categoria categoria) {
        serviceCategorias.guardar(categoria);
        return ResponseEntity.ok("Los datos de la categoría fueron guardados!");
    }

    //buscar por id
    @GetMapping("/ver/{id}")
    public Categoria VerporId(@PathVariable("id") int idCategoria) {
        return serviceCategorias.buscarPorId(idCategoria);
    }

    
    
    
    @PutMapping("actualizar/{id}")
    public ResponseEntity<String> actualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoria) {
        categoria.setId(id);
        Categoria categoriaActualizada = serviceCategorias.actualizar(categoria);
        if (categoriaActualizada != null) {
            return ResponseEntity.ok("La categoria fue actualizada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminar(@PathVariable("id") int idCategoria) {
        serviceCategorias.eliminar(idCategoria);
        return ResponseEntity.ok("La categoría fue eliminada!");
    }
		
}
