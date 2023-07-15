package certus.edu.pe.eureka.Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import certus.edu.pe.eureka.modelo.Servicios;


public interface ServicoRepository  extends JpaRepository<Servicios, Integer>{


	List<Servicios> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);


}
