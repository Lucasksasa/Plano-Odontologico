package com.DevMaker.Plano_Odondologico.repository;

import com.DevMaker.Plano_Odondologico.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DentistaRepositorio extends JpaRepository <Dentista, Long>{
    Optional<Dentista> findByCro(String cro);
}
