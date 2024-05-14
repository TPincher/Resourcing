package projects.resourcing.credentials;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Long>{
	
	Optional<Credentials> findByUsername(String username);

}
