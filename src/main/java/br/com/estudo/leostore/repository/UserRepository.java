package br.com.estudo.leostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estudo.leostore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	User findByUsername(String username);
	
}
