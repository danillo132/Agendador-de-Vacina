package agenda.vacina.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agenda.vacina.model.Ubs;

@Repository
@Transactional
public interface UbsRepository extends CrudRepository<Ubs, Long> {

	@Query("select u from Ubs u where u.nome = ?1")
	Ubs findUbsByName(String nome);
}
