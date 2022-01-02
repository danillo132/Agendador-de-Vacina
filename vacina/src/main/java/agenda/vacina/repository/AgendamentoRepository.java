package agenda.vacina.repository;

import javax.transaction.TransactionSynchronizationRegistry;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agenda.vacina.model.Agendamento;

@Repository
@Transactional
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {

	
	@Query("Select p from Agendamento p where p.usuario.id = ?1 and p.dose = ?2 ")
	 Agendamento doses(Long id, String dose);
}
