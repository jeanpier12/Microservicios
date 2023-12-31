package certus.edu.pe.servios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import certus.edu.pe.modelo.Categoria;

public interface ICategoriasService {
	Categoria guardar(Categoria categoria);
	void eliminar(Integer idCategoria);
	List<Categoria> buscarTodas();
	Categoria buscarPorId(Integer idCategoria);
	Page<Categoria> buscarTodas(Pageable page);
	Categoria actualizar(Categoria categoria);
}
