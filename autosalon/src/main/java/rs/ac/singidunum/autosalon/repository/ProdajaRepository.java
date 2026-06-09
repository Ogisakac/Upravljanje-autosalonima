package rs.ac.singidunum.autosalon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.autosalon.model.Prodaja;

@Repository
public interface ProdajaRepository extends JpaRepository<Prodaja, Long> {
	
	boolean existsByAutomobilId(Long automobilId);
	
	List<Prodaja> findByKupacId(Long kupacId);
	
	List<Prodaja> findByZaposleniId(Long zaposleniId);

}
