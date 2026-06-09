package rs.ac.singidunum.autosalon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.autosalon.model.Automobil;
import rs.ac.singidunum.autosalon.model.StatusAutomobila;

@Repository
public interface AutomobilRepository extends JpaRepository<Automobil, Long> {
	
	List<Automobil> findByStatus(StatusAutomobila status);
	
	List<Automobil> findByMarkaIgnoreCase(String marka);
}
