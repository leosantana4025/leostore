package br.com.estudo.leostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.estudo.leostore.model.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long> {

}
