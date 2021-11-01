package br.com.isaccanedo.mail.api.client.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.isaccanedo.mail.api.client.entity.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
	@Query(value = "SELECT c FROM Client c")
	List<Client> getAllClients();
	
	@Query(value = "SELECT c FROM Client c WHERE c.email = :email")
	Optional<Client> findByEmail(@Param("email") String email);
}
