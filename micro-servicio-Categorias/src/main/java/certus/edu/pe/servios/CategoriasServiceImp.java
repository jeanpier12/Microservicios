package certus.edu.pe.servios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import certus.edu.pe.modelo.Categoria;
import certus.edu.pe.repositorio.CategoriasRepository;

@Service
public class CategoriasServiceImp implements ICategoriasService {


	@Autowired
	private CategoriasRepository categoriasRepo;
	
	//medotod para guardar un acategoria
	@Override
	public Categoria guardar(Categoria categoria) {
		return categoriasRepo.save(categoria);
	}

	@Override
	public void eliminar(Integer idCategoria) {
		categoriasRepo.deleteById(idCategoria);
	}

	@Override
	//significa transaccion solo de lectura
		@Transactional(readOnly = true)
		public List<Categoria> buscarTodas() {
			return (List<Categoria>) categoriasRepo.findAll();
		}
	

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = categoriasRepo.findById(idCategoria);
		//Si el Optional contiene una categoría (optional.isPresent() es true
		if (optional.isPresent()) {
			//se devuelve esa categoría utilizando el método get()
			return optional.get();
		}
		return null;
	}
	
	
   


	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		return categoriasRepo.findAll(page);	
	}

	
	///Metodo de actualizar
		@Override
		public Categoria actualizar(Categoria categoria) {
		    Integer idCategoria = categoria.getId();
		    Categoria categoriaExistente = buscarPorId(idCategoria);
		    if (categoriaExistente != null) {
		        categoriaExistente.setNombre(categoria.getNombre());
		        categoriaExistente.setDescripcion(categoria.getDescripcion());
		        return categoriasRepo.save(categoriaExistente);
		    }
		    return null;
		}

}
