package br.com.estudo.leostore.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.estudo.leostore.model.Pedido;
import br.com.estudo.leostore.model.Status;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Cacheable("pedido")
	List<Pedido> findByStatus(Status status, Pageable sort);

	@Query("select p from Pedido p join p.user u where u.username = :username")
	List<Pedido> findAllByUsuario(@Param("username") String username);

	@Query("select p from Pedido p join p.user u where u.username = :username and p.status = :status")
	List<Pedido> findByStatusAndUser(@Param("status") Status status, @Param("username") String username);
	
}
