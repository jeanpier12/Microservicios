package certus.edu.pe.eureka.implementacion;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import certus.edu.pe.eureka.modelo.Servicios;



public interface IServicios {
	Servicios guardar(Servicios servicio);
	void eliminar(Integer servicio);
	List<Servicios> buscarTodas();
	Servicios buscarPorId(Integer servicio);
	List<Servicios> buscarDestacadas();
	Page<Servicios> buscarTodas(Pageable page);
	List<Servicios> buscarByExample(Example<Servicios> example);
	Servicios actualizar(Servicios servicio);
}
