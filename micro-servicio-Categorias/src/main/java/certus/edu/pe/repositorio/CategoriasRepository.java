package certus.edu.pe.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import certus.edu.pe.modelo.Categoria;


@Repository
public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {
	
}

