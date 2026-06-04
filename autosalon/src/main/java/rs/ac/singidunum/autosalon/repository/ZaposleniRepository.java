package rs.ac.singidunum.autosalon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.ac.singidunum.autosalon.model.Zaposleni;

@Repository
public interface ZaposleniRepository extends JpaRepository<Zaposleni, Long> {

}
