package agenda.vacina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import agenda.vacina.model.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	
	@Query("select u from Usuario u where u.email = ?1")
	Usuario findUserByLogin(String login);
}
