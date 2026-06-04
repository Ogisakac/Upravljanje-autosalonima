package rs.ac.singidunum.autosalon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.autosalon.model.Automobil;

@Repository
public interface AutomobilRepository extends JpaRepository<Automobil, Long> {

}
