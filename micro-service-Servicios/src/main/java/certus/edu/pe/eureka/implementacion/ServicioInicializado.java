package certus.edu.pe.eureka.implementacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import certus.edu.pe.eureka.Repositorio.ServicoRepository;
import certus.edu.pe.eureka.modelo.Servicios;




@Primary
@Service
public class ServicioInicializado  implements IServicios{

	@Autowired
	private ServicoRepository serviciosRepo;
	

	@Override
	public List<Servicios> buscarTodas() {
	
		return serviciosRepo.findAll();
	}

	@Override
	public Servicios guardar(Servicios servicio) {
		return serviciosRepo.save(servicio);
		
	}
	@Override
	public Servicios actualizar(Servicios servicio) {
		//Falta agregar
		   Integer idServicio = servicio.getId();
		    Servicios servicioExistente = buscarPorId(idServicio);
		    if (servicioExistente != null) {
		    	servicioExistente.setNombre(servicio.getNombre());
		    	servicioExistente.setDescripcion(servicio.getDescripcion());
		    	servicioExistente.setFecha(servicio.getFecha());
		    	servicioExistente.setSalario(servicio.getSalario());
		    	servicioExistente.setEstatus(servicio.getEstatus());
		    	servicioExistente.setDestacado(servicio.getDestacado());
		    	servicioExistente.setImagen(servicio.getImagen());
		    	servicioExistente.setDetalles(servicio.getDetalles());
		        return serviciosRepo.save(servicioExistente);
		    }
		    return null;
	}

	@Override
	public void eliminar(Integer servicio) {
		serviciosRepo.deleteById(servicio);
	}

	

	@Override
	public Servicios buscarPorId(Integer servicio) {
		Optional<Servicios> optional = serviciosRepo.findById(servicio);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
		
	}

	@Override
	public List<Servicios> buscarDestacadas() {
	
		return serviciosRepo.findByDestacadoAndEstatusOrderByIdDesc(1, "Aprobada");
	}

	@Override
	public Page<Servicios> buscarTodas(Pageable page) {
		
		return serviciosRepo.findAll(page);
	}

	@Override
	public List<Servicios> buscarByExample(Example<Servicios> example) {
		
		return serviciosRepo.findAll(example);
	}

	
	

	
}
