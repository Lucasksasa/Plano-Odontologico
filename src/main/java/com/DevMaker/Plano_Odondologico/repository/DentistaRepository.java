package com.DevMaker.Plano_Odondologico.repository;

import com.DevMaker.Plano_Odondologico.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DentistaRepository extends JpaRepository <Dentista, Long>{
    Optional<Dentista> findByCro(String cro);
}
