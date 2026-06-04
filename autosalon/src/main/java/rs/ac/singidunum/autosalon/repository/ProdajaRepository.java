package rs.ac.singidunum.autosalon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.autosalon.model.Prodaja;

@Repository
public interface ProdajaRepository extends JpaRepository<Prodaja, Long> {
	
	boolean existsByAutomobilId(Long automobilId);
	

}
